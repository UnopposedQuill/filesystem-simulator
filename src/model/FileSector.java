package model;

/**
 *
 * @author darkl
 */
public class FileSector {
    
    //The sector size in the drive manager constructor will decide the amount
    //of chars read by each sector
    private int sectorPointer;
    
    //So I can link each sector together
    private FileSector nextSector, previousSector;

    public FileSector(int sectorPointer, FileSector nextSector, FileSector previousSector) {
        this.sectorPointer = sectorPointer;
        this.nextSector = nextSector;
        this.previousSector = previousSector;
    }
    
    public FileSector(int sectorPointer) {
        this.sectorPointer = sectorPointer;
        this.nextSector = this.previousSector = null;
    }

    /**
     * Returns a pointer to the data corresponding to this sector
     * @return An integer representing the location of the data of the sector
     */
    public int getSectorPointer() {
        return sectorPointer;
    }

    public void setSectorPointer(int sectorPointer) {
        this.sectorPointer = sectorPointer;
    }

    public FileSector getNextSector() {
        return nextSector;
    }

    public void setNextSector(FileSector nextSector) {
        this.nextSector = nextSector;
    }

    public FileSector getPreviousSector() {
        return previousSector;
    }

    public void setPreviousSector(FileSector previousSector) {
        this.previousSector = previousSector;
    }
    
    
}
