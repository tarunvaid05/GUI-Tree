/*
Tarun Vaidhyanathan
115510562
R02
 */
import java.io.File;
import java.util.*;
/**
 * Represents a tree structure for building JavaFX GUI components.
 */
public class FXComponentTree {
    /** Root node of the tree */
    private FXTreeNode root;
    /** Cursor node of the tree */
    private FXTreeNode cursor;
    /**
     * Constructs a new FXComponentTree with a root node of type AnchorPane.
     */
    public FXComponentTree(){
        FXTreeNode node = new FXTreeNode(ComponentType.AnchorPane);
        node.setParent(null);
        node.setText(null);
        cursor = node;
        root = node;
    }
    /**
     * Gets the cursor node.
     * @return The cursor node.
     */
    public FXTreeNode getCursor(){
        return this.cursor;
    }
    /**
     * Gets the root node.
     * @return The root node.
     */
    public FXTreeNode getRoot() {
        return this.root;
    }
    /**
     * Sets the cursor node.
     * @param cursor The cursor node to be set.
     */
    public void setCursor(FXTreeNode cursor) {
        this.cursor = cursor;
    }
    /**
     * Sets the root node.
     * @param root The root node to be set.
     */
    public void setRoot(FXTreeNode root) {
        this.root = root;
    }
    /**
     * Moves the cursor to the root node.
     */
    public void cursorToRoot(){
        this.cursor = this.root;
    }
    /**
     * Deletes a child node at the specified index.
     * @param index The index of the child node to be deleted.
     */
    public void deleteChild(int index){
        FXTreeNode[] temp = new FXTreeNode[this.cursor.getChildren().length - 1];
        for(int i = 0; i < this.cursor.getChildren().length; i++){
            int j = 0;
            if(i != index){
                temp[j] = this.cursor.getChildren()[i];
                j++;
            }
        }
        this.cursor.setChildren(temp);
    }
    /**
     * Adds a new child node at the specified index.
     * @param index The index at which the new child node will be added.
     * @param node The node to be added as a child.
     */
    public void addChild(int index, FXTreeNode node){
        FXTreeNode[] temp = new FXTreeNode[this.cursor.getChildren().length + 1];
        for(int i = 0; i < index - 1; i ++){
            temp[i] = this.cursor.getChildren()[i];
        }
        temp[index - 1] = node;
        for(int i = index - 1; i < this.cursor.getChildren().length; i++){
            temp[i+1] = this.getCursor().getChildren()[i];
        }
        this.cursor.setChildren(temp);
    }
    /**
     * Sets the text of the node at the cursor position.
     * @param text The text to be set.
     */
    public void setTextAtCursor(String text){
        this.cursor.setText(text);
    }
    /**
     * Moves the cursor to the child node at the specified index.
     * @param index The index of the child node to move to.
     */
    public void cursorToChild(int index){
        this.cursor = this.cursor.getChildren()[index - 1];
    }
    /**
     * Moves the cursor to the parent node.
     */
    public void cursorToParent(){
        this.cursor = this.cursor.getParent();
    }
    /**
     * Reads GUI components from a file and constructs a tree structure accordingly.
     * @param filename The name of the file to read from.
     * @return The constructed FXComponentTree.
     */
    public static FXComponentTree readFromFile(String filename){
        FXComponentTree tree = new FXComponentTree();
        try{
            File file = new File(filename);
            Scanner input = new Scanner(file);
            input.nextLine();
            while (input.hasNextLine()) {
                String currentLine = input.nextLine();
                FXTreeNode parent = tree.root;

                if (!currentLine.isEmpty()) {
                    ComponentType type = null;
                    if (currentLine.contains("VBox")) {
                        type = ComponentType.VBox;
                    } else if (currentLine.contains("HBox")) {
                        type = ComponentType.HBox;
                    } else if (currentLine.contains("Button")) {
                        type = ComponentType.Button;
                    } else if (currentLine.contains("Label")) {
                        type = ComponentType.Label;
                    } else if (currentLine.contains("TextArea")) {
                        type = ComponentType.TextArea;
                    }

                    String position = currentLine.substring(0, currentLine.indexOf(" "));
                    String[] positionArray = position.split("-");
                    for (int i = 1; i < positionArray.length; i++) {
                        int index = Integer.parseInt(positionArray[i]);
                        if (parent.getChildren()[index] == null) {
                            parent.getChildren()[index] = new FXTreeNode(type);
                            parent.getChildren()[index].setParent(parent);
                        }
                        parent = parent.getChildren()[index];
                    }

                    parent.setType(type);
                    if (type != ComponentType.VBox && type != ComponentType.HBox) {
                        parent.setText(currentLine.substring(currentLine.indexOf(" ", currentLine.indexOf(" ") +1)));
                    }
                }
            }
            System.out.println(filename + " loaded");
        }

        catch(Exception ex){
            System.out.println(filename + " not found.");
        }
        return tree;
    }
    /**
     * Writes the contents of the FXComponentTree to a file.
     * @param tree The FXComponentTree to be written to the file.
     * @param filename The name of the file to write to.
     */
    public static void writeToFile(FXComponentTree tree, String filename){

    }
    public static void exportToFXML(FXComponentTree tree, String filename){
    }
    /**
     * Prints the tree structure starting from the given node.
     * @param currentNode The node from which to start printing.
     */
    public void print(FXTreeNode currentNode) {
        if(currentNode.equals(this.root)){
            if(this.root.equals(cursor)){
                System.out.print("==>");
            }
            System.out.println(currentNode.toString());
        }

        FXTreeNode[] children = currentNode.getChildren();
        for(int i = 0; i < children.length; i++){
            if(children[i] != null){
                FXTreeNode temp = children[i];
                int space = 0;
                while(temp != null){
                    if(temp.getParent() != null){
                        temp = temp.getParent();
                        space++;
                    }
                    else{
                        break;
                    }
                }
                if(cursor.equals(children[i])){
                    System.out.print("==>");
                }
                for(int j = 1; j <= space;j++){
                    System.out.print("  ");
                }
                System.out.println(children[i].toString());
                print(children[i]);
            }else{
                break;
            }
        }
    }
}
