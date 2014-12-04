package algorithms;

import data.Tree;
import helpers.CLIParser;

public abstract class AbstractAlgorithms {

    public double timer = 0.0;
    public int    affectationCounter = 0;
    public int    operationCounter   = 0;

    public abstract Tree mainFunction();

    public final void run() {
        long beginning = System.currentTimeMillis();
        Tree tree = mainFunction();
        long end = System.currentTimeMillis();

        System.out.println(tree);
        System.out.println(tree.root.totalBalance);

        timer = ((double)(end - beginning)) / 1000.0;
        if (CLIParser.opts.time)
            System.out.println(timer);
    }

}
