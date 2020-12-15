
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
    private int begin, end;
    
    /**
     * The current amount of characters that belong to said file
     */
    private int size;
    
    /**
     * The extension related to said file
     */
    private final String extension;

    public FileNode(int begin, int end, int size, String extension, String name, DirectoryNode parent) {
        super(parent, name);
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
