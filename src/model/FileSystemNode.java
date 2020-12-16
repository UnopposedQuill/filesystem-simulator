
package model;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * This class will take care of storing the complete file system tree
 * Note that only directories may point to subsequent directories
 * @author darkl
 */
public abstract class FileSystemNode {
    
    /**
     * Name of the node, this will be the name of file, or the directory depending
     * on the subclass
     */
    private String name;
    
    /**
     * Both initial creation and then last modification date
     * First cannot change
     */
    private final Date creationDate;
    private Date modificationDate;
    
    /**
     * Parent node to this element
     */
    protected DirectoryNode parent;

    public FileSystemNode(DirectoryNode parent, String name) {
        this.name = name;
        this.creationDate = this.modificationDate = GregorianCalendar.getInstance().getTime();
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void updateModificationDate() {
        this.modificationDate = GregorianCalendar.getInstance().getTime();
    }

    public Date getCreationDate() {
        return creationDate;
    }
    
    public DirectoryNode getParent() {
        return this.parent;
    }
    
    /**
     * Every subclass is required to implement this method in
     * order to add the node into the tree itself
     * @return A string representing the node
     */
    @Override
    public abstract String toString();
    
    public abstract int getSize();
}
