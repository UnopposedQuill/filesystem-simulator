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
    private FileSector nextSector;

    public FileSector(int sectorPointer, FileSector nextSector) {
        this.sectorPointer = sectorPointer;
        this.nextSector = nextSector;
    }

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
}
