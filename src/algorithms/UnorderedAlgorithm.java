package algorithms;

import data.Leaf;
import data.Node;
import data.Tree;
import helpers.ExtMath;
import sun.awt.image.ImageWatched;

import java.util.*;

public class UnorderedAlgorithm extends AbstractAlgorithms {

    private LinkedList<Integer> weightList;

    public UnorderedAlgorithm(ArrayList<Integer> wl) {
        weightList = new LinkedList<>(wl);
        Collections.sort(weightList, Collections.reverseOrder());
    }

    @Override
    public Tree mainFunction() {
        int sum = ExtMath.sum(weightList);
        return new Tree(createNode(weightList, sum));
    }

    private void addSortedElement(LinkedList<Integer> W, Integer element) {
        ListIterator<Integer> iterator = W.listIterator();
        while(iterator.hasNext()) {
            Integer i = iterator.next();
            if (i < element) {
                iterator.previous();
                iterator.add(element);
                return;
            }
        }

        W.addLast(element);
    }

    private Node createNode(LinkedList<Integer> W, double sum) {
        if (W.size() == 1)
            return new Leaf(W.poll());
        else if (W.size() == 2) {
            Leaf leaf = new Leaf(W.poll());
            return new Node(leaf, new Leaf(W.poll()));
        } else {
            boolean top = true;
            double half = ExtMath.half(sum);
            double leftWeight = half;
            double rightWeight = half;
            LinkedList<Integer> leftList = new LinkedList<>();
            LinkedList<Integer> rightList = new LinkedList<>();

            while (W.size() != 0) {
                Integer element;
                if (top) element = W.poll();
                else element = W.pollLast();

                if (leftWeight == rightWeight && (leftList.size() != 0 && rightList.size() != 0)) {
                    if (Math.abs(leftList.peek() - element) > (Math.abs(rightList.peek() - element))) {
                        addSortedElement(rightList, element);
                        rightWeight -= element;
                    } else {
                        addSortedElement(leftList, element);
                        leftWeight -= element;
                    }
                } else if (leftWeight >= rightWeight) {
                    addSortedElement(leftList, element);
                    leftWeight -= element;
                } else {
                    addSortedElement(rightList, element);
                    rightWeight -= element;
                }

                top = !top;
            }

            double lw = half - leftWeight;
            double rw = half - rightWeight;
            return new Node(createNode(leftList, lw),
                    createNode(rightList, rw));
        }
    }
}
