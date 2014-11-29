public class Main {

    public static void main(String[] args) {
        Leaf l1 = new Leaf(42);
        Leaf l2 = new Leaf(5);
        Leaf l3 = new Leaf(3);

        Node n1 = new Node(l1, l2);
        Node n2 = new Node(n1, l3);

        Tree t = new Tree(n2);

        System.out.println("Tree.root.weight  = " + t.root.weight);
        System.out.println("Tree.root.balance = " + t.root.balance);

        System.out.println("Tree.root.weight  = " + t.root.leftChild.weight);
        System.out.println("Tree.root.balance = " + t.root.leftChild.balance);

    }

}
