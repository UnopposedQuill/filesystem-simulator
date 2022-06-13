
package model;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author darkl
 */
public class DriveManager {
    private final char[] diskContents;
    private final FileSectorStateEnum[] ocuppiedSectors;
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
        this.ocuppiedSectors = new FileSectorStateEnum[diskContents.length/sectorSize];
        for (int i = 0; i < ocuppiedSectors.length; i++) {
            ocuppiedSectors[i] = FileSectorStateEnum.FREE;//Every sector is free right now
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
        
        this.updateDiskContents();
        
        //Now that the drive is done, I prepare the file system
        this.rootNode = new DirectoryNode(null, "root");
        this.currentDirectory = this.rootNode;
    }
    
    /**
     * This method will take care of inserting a new directory inside the current directory
     * It will check for existing directories before creating
     * @param name The name of the new directory
     * @return True if the creation was successful, false otherwise
     */
    public boolean makeDirectory(String name){
        //First I need to check if there's a file with that name and extension
        DirectoryNode newDirectoryNode = new DirectoryNode(this.currentDirectory, name);
        
        if (this.currentDirectory.getChildren().contains(newDirectoryNode)){
            //It exists, return failure
            return false;
        }
        
        this.currentDirectory.addChildren(newDirectoryNode);
        return true;
    }
    
    /**
     * This method will take care of changing the currentDirectory
     * If the route has the form of "/root/subdirectory" then the route
     * will be taken as a absolute, otherwise it will start navigating through
     * the current directory's subcontent
     * @param route The route to which change into
     * @return A Boolean representing the success of the directory change
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
            if (this.ocuppiedSectors[i] == FileSectorStateEnum.FREE) {
                //This sector is free, set it to occupied and return it
                this.ocuppiedSectors[i] = FileSectorStateEnum.OCCUPIED;
                return new FileSector(i * sectorSize);
            }
        }
        return null;//Disk is full
    }
    
    /**
     * This will flag as free the given file sector
     * @param fileSector The file sector to be freed
     */
    private void freeSector(FileSector fileSector){
        this.ocuppiedSectors[fileSector.getSectorPointer()/sectorSize] = FileSectorStateEnum.FREE;
    }
    
    /**
     * This function allows for easy copying of contents between two arrays
     * It will move the smaller between amount and the source length bytes from
     * the source pointed by sourceoffset into the destination by destOffset.
     * @param destination The destination array
     * @param destOffset The position in the destination from which to start copying
     * @param source The source data
     * @param sourceOffset The position in the source from which to start copying
     * @param amount The maximum amount of bytes to move
     */
    private static void copyArrayContents(char[] destination, int destOffset, char[] source, int sourceOffset, int amount){
        int initialPosition = 0;
        while(amount-- > 0 && sourceOffset < source.length){
            destination[(initialPosition++) + destOffset] = source[sourceOffset++];
        }
    }
    
    /**
     * This will create a file with the given arguments into the current directory
     * Note that extension doesn't require any dots in it
     * It will also clear to 0 any information in the sectors taken by this method.
     * This function will also notice when there is not enough space for the file
     * @param fileSize The size of the file
     * @param extension The extension of the file
     * @param name The name of the file
     * @return The new File created, or null in case of failure
     */
    public FileNode createFile(int fileSize, String extension, String name){
        
        //First I need to check if there's a file with that name and extension
        if (this.fileExists(name, extension)) {
            return null;
        }
        
        //First I need to allocate enough sectors for this file
        int sectorAmount = (int)Math.ceil((double)fileSize/sectorSize);
        FileSector[] sectors = new FileSector[sectorAmount];
        
        //Populate with empty sectors
        for (int i = 0; i < sectors.length; i++) {
            sectors[i] = this.getFirstEmptySector();
            if (sectors[i] == null) {
                //Didn't find available space
                //I need to free previous sectors
                for (int j = 0; j < sectors.length - 1; j++) {
                    this.freeSector(sectors[j]);
                }
                return null;
            }
            
            //I need to hook each sector to its previous and next
            if (i > 0) {
                sectors[i-1].setNextSector(sectors[i]);
                sectors[i].setPreviousSector(sectors[i-1]);
            }
            
            for (int j = 0; j < this.sectorSize; j++) {
                this.diskContents[sectors[i].getSectorPointer() + j] = 0;
            }
        }
        
        //Lastly I can now insert it
        FileNode newFileNode = new FileNode(sectors[0], sectors[sectorAmount-1], fileSize, extension, name, this.currentDirectory);
        this.currentDirectory.addChildren(newFileNode);
        
        //Then update the disk and return
        this.updateDiskContents();
        return newFileNode;
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
     * If the node is null, it will return false
     * @param fileNode The node whose data will be overwritten
     * @param data The data which will be saved on top of the previous one
     * @return True in case data was saved
     */
    public boolean saveData(FileNode fileNode, char[] data){
        
        if (fileNode == null) return false;
        
        FileSector pointer = fileNode.getBegin();
        int counter = 0;
        
        //While I'm missing some sector pointers
        while(pointer != null){
            
            //Move current remaining data into the sector
            DriveManager.copyArrayContents(this.diskContents, pointer.getSectorPointer(), data, counter, this.sectorSize);
            counter += this.sectorSize;
            
            //Proceed to next sector
            pointer = pointer.getNextSector();
        }
        
        //Change its modification date
        fileNode.updateModificationDate();
        
        //Then update the disk and return
        this.updateDiskContents();
        
        return true;
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
    public FileSectorStateEnum isSectorFree(int sector){
        
        //Need this for out of bounds
        if (0 > sector || sector >= this.ocuppiedSectors.length) return FileSectorStateEnum.OUTOFBOUNDS;
        
        //Then this for the actual data
        return this.ocuppiedSectors[sector];
    }

    /**
     * Returns an int representation of the current sector size in this
     * drive manager
     * @return An integer representing the current sector size
     */
    public int getSectorSize() {
        return sectorSize;
    }
    
    /**
     * Adds a byte into the given fileNode, and will add new sectors if there is
     * enough disk space
     * @param fileNode The node whose size will be increased by 1
     * @return True if the addition was succesful
     */
    public boolean addByte(FileNode fileNode){
        //There might be cases where the sector is not completely full, as such
        //I only need to increase the size
        if (fileNode.getSize() % this.sectorSize != 0) {
            fileNode.setSize(fileNode.getSize() + 1);
            return true;
        }
        
        //I need to add one sector to the file
        FileSector newFileSector = this.getFirstEmptySector();
        
        //No free sectors
        if (newFileSector == null) return false;
        
        //Move the sector pointers
        newFileSector.setPreviousSector(fileNode.getEnd());
        fileNode.getEnd().setNextSector(newFileSector);
        fileNode.setEnd(newFileSector);
        
        return true;//Succesful operation
    }
    
    public boolean importFile(File importingFile){
        
        //Supposedly, there's at least a \ right before the file name
        String name = importingFile.getName().substring(importingFile.getName().lastIndexOf('\\') + 1, importingFile.getName().lastIndexOf('.')),
               extension = importingFile.getName().substring(importingFile.getName().lastIndexOf('.') + 1);
        
        //File already exists
        if (this.fileExists(name, extension)) return false;
        
        //Create the fileNode, so I can start adding new bytes later
        FileSector fileSector = this.getFirstEmptySector();
        FileNode fileNode = new FileNode(fileSector, fileSector, 0, extension, name, this.currentDirectory);
        
        
        //Counter will be so I can know if the current sector is full
        //Readchar will store the char until I save it
        int readChar, counter = 0;
        
        try {
            FileReader fr = new FileReader(importingFile);
            
            //I can start reading bytes into this file
            do {
                readChar = fr.read();
                if (readChar == -1) {
                    //Read unsuccesful, I need to check if there are some unnecessary sectors
                    //Unnecessary sectors are those blank sectors when the file has more than 1 sector
                    if (counter == 0 && fileNode.getBegin() != fileNode.getEnd()) {
                        this.freeSector(fileNode.getEnd());
                        fileNode.setEnd(fileNode.getEnd().getPreviousSector());
                    }
                    break;
                }
                
                if (counter == this.sectorSize) {
                    //Sector is full, create and add a new one to the end
                    fileSector = this.getFirstEmptySector();
                    fileSector.setPreviousSector(fileNode.getEnd());
                    fileNode.getEnd().setNextSector(fileSector);
                    fileNode.setEnd(fileSector);
                    counter = 0;
                }
                
                //Get the byte in
                this.diskContents[fileSector.getSectorPointer()+counter] = (char)readChar;
                fileNode.setSize(fileNode.getSize() + 1);
                counter++;
                
            } while (true);
            
        } catch (IOException ex) {
            //Exception on read
            Logger.getLogger(DriveManager.class.getName()).log(Level.SEVERE, null, ex);
            this.removeNode(fileNode);
            return false;
        }
        
        this.currentDirectory.addChildren(fileNode);
        
        //Then update the disk and return
        this.updateDiskContents();
        
        return true;//Operation succesful
    }
    
    /**
     * This method will update the disk file to match the contents of the
     * program
     */
    private void updateDiskContents(){
        //Then write the current contents to it
        try (FileWriter fw = new FileWriter(diskFile)) {
            fw.write(diskContents);
        } catch (IOException ex) {
            Logger.getLogger(DriveManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * This will check if there exists a file with the given name and extension
     * inside the current directory
     * @param name The name of the file
     * @param extension The extension of the file
     * @return True if the file exists
     */
    public boolean fileExists(String name, String extension){
        return this.currentDirectory.getChildren().contains(new FileNode(null, null, 0, extension, name, this.currentDirectory));
    }
    
    /**
     * This will check if there exists a directory with the given name
     * inside the current directory
     * @param name The name of the directory
     * @return True if the directory exists
     */
    public boolean directoryExists(String name){
        return this.currentDirectory.getChildren().contains(new DirectoryNode(this.currentDirectory, name));
    }
    
    /**
     * This will copy the given fileSystemNode in the current directory
     * @param fileSystemNode The node to copy
     * @return True if the node and any possible subnodes were copied succesfully
     */
    public boolean copyFile(FileSystemNode fileSystemNode){
        
        if (fileSystemNode instanceof FileNode) {
            FileNode fileNode = (FileNode) fileSystemNode,
                    newFileNode = this.createFile(fileNode.getSize(), fileNode.getExtension(), fileNode.getName());
            
            return newFileNode != null && this.saveData(newFileNode, this.getData(fileNode));
        }
        
        //It's a directory, I need to keep track of the current directory
        DirectoryNode previousDirectory = this.currentDirectory;
        
        if (fileSystemNode instanceof DirectoryNode) {
            DirectoryNode directoryNode = (DirectoryNode) fileSystemNode;
            
            if (this.makeDirectory(directoryNode.getName())) {
                //The directory was successfully created, change to the subdirectory
                this.changeDirectory(directoryNode.getName());
                
                //Then copy new children
                for (FileSystemNode childFileSystemNode : directoryNode.getChildren()) {
                    if (!this.copyFile(childFileSystemNode)) {
                        //This copy was not successfull, revert all changes
                        this.removeNode(this.currentDirectory);
                    }
                }
                
                //All subnodes copied successfully
                this.currentDirectory = previousDirectory;
                return true;
            }
            
        }
        
        return false;
    }
    
    /**
     * This method will move the given element into the current directory
     * @param fileSystemNode The element to be moved
     * @param newName The new name for the element
     * @return True if the operation was successful, false otherwise
     */
    public boolean moveElement(FileSystemNode fileSystemNode, String newName){
        
        if (fileSystemNode instanceof FileNode) {
            
            //A regex for "name.ext" formats
            if (!newName.matches("\\w(\\w|\\s)*\\.[a-z]{3}")) return false;
            
            String name = newName.substring(0, newName.indexOf('.')),
                   extension = newName.substring(newName.indexOf('.') + 1);
            
            //Test if there already exists a file with this name and extension in here
            if (this.fileExists(name, extension)) return false;
            
            FileNode fileNode = (FileNode) fileSystemNode;
            fileNode.setName(name);
            fileNode.setExtension(extension);
            
            //I'll remove it, and then add it to the target directory
            fileNode.getParent().getChildren().remove(fileNode);
            fileNode.setParent(this.currentDirectory);
            this.currentDirectory.addChildren(fileNode);
            
            return true;
            
        } else if (fileSystemNode instanceof DirectoryNode && !newName.isEmpty() && !this.directoryExists(newName)) {
            DirectoryNode directoryNode = (DirectoryNode) fileSystemNode;
            directoryNode.setName(newName);
            
            //I'll remove it, and then add it to the target directory
            directoryNode.getParent().getChildren().remove(directoryNode);
            directoryNode.setParent(this.currentDirectory);
            this.currentDirectory.addChildren(directoryNode);
            
            return true;
        }
        
        return false;
    }
    
    public ArrayList<FileSystemNode> findFiles(FileSystemNode root, String input){
        ArrayList<FileSystemNode> matchingElements = new ArrayList<>();
        
        if (root.toString().matches(input)) {
            matchingElements.add(root);
        }
        
        if (root instanceof DirectoryNode){
            DirectoryNode directoryNode = (DirectoryNode) root;
            directoryNode.getChildren().forEach(fileSystemNode -> {
                matchingElements.addAll(this.findFiles(fileSystemNode, input));
            });
        }
        
        return matchingElements;
    }
}
