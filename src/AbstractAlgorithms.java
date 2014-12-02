public abstract class AbstractAlgorithms {

    public double timer = 0.0;
    public int    affectationCounter = 0;
    public int    operationCounter   = 0;

    public abstract void mainFunction();

    public void run() {
        long beginning = System.currentTimeMillis();
        mainFunction();
        long end = System.currentTimeMillis();

        timer = ((double)(beginning - end)) / 1000.0;
    }

}
