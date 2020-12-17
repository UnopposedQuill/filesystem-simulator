
package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author darkl
 */
public class DriveManager {
    private final char[] diskContents;
    private final boolean[] ocuppiedSectors;
    private final int sectorSize;
    private final File diskFile;
    private final DirectoryNode rootNode;
    private DirectoryNode currentDirectory;

    /**
     * Creates a virtual disk, and attaches a drive manager to it.
     * @param diskSize The size of the disk
     * @param sectorSize Amount of bytes for each sector
     * @throws IOException In case it couldn't create the drive file
     */
    public DriveManager(int diskSize, int sectorSize) throws IOException {
        this.diskContents = new char[diskSize];
        
        //Initialize the whole drive to zeroes
        for (int i = 0; i < diskContents.length; i++) {
            diskContents[i] = 0;
        }
        
        this.sectorSize = sectorSize;
        
        //Update the free sector array
        this.ocuppiedSectors = new boolean[diskContents.length/sectorSize];
        for (int i = 0; i < ocuppiedSectors.length; i++) {
            ocuppiedSectors[i] = false;//Every sector is free right now
        }
        
        //Now I need to create the file representing the disk
        diskFile = new File("drive.dat");
        
        try {
            diskFile.createNewFile();
        } catch (IOException ex) {
            System.out.println("Failure to create file, attempting to delete first");
            diskFile.delete();
            diskFile.createNewFile();
        }
        
        //Then write the current contents to it
        try (FileWriter fw = new FileWriter(diskFile)) {
            fw.write(diskContents);
        }
        
        //Now that the drive is done, I prepare the file system
        this.rootNode = new DirectoryNode(null, "root");
        this.currentDirectory = this.rootNode;
    }
    
    /**
     * This method will take care of inserting a new directory inside the current directory
     * @param name The name of the new directory
     */
    public void makeDirectory(String name){
        this.currentDirectory.addChildren(new DirectoryNode(currentDirectory, name));
    }
    
    /**
     * This method will take care of changing the currentDirectory
     * @param route The route to which change into
     * @return A boolean representing the success of the directory change
     */
    public boolean changeDirectory(String route){
        //So I can return in case of error
        DirectoryNode previousDirectory = currentDirectory;
        
        //First check if it's absolute or relative route
        if (route.startsWith("/root/")) {
            //Absolute route
            this.currentDirectory = this.rootNode;
            route = route.substring(6);//Remove the starting '/'
        }
        
        String[] directoryNames = route.isEmpty() ? new String[0] : route.split("/");
        boolean advanced = false;//Will be set to true in each run
        
        //Now since the directory swap, I can now proceed to explore
        for (String directoryName : directoryNames) {
            //I need to go for each of the children in said directory
            for (int i = 0; i < this.currentDirectory.getChildren().size(); i++) {
                FileSystemNode childrenNode = this.currentDirectory.getChildren().get(i);
                
                //If it is a directory, and its name matches
                if (childrenNode instanceof DirectoryNode && ((DirectoryNode)childrenNode).getName().equals(directoryName)) {
                    currentDirectory = (DirectoryNode)childrenNode;
                    advanced = true;
                    break;//I'll skip all the other children, so I can process next directory
                }
            }
            
            //There was no child with the name, error and go back
            if (!advanced) {
                this.currentDirectory = previousDirectory;
                return false;
            }
            advanced = false;
        }
        return true;
    }

    /**
     * Returns an instance of DirectoryNode representing the current node
     * @return An instance containing the information of the current directory
     */
    public DirectoryNode getCurrentDirectory() {
        return currentDirectory;
    }
    
    /**
     * Returns an instance of DirectoryNode representing the root node
     * @return An instance containing the information of the root directory
     */
    public DirectoryNode getRootDirectory() {
        return rootNode;
    }
    
    /**
     * This will return the first empty sector in the drive
     * @return A file sector pointing to the first empty sector of this drive
     * It may return null if the drive is full
     */
    public FileSector getFirstEmptySector(){
        
        //I need a pointer to said sector
        for (int i = 0; i < this.ocuppiedSectors.length; i++) {
            if (!this.ocuppiedSectors[i]) {
                //This sector is free, set it to occupied and return it
                this.ocuppiedSectors[i] = true;
                return new FileSector(i * sectorSize, null);
            }
        }
        return null;//Disk is full
    }
    
    /**
     * This will flag as free the given file sector
     * @param fileSector The file sector to be freed
     */
    private void freeSector(FileSector fileSector){
        this.ocuppiedSectors[fileSector.getSectorPointer()/sectorSize] = false;
    }
    
    private static void copyArrayContents(char[] destination, int destOffset, char[] source, int sourceOffset, int amount){
        int initialPosition = 0;
        while(amount-- > 0){
            destination[(initialPosition++) + destOffset] = source[sourceOffset++];
        }
    }
    
    public void createFile(int fileSize, String extension, String name){
        //First I need to allocate enough sectors for this file
        int sectorAmount = (int)Math.ceil((double)fileSize/sectorSize);
        FileSector[] sectors = new FileSector[sectorAmount];
        
        //Populate with empty sectors
        for (int i = 0; i < sectors.length; i++) {
            sectors[i] = this.getFirstEmptySector();
            //I need to hook each sector to its next one
            if (i > 0) sectors[i-1].setNextSector(sectors[i]);
            
            for (int j = 0; j < this.sectorSize; j++) {
                this.diskContents[sectors[i].getSectorPointer() + j] = 0;
            }
        }
        
        //Lastly I can now insert it
        this.currentDirectory.addChildren(new FileNode(sectors[0], sectors[sectorAmount-1], fileSize, extension, name, this.currentDirectory));
    }
    
    /**
     * Will return an array with the contents of a given fileNode
     * @param fileNode The fileNode from which to retrieve the contents
     * @return An array with the information
     */
    public char[] getData(FileNode fileNode){
        
        char[] contents = new char[fileNode.getSize()];
        int counter = 0;
        FileSector pointer = fileNode.getBegin();
        while (pointer != null){
            for (int i = 0; i < this.sectorSize && (counter * sectorSize) + i < fileNode.getSize(); i++) {
                contents[(counter * sectorSize) + i] =
                        this.diskContents[pointer.getSectorPointer() + i];
            }
            counter++;
            pointer = pointer.getNextSector();
        } 
        
        return contents;
    }
    
    /**
     * This will take care of overwritting the previous data using the new data
     * @param fileNode The node whose data will be overwritten
     * @param data The data which will be saved on top of the previous one
     */
    public void saveData(FileNode fileNode, char[] data){
        FileSector pointer = fileNode.getBegin();
        //I will use the min here, but it should be the same
        int amountToSave = Math.min(data.length, fileNode.getSize()),
                counter = 0;
        
        //While I'm missing some pointers
        while(pointer != null){
            
            //Move current remaining data into the sector
            DriveManager.copyArrayContents(this.diskContents, pointer.getSectorPointer(), data, counter, this.sectorSize - ((counter + sectorSize) - amountToSave));
            counter += this.sectorSize - ((counter + sectorSize) - amountToSave);
            pointer = pointer.getNextSector();
        }
        
        //And lastly, change its modification date
        fileNode.updateModificationDate();
    }

    /**
     * This will return an array representing the data of the current contents
     * It is linked to the actual contents
     * @return An array pointer to the current diskContents
     */
    public char[] getContent() {
        return this.diskContents;
    }
    
    public void removeNode(FileSystemNode fileSystemNode) {
        if (fileSystemNode != null){
            if (fileSystemNode instanceof DirectoryNode) {
                DirectoryNode directoryNode = (DirectoryNode) fileSystemNode;
                //I need to free all of its childs
                for (int i = 0; i < directoryNode.getChildren().size(); i++) {
                    removeNode(directoryNode.getChildren().get(i));
                }
            }
            //I cannot ever remove the root file, but I can remove the contents in it
            if (fileSystemNode != this.rootNode){
                //Now I have to free its data
                if (fileSystemNode instanceof FileNode) {
                    FileNode fileNode = (FileNode) fileSystemNode;
                    FileSector pointer = fileNode.getBegin();
                    while(pointer != null){
                        this.freeSector(pointer);
                        pointer = pointer.getNextSector();
                    }
                }
                
                fileSystemNode.parent.getChildren().remove(fileSystemNode);
            }
        }
    }
    
    /**
     * Returns whether the specified sector is free
     * @param sector The sector to be evaluated
     * @return True if the sector is free
     */
    public boolean isSectorFree(int sector){
        return !this.ocuppiedSectors[sector];
    }

    /**
     * Returns an int representation of the current sector size in this
     * drive manager
     * @return An integer representing the current sector size
     */
    public int getSectorSize() {
        return sectorSize;
    }
}
