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

    private ArrayList<Integer> W;

    public UnorderedAlgorithm2(ArrayList<Integer> wl) {
        W = new ArrayList<>(wl);
        Collections.sort(W, Collections.reverseOrder());
    }

    @Override
    public Tree mainFunction() {
        return new Tree(createNode());
    }


    private Integer poll(ArrayList<Integer> W) {
        return W.remove(0);
    }

    private Node createNode() {
        if (W.size() == 1)
            return new Leaf(poll(W));
        else if (W.size() == 2) {
            Leaf right = new Leaf(poll(W));
            return new Node(new Leaf(poll(W)), right);
        } else {
            Integer first_max;
            Integer second_max = null;
            int sum = 0;

            first_max = poll(W);
            int cpt = W.size() - 2; /* -2 because of the first poll */

            while (sum < first_max && cpt >= 0) {
                int element = W.get(cpt);
                sum += element;
                --cpt;
            }

            if (cpt >= 0) {
                second_max = poll(W);
            }


            if (second_max != null) {
                return new Node(new Node(new Leaf(first_max), new Leaf(second_max)), createNode());
            } else {
                return new Node(new Leaf(first_max), createNode());
            }
        }
    }

}
