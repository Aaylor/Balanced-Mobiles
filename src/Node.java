public class Node {

    public Node leftChild;
    public Node rightChild;
    public int  weight;
    public int  balance;

    public Node() {
        leftChild = null;
        rightChild = null;
        weight = 0;
        balance = 0;
    }

    public Node(Node leftChild, Node rightChild, int weight, int balance) {
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.weight = weight;
        this.balance = balance;
    }

}
