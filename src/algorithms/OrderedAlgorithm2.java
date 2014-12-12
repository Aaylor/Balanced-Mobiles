package algorithms;

import data.Leaf;
import data.Node;
import data.Tree;

import java.util.ArrayList;

/**
 * Created by aaylor on 09/12/14.
 */
public class OrderedAlgorithm2 extends AbstractAlgorithms {
    /**
     * The Weight list.
     */
    private Integer[] W;

    /**
     * Create the algorithm with the given entry.
     * @param entry weight list
     */
    public OrderedAlgorithm2(ArrayList<Integer> entry) {
        W = entry.toArray(new Integer[0]);
    }

    @Override
    public Tree mainFunction() {
        return new Tree(constructTree(0, W.length - 1));
    }

    private int indexMin(int beg, int end) {
        int index = beg;
        int min   = W[beg];

        for (int i = beg + 1; i <= end; i++) {
            if (W[i] < min) {
                min = W[i];
                index = i;
            }
        }

        return index;
    }

    private int chooseMinUnbalanced(int beg, int end, int index) {
        Integer right, left;

        int rightIndex = index - 1;
        int leftIndex  = index + 1;

        right = (rightIndex < beg ? null : W[rightIndex]);
        left  = (leftIndex  > end ? null : W[leftIndex]);

        return (left == null ? rightIndex : (right == null ? leftIndex :  (right < left ? rightIndex : leftIndex)));
    }


    private Node constructTree(int beg, int end) {
        if (beg == end) return new Leaf(W[beg]);
        else if (beg + 1 == end) return new Node(new Leaf(W[beg]), new Leaf(W[end]));
        else {
            int index = indexMin(beg, end);
            int indexMinUnbalanced = chooseMinUnbalanced(beg, end, index);

            int indexLeft  = index - 1;
            int indexRight = index + 1;

            Node currentNode;
            if (indexMinUnbalanced < index) {
                --indexLeft;
                currentNode = new Node(new Leaf(W[indexMinUnbalanced]),
                        new Leaf((W[index])));
            } else {
                ++indexRight;
                currentNode = new Node(new Leaf(W[index]),
                        new Leaf((W[indexMinUnbalanced])));
            }

            while(true) {
                if (indexRight > end && indexLeft < beg) {

                    return currentNode;

                } else if (indexRight > end) {

                    if (W[indexLeft] - currentNode.weight >= 0) {
                        currentNode = new Node(new Leaf(W[indexLeft]), currentNode);
                        --indexLeft;
                    } else {
                        return new Node(constructTree(beg, indexLeft), currentNode);
                    }

                } else if (indexLeft < beg) {

                    if (W[indexRight] - currentNode.weight >= 0) {
                        currentNode = new Node(currentNode, new Leaf(W[indexRight]));
                        ++indexRight;
                    } else {
                        return new Node(currentNode, constructTree(indexRight, end));
                    }

                } else {

                    int diffLeft  = W[indexLeft]  - currentNode.weight;
                    int diffRight = W[indexRight] - currentNode.weight;
                    if (diffLeft < 0 || diffRight < 0) {
                        if (diffLeft < diffRight) {
                            return new Node(new Node(constructTree(beg, indexLeft), currentNode),
                                    constructTree(indexRight, end));
                        } else {
                            return new Node(constructTree(beg, indexLeft), new Node(currentNode, constructTree(indexRight, end)));
                        }
                    }
                    if (diffLeft < diffRight) {
                        currentNode = new Node(new Leaf(W[indexLeft]), currentNode);
                        --indexLeft;
                    } else {
                        currentNode = new Node(currentNode, new Leaf(W[indexRight]));
                        ++indexRight;
                    }

                }
            }

        }
    }
}
