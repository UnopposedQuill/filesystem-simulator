package model;

import java.util.ArrayList;

/**
 * This will take care of representing the directories
 * @author darkl
 */
public class DirectoryNode extends FileSystemNode{

    private DirectoryNode parent;
    
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
        super(name);
        this.children = new ArrayList<>();
        this.parent = parent;
    }

    /**
     * This will output a representation of the directory of this directory node
     * @return A string separated by / representing the route of this directory
     */
    @Override
    public String toString() {
        DirectoryNode ptr = this;
        String directoryRoute = "/";
        while (ptr != null){
            directoryRoute = "/" + ptr.getName() + directoryRoute;
            ptr = ptr.parent;
        }
        return directoryRoute.substring(1);
    }
}
