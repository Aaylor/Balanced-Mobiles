public class Tree {

    public Node root;
    public int  weight;
    public int  balance;

    public Tree() {
        root    = null;
        weight  = 0;
        balance = 0;
    }

    public Tree(Node root) {
        this.root = root;
        /* FIXME: calculate the weight and balance */
    }

}
