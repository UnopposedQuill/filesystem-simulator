
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
    
    private final Date creationDate;
    private Date modificationDate;

    public FileSystemNode(String name) {
        this.name = name;
        this.creationDate = this.modificationDate = GregorianCalendar.getInstance().getTime();
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

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    public Date getCreationDate() {
        return creationDate;
    }
    
    
}
