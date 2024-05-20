/*
Tarun Vaidhyanathan
115510562
R02
 */
import java.util.*;
/**
 * FXGuiMaker is a command-line tool that emulates a simple GUI builder.
 * Users can load, manipulate, and save JavaFX GUI components represented in a tree structure.
 */
public class FXGuiMaker {
    /**
     * Main method to run the FXGuiMaker tool.
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Initialize an empty FXComponentTree
        FXComponentTree tree = new FXComponentTree();

        // Print welcome message and menu options
        System.out.println("Welcome to counterfeit SceneBuilder.");
        System.out.println("Menu:");
        System.out.println("\tL) Load from file");
        System.out.println("\tP) Print tree");
        System.out.println("\tC) Move cursor to a child node");
        System.out.println("\tR) Move cursor to root");
        System.out.println("\tA) Add a child");
        System.out.println("\tU) Cursor up (to parent)");
        System.out.println("\tE) Edit text of cursor");
        System.out.println("\tD) Delete child");
        System.out.println("\tS) Save to file");
        System.out.println("\tX) Export FXML //Works the same as save, extra credit");
        System.out.println("\tQ) Quit");

        // Initialize Scanner to take user input
        Scanner input = new Scanner(System.in);

        // Main loop to handle user commands
        while(true){
            System.out.print("Please select an option: ");
            String choice = input.nextLine().trim().toUpperCase();
            switch (choice){
                case "L":
                    System.out.print("Please enter filename: ");
                    tree = FXComponentTree.readFromFile(input.nextLine());
                    break;
                case "P":
                    tree.print(tree.getRoot());
                    break;
                case "C":
                    System.out.print("Please enter number of child (starting with 1): ");
                    int index = input.nextInt();
                    input.nextLine();
                    if(tree.getCursor().getChildren()[index-1] != null){
                        tree.setCursor(tree.getCursor().getChildren()[index-1]);
                        System.out.print("Cursor moved to " + tree.getCursor().getType());
                        if(tree.getCursor().getType().equals(ComponentType.Button) || tree.getCursor().getType().equals(ComponentType.Label) || tree.getCursor().getType().equals(ComponentType.TextArea)){
                            System.out.print(": " + tree.getCursor().getText());
                        }
                        System.out.println();
                    }
                    else{
                        System.out.println("Cannot move cursor as no child exists at that index");
                    }
                    break;
                case "R":
                    tree.setCursor(tree.getRoot());
                    System.out.println("Cursor is at root.");
                    break;
                case "A":
                    System.out.print("Select component type (H - HBox, V - VBox, T - TextArea, B - Button, L - Label): ");
                    String selection = input.nextLine().toUpperCase();
                    ComponentType type = null;
                    String text = null;
                    switch(selection){
                        case "H":
                            type = ComponentType.HBox;
                            System.out.print("Please enter text: ");
                            text = input.nextLine();
                            break;
                        case "V":
                            type = ComponentType.VBox;
                            System.out.print("Please enter text: ");
                            text = input.nextLine();
                            break;
                        case "T":
                            type = ComponentType.TextArea;
                            System.out.print("Please enter text: ");
                            text = input.nextLine();
                            break;
                        case "B":
                            type = ComponentType.Button;
                            System.out.print("Please enter text: ");
                            text = input.nextLine();
                            break;
                        case "L":
                            type = ComponentType.Label;
                            System.out.print("Please enter text: ");
                            text = input.nextLine();
                            break;
                    }
                    FXTreeNode newNode = new FXTreeNode(type);
                    newNode.setText(text);
                    newNode.setParent(tree.getCursor().getParent());
                    System.out.print("Please enter an index: ");
                    int index2 = input.nextInt();
                    input.nextLine();
                    if(tree.getCursor().getChildren()[index2] != null){
                        input.nextLine();
                        tree.addChild(index2, newNode);
                        System.out.println("added.");
                    }
                    else{
                        System.out.println("Invalid");
                    }
                    break;
                case "U":
                    tree.setCursor(tree.getCursor().getParent());
                    System.out.println("Cursor Moved to " + tree.getCursor().getType());
                    break;
                case "E":
                    System.out.print("Please enter new text: ");
                    if(tree.getCursor().getType().equals(ComponentType.Button) || tree.getCursor().getType().equals(ComponentType.Label) || tree.getCursor().getType().equals(ComponentType.TextArea)){
                        String text2 = input.nextLine();
                        tree.getCursor().setText(text2);
                        System.out.println("Text Edited.");
                    }
                    else{
                        System.out.println("Cannot edit text");
                    }
                    break;
                case "D":
                    System.out.print("Please enter number of child (starting with 1): ");
                    int index3 = input.nextInt();
                    if(tree.getCursor().getChildren()[index3-1] != null){
                        ComponentType type2 = tree.getCursor().getChildren()[index3-1].getType();
                        tree.deleteChild(index3-1);
                        System.out.println(type2 + " removed.");
                    }
                    else {
                        System.out.println("IndexOutOfBounds");
                    }
                    break;
                case "S":
                    System.out.println("Please enter file name: ");
                    String fileName = input.nextLine();
                    FXComponentTree.writeToFile(tree, fileName);
                    System.out.println(fileName + " saved to computer.");
                    break;
                case "X":
                    break;
                case "Q":
                    System.out.println("Make like a tree and leave!");
                    System.exit(0);
                    break;

            }
        }
    }
}
