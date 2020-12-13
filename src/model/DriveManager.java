
package model;

import java.util.ArrayList;

/**
 *
 * @author darkl
 */
public class DriveManager {
    private ArrayList<Byte> diskContents;
    private int sectorSize;

    public DriveManager(ArrayList<Byte> diskContents, int sectorSize) {
        this.diskContents = diskContents;
        this.sectorSize = sectorSize;
    }
    
    public void makeDirectory(){
        
    }
    
    public void createFile(){
        
    }
}
