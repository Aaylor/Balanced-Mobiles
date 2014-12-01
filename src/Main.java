import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;

public class Main {

    public static LinkedList<Integer> readWeight(InputStream stream) {
        int lineNumber = 1;
        LinkedList<Integer> weightList = new LinkedList();
        BufferedReader bis = new BufferedReader(new InputStreamReader(stream));

        try {
            String input;
            String prompt = " ~~~> ";
            do {
                if (CLIParser.opts.readSTDIN) System.out.print(prompt);

                input = bis.readLine();

                if (input == null || input.matches("^\\s*$"))
                    break;

                try {
                    int weight = Integer.parseInt(input.trim());
                    weightList.add(weight);
                } catch (Exception e) {
                    System.err.println("Error while reading input, line "
                            + lineNumber + ": expected integer.");
                }
            } while(true);
        } catch (IOException e) {
            System.err.println("Error while reading input: " + e.getMessage());
        }

        return weightList;
    }

    public static void main(String[] args) {

        LinkedList<Integer> weightList = new LinkedList();
        HashMap<String, Object> arguments = CLIParser.parse(args);

        if (arguments.containsKey("filename")) {
            try {
                weightList = readWeight(
                        new FileInputStream((String)arguments.get("filename")));
            } catch (FileNotFoundException e) {
                System.err.println("Error while getting file: " + e.getMessage());
                System.err.println("Aborting.");
                System.exit(1);
            }
        } else {
            weightList = readWeight(System.in);
        }

        if (weightList.size() == 0) {
            System.out.println("No entry.");
            System.exit(0);
        }

        System.out.println(weightList);
    }

}
