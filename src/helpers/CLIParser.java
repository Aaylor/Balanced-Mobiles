package helpers;

import java.util.HashMap;


public class CLIParser {

    /**
     * Enumeration of each algorithm.
     */
    public static enum Algorithm {
        OrderedDynamicAlgorithm,
        OrderedAlgorithm,
        OrderedAlgorithm2,
        UnorderedAlgorithm1,
        UnorderedAlgorithm2,
    }

    /**
     * Option class. Contains the result of args parsing.
     */
    public static class Options {
        public static boolean readSTDIN = true;
        public static boolean prompt    = false;
        public static boolean time      = false;
        public static boolean counter   = false;
        public static boolean noout     = false;

        public static Algorithm algo    = Algorithm.OrderedAlgorithm;

        public static Integer maxNumber = 424242;
    }



    /** There is only one CLIParser */
    private static CLIParser parser;

    static {
        parser = new CLIParser();
    }

    private CLIParser() {
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
                        case "--noout":
                            Options.noout = true;
                            break;
                        case "-m":
                        case "--max":
                            Options.maxNumber = toInt(getArgument(args, i + 1));
                            ++i;
                            break;
                        case "-p":
                        case "--prompt":
                            Options.prompt = true;
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
                        case "-od":
                        case "--ordered-dynamic":
                            Options.algo = Algorithm.OrderedDynamicAlgorithm;
                            break;
                        case "-o1":
                        case "--ordered-1":
                            Options.algo = Algorithm.OrderedAlgorithm;
                            break;
                        case "-o2":
                        case "--ordered-2":
                            Options.algo = Algorithm.OrderedAlgorithm2;
                            break;
                        case "-u1":
                        case "--unordered-1":
                            Options.algo = Algorithm.UnorderedAlgorithm1;
                            break;
                        case "-u2":
                        case "--unordered-2":
                            Options.algo = Algorithm.UnorderedAlgorithm2;
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
