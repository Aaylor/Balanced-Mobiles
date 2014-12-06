package data;

public class Leaf extends Node {

    public Leaf() {
        super();
    }

    public Leaf(int weight) {
        super(null, null, weight, 0, 0);
    }

    @Override
    public void setChildren(Node left, Node right) {
        throw new UnsupportedOperationException("can't set children on a leaf.");
    }

    @Override
    public String toString() {
        return "" + weight;
    }
}
