package algorithms;

import data.Tree;
import helpers.CLIParser;

public abstract class AbstractAlgorithms {

    /**
     * Time needed by the algorithm to return the tree.
     */
    public double timer = 0.0;

    /**
     * Number of affectation needed by the algorithm to return the tree
     */
    public int    affectationCounter = 0;

    /**
     * Number of operation needed by the algorithm to return the tree
     */
    public int    operationCounter   = 0;

    /**
     * Number of comparison needed by the algorithm to return the tree
     */
    public int    comparisonCounter  = 0;

    /**
     * This function will return the tree corresponding to
     * the algorithm used
     * @return the new tree
     */
    public abstract Tree mainFunction();

    /**
     * Run the algorithm with time checking, and printing every
     * needed informations at the end.
     */
    public final void run() {
        long beginning = System.currentTimeMillis();

        Tree tree;
        try {
            tree = mainFunction();
        } catch (StackOverflowError e) {
            System.err.println("Stack overflow, aborting.");
            e.printStackTrace();
            System.exit(42);
            return; /* Not reached, but needed. Yeah. Java. */
        }
        long end = System.currentTimeMillis();

        if (!CLIParser.Options.noout)
            System.out.println(tree);

        System.out.println(tree.root.totalBalance);

        timer = ((double)(end - beginning)) / 1000.0;
        if (CLIParser.Options.time)
            System.out.println(timer);

        if (CLIParser.Options.counter) {
            System.out.println("Affectations: " + affectationCounter);
            System.out.println("Comparisons:  " + comparisonCounter);
            System.out.println("Operations:   " + operationCounter);
        }
    }

    /**
     * Add the number of each counter into their store values
     * @param aC number of affectation to add
     * @param oC number of operation to add
     * @param cC number of comparison to add
     */
    protected void addToCounter(int aC, int oC, int cC) {
        affectationCounter += aC;
        operationCounter   += oC;
        comparisonCounter  += cC;
    }
}
