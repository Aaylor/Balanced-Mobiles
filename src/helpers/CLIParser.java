package helpers;

import algorithms.UnorderedAlgorithm;

import java.util.HashMap;


public class CLIParser {

    /**
     * Enumeration of each algorithm.
     */
    public static enum Algorithm {
        OrderedGreedyAlgorithm,
        OrderedAlgorithm2,
        UnorderedAlgorithm1,
    }

    /**
     * Option class. Contains the result of args parsing.
     */
    public static class Options {
        public static boolean readSTDIN = true;
        public static boolean time      = false;
        public static boolean counter   = false;

        public static Algorithm algo    = Algorithm.OrderedGreedyAlgorithm;
    }



    /** There is only one CLIParser */
    private static CLIParser parser;

    /** Contains every options */
    private static HashMap<String, Integer> options;

    static {
        parser = new CLIParser();
    }

    private CLIParser() {
        options = new HashMap();

        options.put("-t", 0);
        options.put("--time", 0);

        options.put("-r", 1);
        options.put("--random", 1);

        options.put("-c", 0);
        options.put("--counter", 0);

        options.put("-o2", 0);
        options.put("--ordered-2", 0);
    }

    /**
     * Returns the needed argument at the given position.
     * @param args args array
     * @param position integer position
     * @return the argument
     */
    private static String getArgument(String[] args, int position) {
        if (position >= args.length)
            throw new IllegalArgumentException("expected arguments at position '" + position + "' .");

        return args[position];
    }

    /**
     * Parse a string to integer.
     * @param s the string to parse
     * @return an integer
     */
    private static Integer toInt(String s) {
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            throw new IllegalArgumentException("expected an integer (" + s + ").");
        }
    }

    /**
     * Parse every arguments.
     * @param args args list
     * @return a map containing every arg with their args
     */
    public static HashMap<String, Object> parse(String[] args) {
        HashMap<String, Object> map = new HashMap();

        for (int i = 0; i < args.length; i++) {
            switch (args[i].charAt(0)) {
                case '-':
                    switch(args[i]) {
                        case "-c":
                        case "--counter":
                            Options.counter = true;
                            break;
                        case "-r":
                        case "--random":
                            map.put("random", toInt(getArgument(args, i + 1)));
                            i++;
                            break;
                        case "-t":
                        case "--time":
                            Options.time = true;
                            break;
                        case "-o2":
                        case "--ordered-2":
                            Options.algo = Algorithm.OrderedAlgorithm2;
                            break;
                        case "-u1":
                        case "--unordered-1":
                            Options.algo = Algorithm.UnorderedAlgorithm1;
                            break;
                        default:
                            throw new IllegalArgumentException(
                                    "Arguments (" + args[i] + ") doesn't exists.");
                    }
                    break;


                default:
                    Options.readSTDIN = false;
                    map.put("filename", args[i]);
                    break;
            }
        }

        return map;
    }


}
