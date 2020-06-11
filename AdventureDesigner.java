import java.util.InputMismatchException;
import java.util.Scanner;
class FullTreeException extends Exception{}
class NoSuchNodeException extends Exception{}

public class AdventureDesigner extends SceneTree{
    public static SceneTree sceneTree = new SceneTree();

    /**
     * allows the user to play the game designed in the main and ends when the user reaches a leaf node
     */
    public static void playGame(){
        SceneNode tempCursor = sceneTree.cursor;
        sceneTree.cursor = sceneTree.root;
        System.out.println("Now beginning game...\n");
        while(!sceneTree.cursor.isEnding()){
           sceneTree.cursor.displayScene();
           System.out.println("A)"+sceneTree.cursor.getLeft().getTitle());
           if(sceneTree.cursor.getMiddle()!=null)
               System.out.println("B)"+sceneTree.cursor.getMiddle().getTitle());
           if(sceneTree.cursor.getRight()!=null)
                System.out.println("C)"+sceneTree.cursor.getRight().getTitle());
           Scanner in = new Scanner(System.in);
           System.out.println("Please enter an option:");
            String option = in.next().toLowerCase();
           sceneTree.moveCursorForwards(option);
        }
        sceneTree.cursor.displayScene();
        sceneTree.cursor = tempCursor;
        System.out.println("\nThe End\n\n"+"Returning back to creation mode...");



    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Creating a story...");
        System.out.println("Please enter a title: ");
        String rootTitle = in.nextLine();
        System.out.println("Please enter a scene");
        String scene = in.nextLine();
        sceneTree.root = new SceneNode(rootTitle,scene,1);
        sceneTree.cursor = sceneTree.root;
        System.out.println("Scene #1 added");
        String menu =
                        "\nA) Add Scene\n"+
                        "R) Remove Scene\n"+
                        "S) Show Current Scene\n"+
                        "P) Print Adventure Tree\n"+
                        "B) Go Back A Scene\n"+
                        "F) Go Forward A Scene\n"+
                        "G) Play Game\n"+
                        "N) Print Path To Cursor\n"+
                        "M) Move Scene\n"+
                        "Q) Quit";

        while(!in.equals("q")) {
            System.out.println(menu + "\n\n" + "Enter a selection:");
            String selection = in.next().toLowerCase();
            if (selection.equals("a")) {
                try {
                        System.out.println("Please enter a title:");
                        in.nextLine();
                        String title = in.nextLine();
                        System.out.println("Please enter a scene:");
                        String desc = in.nextLine();
                        if (sceneTree.cursor.isFull())
                            throw new FullTreeException();
                        sceneTree.addNewNode(title, desc);
                        System.out.println("Scene #"+ getNumScenes() +" added.");
                } catch (FullTreeException ex) {
                    System.out.println("You cannot add another scene!");
                }

            } else if (selection.equals("r")) {
                try {
                    if (sceneTree.cursor.isEnding())
                        throw new NoSuchNodeException();
                    else {
                        System.out.println("Which option do you wish to remove to:");
                        String option = in.next().toLowerCase();
                        String sceneTitle="";
                        if (option.equals("a")) {
                            sceneTitle=sceneTree.cursor.getLeft().getTitle();
                            if (sceneTree.cursor.getLeft() == null)
                                throw new NoSuchNodeException();
                        } else if (option.equals("b")) {
                            sceneTitle=sceneTree.cursor.getMiddle().getTitle();
                            if (sceneTree.cursor.getMiddle() == null)
                                throw new NoSuchNodeException();
                        } else if (option.equals("c")) {
                            sceneTitle=sceneTree.cursor.getRight().getTitle();
                            if (sceneTree.cursor.getRight() == null)
                                throw new NoSuchNodeException();
                        }
                        else
                            throw new InputMismatchException();
                        sceneTree.removeScene(option);
                        System.out.println(sceneTitle+" removed.");
                    }

                } catch (NoSuchNodeException ex) {
                    System.out.println("That option does not exist.");
                }
                catch (InputMismatchException ex){
                    System.out.println("Make sure input is a,b, or c. ");
                }

            } else if (selection.equals("s")) {
                sceneTree.cursor.displayFullScene();
            } else if (selection.equals("p")) {
                System.out.println(sceneTree.toString());
            } else if (selection.equals("b")) {
                try {
                    if (sceneTree.cursor == sceneTree.root)
                        throw new NoSuchNodeException();
                    sceneTree.moveCursorBackwards();
                    System.out.println("Successfully moved back to " + sceneTree.cursor.getTitle());
                } catch (NoSuchNodeException ex) {
                    System.out.println("That option does not exist");
                }
            } else if (selection.equals("f")) {
                try {
                    if (sceneTree.cursor.isEnding())
                        throw new NoSuchNodeException();
                    else {
                        System.out.println("Which option do you wish to go to:");
                        String option = in.next().toLowerCase();
                        if (option.equals("a")) {
                            if (sceneTree.cursor.getLeft() == null)
                                throw new NoSuchNodeException();
                        } else if (option.equals("b")) {
                            if (sceneTree.cursor.getMiddle() == null)
                                throw new NoSuchNodeException();
                        } else if (option.equals("c")) {
                            if (sceneTree.cursor.getRight() == null)
                                throw new NoSuchNodeException();
                        } else
                            throw new InputMismatchException();
                        sceneTree.moveCursorForwards(option);
                        System.out.println("Successfully moved to " + sceneTree.cursor.getTitle());
                    }

                } catch (NoSuchNodeException ex) {
                    System.out.println("That option does not exist.");
                } catch (InputMismatchException ex){
                    System.out.println("Make sure input is a,b, or c. ");
            }

            } else if (selection.equals("g")) {
                playGame();
            } else if (selection.equals("n")) {
                System.out.println(sceneTree.getPathFromRoot());
            } else if (selection.equals("m")) {
                try {
                    System.out.println("Move current scene to: ");
                    int move = in.nextInt();
                    if (move > getNumScenes() || move < 1)
                        throw new NoSuchNodeException();
                    if(sceneTree.findNode(move, sceneTree.root).isFull())
                        throw new FullTreeException();
                    else {
                        sceneTree.moveScene(move);
                        System.out.println("Successfully moved scene.");
                    }
                }catch (NoSuchNodeException ex){
                    System.out.println("A node with such a sceneID does not exist. ");
                }catch (FullTreeException ex){
                    System.out.println("This path has more than three options. ");}


            } else if (selection.equals("q")) {
                System.out.println("Program terminating normally...");
                System.exit(0);
            } else {
                System.out.println("Invalid Input");
            }


        }
        
    }
}
