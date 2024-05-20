
/*
Tarun Vaidhyanathan
115510562
R02
 */
/**
 * Represents a node in the FXComponentTree.
 */
public class FXTreeNode {
    /** Text the node holds */
    private String text;
    /** Component type of the node */
    private ComponentType type;
    /** Parent of the node */
    private FXTreeNode parent;
    /** PAn array of children of the node */
    private FXTreeNode[] children;
    /** Max number of chidren a node can have*/
    final int maxChildren = 10;
    /**
     * Constructs a new FXTreeNode with the specified type.
     * @param type The type of the node.
     */
    public FXTreeNode(ComponentType type){
        this.children = new FXTreeNode[maxChildren];
        this.text = null;
        this.type = type;
    }
    /**
     * Gets the text associated with the node.
     * @return The text associated with the node.
     */
    public String getText(){
        return this.text;
    }
    /**
     * Sets the text associated with the node.
     * @param text The text to be set.
     */
    public void setText(String text){
        this.text = text;
    }
    /**
     * Gets the type of the node.
     * @return The type of the node.
     */
    public ComponentType getType(){
        return this.type;
    }
    /**
     * Sets the type of the node.
     * @param type The type to be set.
     */
    public void setType(ComponentType type){
        this.type = type;
    }
    /**
     * Gets the parent node.
     * @return The parent node.
     */
    public FXTreeNode getParent(){
        return this.parent;
    }
    /**
     * Sets the parent node.
     * @param parent The parent node to be set.
     */
    public void setParent(FXTreeNode parent){
        this.parent = parent;
    }
    /**
     * Gets the children nodes.
     * @return The children nodes.
     */
    public FXTreeNode[] getChildren(){
        return this.children;
    }
    /**
     * Sets the children nodes.
     * @param children The children nodes to be set.
     */
    public void setChildren(FXTreeNode[] children){
        this.children = children;
    }
    /**
     * Generates a string representation of the node.
     * @return The string representation of the node.
     */
    @Override
    public String toString(){
        if(this.getType().equals(ComponentType.AnchorPane)){
            return this.type.toString();
        }
        if(this.getText() == null){
            return "+--" + this.type;
        }
        else{
            return "+--" + this.type + ": " + this.text.trim();
        }
    }
}