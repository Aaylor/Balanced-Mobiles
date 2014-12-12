package algorithms;

import data.Leaf;
import data.Node;
import data.Tree;

import java.util.ArrayList;

public class OrderedDynamicAlgorithm extends AbstractAlgorithms {

    Integer[] W;

    public OrderedDynamicAlgorithm(ArrayList<Integer> W) {
        this.W = W.toArray(new Integer[0]);
    }

    @Override
    public Tree mainFunction() {
        return new Tree(constructTree());
    }

    private int getRealSum(int[] sums, int beg, int end) {
        return sums[end + 1] - sums[beg];
    }

    private Node constructTree() {
        int[][] balances = new int[W.length][W.length];
        int[][] way      = new int[W.length][W.length];
        int[] sums       = new int[W.length + 1];

        sums[0] = 0;
        for (int i = 1; i < W.length + 1; i++) {
            sums[i] = sums[i - 1] + W[i - 1];
        }

        for (int j = 0; j < W.length; j++) {
            for (int i = j - 1; i >= 0; i--) {

                int indexMin = Integer.MAX_VALUE;
                int localTotalBalance = Integer.MAX_VALUE;

                for (int k = i; k < j; k++) {
                    int balance =
                            balances[i][k] + balances[k+1][j] + Math.abs(getRealSum(sums, i, k) - getRealSum(sums, k + 1, j));

                    if (balance < localTotalBalance) {
                        indexMin = k;
                        localTotalBalance = balance;
                    }
                }

                balances[i][j] = localTotalBalance;
                way[i][j] = indexMin;

            }
        }

        return reconstructTree(way);
    }

    public Node reconstructTree(int[][] way) {
        return helper(way, 0, W.length - 1);
    }

    private Node helper(int[][] way, int beg, int end) {
        if (beg >= end) {
            return new Leaf(W[beg]);
        } else if (beg + 1 == end) {
            return new Node(new Leaf(W[beg]), new Leaf(W[end]));
        } else {
            return new Node(helper(way, beg, way[beg][end]), helper(way, way[beg][end] + 1, end));
        }
    }

}
