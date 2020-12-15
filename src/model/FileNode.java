
package model;

/**
 * This class will take care of containing the data for 
 * @author darkl
 */
public class FileNode extends FileSystemNode{
    
    /**
     * Pointers to the initial sector and the finalizing sector
     */
    private int begin, end;
    
    /**
     * The current amount of characters that belong to said file
     */
    private int size;
    
    /**
     * The extension related to said file
     */
    private final String extension;

    public FileNode(int begin, int end, int size, String extension, String name) {
        super(name);
        this.begin = begin;
        this.end = end;
        this.size = size;
        this.extension = extension;
    }

    public int getBegin() {
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getExtension() {
        return extension;
    }
}
