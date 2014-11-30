import java.util.HashMap;


public class CLIParser {

    private static CLIParser parser;
    private static HashMap<String, Integer> options;

    static {
        parser = new CLIParser();
    }

    private CLIParser() {
    }

    public static CLIParser parser() {
        return parser;
    }

    public static HashMap<String, Object> parse(String[] args) {
        HashMap<String, Object> map = new HashMap<String, Object>();

        for (int i = 0; i < args.length; i++) {
            switch (args[i].charAt(0)) {
                case '-':
                    throw new IllegalArgumentException("Not implemented");

                default:
                    map.put("filename", new String(args[i]));
            }
        }

        return map;
    }

}
