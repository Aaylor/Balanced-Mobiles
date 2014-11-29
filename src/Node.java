public class Node {

    public Node parentNode;
    public Node leftChild;
    public Node rightChild;
    public int  weight;
    public int  balance;

    public Node() {
        parentNode  = null;
        leftChild   = null;
        rightChild  = null;
        weight      = 0;
        balance     = 0;
    }

    public Node(Node leftChild, Node rightChild) {
        this.leftChild  = leftChild;
        this.rightChild = rightChild;

        this.weight  = leftChild.weight + rightChild.weight;
        this.balance = Math.abs(leftChild.weight - rightChild.weight);
    }

    public Node(Node leftChild, Node rightChild, int weight, int balance) {
        this.parentNode = null;
        this.leftChild  = leftChild;
        this.rightChild = rightChild;
        this.weight     = weight;
        this.balance    = balance;
    }

}
