package data;

public class Tree {

    public Node root;

    public Tree() {
        root = null;
    }

    public Tree(Node root) {
        this.root = root;
    }


    @Override
    public String toString() {
        return root.toString();
    }

}
