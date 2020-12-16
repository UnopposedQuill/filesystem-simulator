package model;

import java.util.ArrayList;
import java.util.Objects;

/**
 * This will take care of representing the directories
 * @author darkl
 */
public class DirectoryNode extends FileSystemNode{
    
    /**
     * This is so each can point to extra ones
     */
    private final ArrayList<FileSystemNode> children;

    public ArrayList<FileSystemNode> getChildren() {
        return children;
    }
    
    public <T extends FileSystemNode> boolean addChildren(T child){
        return this.children.add(child);
    }

    public DirectoryNode(DirectoryNode parent, String name) {
        super(parent, name);
        this.children = new ArrayList<>();
    }

    /**
     * This will output a representation of the directory of this directory node
     * @return A string separated by / representing the route of this directory
     */
    public String getRoute() {
        DirectoryNode ptr = this;
        String directoryRoute = "/";
        while (ptr != null){
            directoryRoute = "/" + ptr.getName() + directoryRoute;
            ptr = ptr.parent;
        }
        return directoryRoute;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.getRoute());
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
        return this.getName();
    }
    
    /**
     * Will return the sum of each of its subchildren
     * @return An int representing the amount of bytes occupied by its children
     */
    @Override
    public int getSize(){
        return this.children.stream().mapToInt( (t) -> {
            return t.getSize();
        }).sum();
    }
}
