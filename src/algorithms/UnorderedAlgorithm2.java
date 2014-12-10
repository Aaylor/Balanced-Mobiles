package algorithms;

import data.Leaf;
import data.Node;
import data.Tree;
import helpers.ExtMath;

import java.util.*;

/**
 * Created by aaylor on 09/12/14.
 */
public class UnorderedAlgorithm2 extends AbstractAlgorithms {

    private LinkedList<Integer> W;

    public UnorderedAlgorithm2(ArrayList<Integer> wl) {
        W = new LinkedList<>(wl);
        Collections.sort(W, Collections.reverseOrder());
    }

    @Override
    public Tree mainFunction() {
        return new Tree(createNode());
    }

    private Node createNode() {
        Integer first_max;
        Integer second_max = null;
        int sum = 0;

        if (W.size() == 1)
            return new Leaf(W.poll());
        else if (W.size() == 2) {
            Leaf right = new Leaf(W.poll());
            return new Node(new Leaf(W.poll()), right);
        } else {

            first_max = W.poll();
            Iterator<Integer> iterator = W.descendingIterator();

            while (sum < first_max && iterator.hasNext()) {
                int element = iterator.next();
                sum += element;
            }

            if (W.size() != 0) {
                second_max = W.poll();
            }

            if (second_max != null) {
                return new Node(new Node(new Leaf(first_max), new Leaf(second_max)), createNode());
            } else {
                return new Node(new Leaf(first_max), createNode());
            }
        }
    }

}
