package data;

public class Tree {

    /**
     * The root node of the current tree.
     */
    public Node root;

    /**
     * Construct an empty tree.
     */
    public Tree() {
        root = null;
    }

    /**
     * Construct a tree with a root node
     * @param root a root node
     */
    public Tree(Node root) {
        this.root = root;
    }


    @Override
    public String toString() {
        return root.toString();
    }

}
