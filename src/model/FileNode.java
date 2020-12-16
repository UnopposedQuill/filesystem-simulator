
package model;

import java.util.Objects;

/**
 * This class will take care of containing the data for 
 * @author darkl
 */
public class FileNode extends FileSystemNode{
    
    /**
     * Pointers to the initial sector and the finalizing sector
     */
    private FileSector beginFileSector, endFileSector;
    //private int begin, end;
    
    /**
     * The current amount of characters that belong to said file
     */
    private int size;
    
    /**
     * The extension related to said file
     */
    private final String extension;

    public FileNode(FileSector beginFileSector, FileSector endFileSector, int size, String extension, String name, DirectoryNode parent) {
        super(parent, name);
        this.size = size;
        this.extension = extension;
        this.beginFileSector = beginFileSector;
        this.endFileSector = endFileSector;
    }

    public FileSector getBegin() {
        return beginFileSector;
    }

    public void setBegin(FileSector begin) {
        this.beginFileSector = begin;
    }

    public FileSector getEnd() {
        return endFileSector;
    }

    public void setEnd(FileSector end) {
        this.endFileSector = end;
    }

    @Override
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getExtension() {
        return extension;
    }
    
    /**
     * This will output a representation of the directory of this directory node
     * @return A string separated by / representing the route of this directory
     */
    public String getRoute() {
        DirectoryNode ptr = this.parent;
        String directoryRoute = "/" + this.getName() + "." + this.extension;
        while (ptr != null){
            directoryRoute = "/" + ptr.getName() + directoryRoute;
            ptr = ptr.parent;
        }
        return directoryRoute;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.getRoute());
        return hash;
    }

    /**
     * Will evaluate if two objects are equal. Two elements will be equal
     * should their route be the same
     * @param obj Object to which this will be compared
     * @return True if the object is equal to this
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DirectoryNode other = (DirectoryNode) obj;
        return Objects.equals(this.getRoute(), other.getRoute());
    }

    @Override
    public String toString() {
        return this.getName() + "." + this.extension;
    }
}
