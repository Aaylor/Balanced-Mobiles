package helpers;

import java.util.HashMap;


public class CLIParser {

    /**
     * Enumeration of each algorithm.
     */
    public static enum Algorithm {
        OrderedGreedyAlgorithm
    }

    /**
     * Option class. Contains the result of args parsing.
     */
    public static class Options {
        public static boolean readSTDIN = true;
        public static boolean time      = false;
        public static boolean random    = false;

        public static Algorithm algo    = Algorithm.OrderedGreedyAlgorithm;
    }



    /** There is only one CLIParser */
    private static CLIParser parser;

    /** Contains every options */
    private static HashMap<String, Integer> options;

    /** Options */
    public static Options opts = new Options();

    static {
        parser = new CLIParser();
    }

    private CLIParser() {
        options = new HashMap();

        options.put("-t", 0);
        options.put("--time", 0);

        options.put("-r", 1);
        options.put("--random", 1);
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
                        case "-t":
                        case "--time":
                            opts.time = true;
                            break;
                        case "-r":
                        case "--random":
                            opts.random     = true;
                            map.put("random", toInt(getArgument(args, i + 1)));
                            i++;
                            break;
                        default:
                            throw new IllegalArgumentException(
                                    "Arguments (" + args[i] + ") doesn't exists.");
                    }
                    break;


                default:
                    opts.readSTDIN = false;
                    map.put("filename", new String(args[i]));
                    break;
            }
        }

        return map;
    }


}
