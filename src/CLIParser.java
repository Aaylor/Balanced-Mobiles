import java.util.HashMap;


public class CLIParser {

    public static class Options {
        public static boolean readSTDIN = true;
        public static boolean time = false;
    }



    private static CLIParser parser;
    private static HashMap<String, Integer> options;

    public static Options opts = new Options();

    static {
        parser = new CLIParser();
    }

    private CLIParser() {
        options = new HashMap();
        options.put("t", 0);
        options.put("-time", 0);
    }

    public static CLIParser parser() {
        return parser;
    }

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
