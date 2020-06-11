/**
 * @author Albert Huang ID#:112786492 Rec:R01
 * This class represents the Scenenode which has the title, scene description, sceneID, the left, middle, and right child nodes and the number of scenes in total
 * */
public class SceneNode {
    private String title, sceneDescription;
    private int sceneID;
    private SceneNode left, right, middle;
    private static int numScenes=1;

    public SceneNode(){
    }
    public SceneNode(String title,String sceneDescription, int sceneID){
        this.title = title;
        this.sceneDescription = sceneDescription;
        this.sceneID=sceneID;
    }
    public void setLeft(SceneNode left){
        this.left = left;
    }
    public void setMiddle(SceneNode middle){
        this.middle = middle;
    }
    public void setRight(SceneNode right){
        this.right = right;
    }
    public SceneNode getLeft(){
        return left;
    }
    public SceneNode getMiddle(){
        return middle;
    }
    public SceneNode getRight(){
        return right;
    }
    public int getSceneID(){
        return sceneID;
    }
    public String getTitle(){
        return title;
    }
    public String getSceneDescription(){
        return sceneDescription;
    }
    public static int getNumScenes() {
        return numScenes;
    }
    public static void setNumScenes(int num){
        numScenes = num;
    }

    /**
     * adds a scene to the leftmost available child node
     * @param scene the scenenode to be added
     */
    public void addScene(SceneNode scene){
        if((left==null)){
            setLeft(scene);}
        else if((middle==null))
            setMiddle(scene);
        else if((right==null))
            setRight(scene);

    }

    /**
     * determines if the node is a leaf
     * @return true if is a leaf, false if not
     */
    public boolean isEnding(){
        if(left==null&&middle==null&&right==null)
            return true;
        return false;

    }

    /**
     * determines if child nodes are full
     * @return true if is full, else if not full
     */
    public boolean isFull() {
        if (left != null && middle != null && right != null) {
            return true;
        }
        return false;
    }

    /**
     * displays the title and description to be used in the playGame method
     */
    public void displayScene(){
        System.out.println(title+"\n"+sceneDescription+"\n");
    }

    /**
     * displays an overview of the node
     */
    public void displayFullScene(){
        System.out.println("Scene ID #"+sceneID);
        System.out.println("Title: "+title);
        System.out.println("Scene: "+sceneDescription);
        String leadTo = "";
        if(isEnding())
            leadTo="NONE";
        else {
            if(left!=null)
            leadTo+="'"+left.getTitle()+"' (#"+ left.getSceneID()+ ")";
            if(middle!=null)
                leadTo+=", '"+middle.getTitle()+"' (#"+ middle.getSceneID()+ ")";
            else if(right!=null)
                leadTo+=", '"+right.getTitle()+"' (#"+ right.getSceneID()+ ")";
            if(leadTo.substring(0,2).equals(", "))
                leadTo=leadTo.substring(2,leadTo.length());
        }

        System.out.println("Leads to: " + leadTo);
    }

    /**
     * prints the title and scene ID
     * @return title and scene ID of node
     */
    public String toString(){
        String s = title + "(#"+sceneID+")";
        return s;
    }



}
