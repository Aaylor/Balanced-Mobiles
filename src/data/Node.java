package data;

public class Node {

    public Node leftChild;
    public Node rightChild;
    public int  weight;
    public int  balance;
    public int  totalBalance;

    public Node() {
        leftChild   = null;
        rightChild  = null;
        weight      = 0;
        balance     = 0;
        totalBalance = 0;
    }

    public Node(Node leftChild, Node rightChild) {
        setChildren(leftChild, rightChild);
    }

    public Node(Node leftChild, Node rightChild, int weight, int balance, int totalBalance) {
        this.leftChild  = leftChild;
        this.rightChild = rightChild;
        this.weight     = weight;
        this.balance    = balance;
        this.totalBalance = totalBalance;
    }

    public void setChildren(Node leftChild, Node rightChild) {
        this.leftChild    = leftChild;
        this.rightChild   = rightChild;
        this.weight       = leftChild.weight + rightChild.weight;
        this.balance      = Math.abs(leftChild.weight - rightChild.weight);
        this.totalBalance = leftChild.totalBalance + rightChild.totalBalance + balance;
    }

    @Override
    public String toString() {
        return "[" + leftChild + ", " + rightChild + "]";
    }
}
