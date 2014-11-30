public class Leaf extends Node {

    public Leaf() {
        super();
    }

    public Leaf(int weight) {
        super(null, null, weight, 0);
    }

    @Override
    public String toString() {
        return "" + weight;
    }
}
