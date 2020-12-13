
package model;

import java.io.File;
import java.io.IOException;
/**
 *
 * @author darkl
 */
public class DriveManager {
    private final Byte[] diskContents;
    private final int sectorSize;
    private final File diskFile;

    /**
     * Creates a virtual disk, and attaches a drive manager to it.
     * @param diskSize The size of the disk
     * @param sectorSize Amount of bytes for each sector
     * @throws IOException In case it couldn't create the drive file
     */
    public DriveManager(int diskSize, int sectorSize) throws IOException {
        this.diskContents = new Byte[diskSize];
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
    }
    
    public void makeDirectory(){
        
    }
    
    public void createFile(){
        
    }
}
