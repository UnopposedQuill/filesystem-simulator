
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
        this.rootNode = new DirectoryNode(null, "");
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
        if (route.startsWith("/")) {
            //Absolute route
            this.currentDirectory = this.rootNode;
            route = route.substring(1);//Remove the starting '/'
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
    
    public void createFile(){
        
    }
}
