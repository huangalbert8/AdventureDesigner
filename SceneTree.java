/**
 * @author Albert Huang ID#:112786492 Rec:R01
 * This class represents the scenetree which has the cursor and the root of the tree
 * */
public class SceneTree extends SceneNode{
    SceneNode root, cursor;

    public SceneTree(){
    }

    /**
     * uses overloaded moveCursorBackwards method to move cursor to parent
     */
    public void moveCursorBackwards(){
        moveCursorBackwards(root);
    }
    /**
     * moves the cursor to the parent node
     * @param parent the root of the tree as a parameter because the
     *
     */
    public void moveCursorBackwards (SceneNode parent){
        if(cursor.equals(root))
            return;
        else if(parent==null)
            return;
        SceneNode left = parent.getLeft();
        SceneNode middle = parent.getMiddle();
        SceneNode right = parent.getRight();
        if((left==cursor||middle==cursor||right==cursor)) {
            cursor = parent;
        }
            moveCursorBackwards(left);
            moveCursorBackwards(middle);
            moveCursorBackwards(right);

    }

    /**
     * moves the cursor forward
     * @param option cursor can either go left, middle, or right, option decides which one it is
     */
    public void moveCursorForwards(String option){
        if(option.equals("a"))
            cursor=cursor.getLeft();
        else if(option.equals("b"))
            cursor=cursor.getMiddle();
        else if(option.equals("c"))
            cursor=cursor.getRight();
    }

    /**
     * adds a new node where the parent is the cursor
     * @param title the title of the new node
     * @param sceneDescription the description of the new node
     */
    public void addNewNode(String title, String sceneDescription){
        SceneNode newNode = new SceneNode(title,sceneDescription,getNumScenes()+1);
        if(root==cursor){
            cursor.addScene(newNode);}
        else {
            moveCursorBackwards(cursor);
            if (cursor.getRight() == null) {
                cursor.addScene(newNode);
            }
        }
        setNumScenes(getNumScenes()+1);
    }

    /**
     * removes the scene node of one of the cursors child
     * @param option which node to be deleted of the cursor's child
     */
    public void removeScene(String option){
        if(cursor.isEnding())
            return;
        else
            if(option.equals("a")) {
                if(cursor.getMiddle()!=null) {
                    cursor.setLeft(cursor.getMiddle());
                    cursor.setMiddle(null);

                    if (cursor.getRight() != null) {
                        cursor.setMiddle(cursor.getRight());
                        cursor.setRight(null);
                    }
                } else
                    cursor.setLeft(null);
            }
            else if(option.equals("b")){
                if(cursor.getRight()!=null){
                cursor.setMiddle(cursor.getRight());
                cursor.setRight(null);}
                else
                    cursor.setMiddle(null);
            }
            else if(option.equals("c"))
                cursor.setRight(null);

    }

    /**
     * Finds a node depending on the sceneID to be used in the moveScene method
     * @param sceneId the node who's sceneID is to be found
     * @param x the root node because the method is recursive
     * @return the node that is found or null if doesn't exist
     */
    public SceneNode findNode(int sceneId, SceneNode x){
        if(sceneId>getNumScenes()||sceneId<1)
            return null;
        if (x != null){
        if (x.getSceneID() == sceneId)
            return x;
        SceneNode node = findNode(sceneId, x.getLeft());
        if(node==null)
        node = findNode(sceneId, x.getMiddle());
        if (node==null)
        node = findNode(sceneId, x.getRight());
        return node;
    }
        return null;
    }

    /**
     * using the findNode helper method it moves cursor to the node with the sceneID in the parameter
     * @param sceneIDToMoveTo the node with the sceneID to be moved to
     */
    public void moveScene(int sceneIDToMoveTo){
        SceneNode tempCursor = cursor;
            moveCursorBackwards(root);
            if (cursor.getLeft() == tempCursor)
                removeScene("a");
            else if (cursor.getMiddle() == tempCursor)
                removeScene("b");
            else if (cursor.getRight() == tempCursor)
                removeScene("c");
            cursor = findNode(sceneIDToMoveTo, root);
            cursor.addScene(tempCursor);
    }

    /**
     * finds the path from the cursor to the root
     * @return the path from the cursor to the root
     */
    public String getPathFromRoot(){
        String s1,s2="";
        SceneNode tempCursor = cursor;
        if(cursor==root)
            s2=root.getTitle();
        else if(root.getLeft()==cursor||root.getMiddle()==cursor||root.getRight()==cursor) {
            s2 = root.getTitle()+", "+cursor.getTitle();
        }
        else{
        while(cursor!=root){
            s1=cursor.getTitle();
            if(s2=="")
                s2 = s1;
            else
            s2=s1+", " +s2;
            moveCursorBackwards(root);
        }
        s2=cursor.getTitle()+", "+s2;
        cursor=tempCursor;}
        return s2;
    }

    /**
     * returns the scenetree by calling the overloads toString method
     * @return scenetree
     */
    public String toString(){
        return toString(0,root);
    }

    /**
     * the overloaded toString method
     * @param num number of indents needed
     * @param x the root of the tree
     * @return string of scenetree
     */
    public String toString( int num, SceneNode x){
        String s ="";
        String indent ="\t";
        for(int i = 0; i<num; i++){
            indent += "\t";
        }
        if(x==cursor)
        s+=x.getTitle()+"(#"+x.getSceneID()+")*\n";
        else
            s+=x.getTitle()+"(#"+x.getSceneID()+")\n";
        if(x.getLeft()!=null)
            s+=indent+"A)"+toString(num+1,x.getLeft());
        if(x.getMiddle()!=null)
           s+= indent+"B)"+toString(num+1,x.getMiddle());
        if(x.getRight()!=null)
            s+=indent+"C)"+toString(num+1,x.getRight());

    return s;
    }

}
