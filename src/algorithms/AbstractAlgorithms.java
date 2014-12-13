package algorithms;

import data.Tree;
import helpers.CLIParser;

public abstract class AbstractAlgorithms {

    /**
     * Time needed by the algorithm to return the tree.
     */
    public double timer = 0.0;

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

    }

}
