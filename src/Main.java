import algorithms.AbstractAlgorithms;
import algorithms.OrderedGreedyAlgorithm;
import helpers.CLIParser;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Main {

    /**
     * Read weight in the given input (could be a file or STDIN)
     * @param stream stream
     * @return the weight list
     */
    public static ArrayList<Integer> readWeight(InputStream stream) {
        int lineNumber = 1;
        ArrayList<Integer> weightList = new ArrayList();
        BufferedReader bis = new BufferedReader(new InputStreamReader(stream));

        try {
            String input;
            String prompt = " ~~~> ";
            do {
                if (CLIParser.Options.readSTDIN) System.out.print(prompt);

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

    /**
     * Create a random weight list
     * @param size the list size
     * @return the weight list
     */
    public static ArrayList<Integer> randomWeight(int size) {
        ArrayList<Integer> weightList = new ArrayList<>();
        Random generator = new Random();

        for (int i = 0; i < size; i++) {
            weightList.add(generator.nextInt(size) + 1);
        }

        return weightList;
    }

    /**
     * Run the correct algorithm, according to the CLIParser args parsing.
     * @param weightList
     */
    public static void runAlgorithm(ArrayList<Integer> weightList) {
        AbstractAlgorithms algorithm;

        switch(CLIParser.Options.algo) {
            case OrderedGreedyAlgorithm:
                algorithm = new OrderedGreedyAlgorithm(weightList);
                break;
            default:
                algorithm = new OrderedGreedyAlgorithm(weightList);
                break;
        }

        algorithm.run();
    }


    public static void main(String[] args) {

        ArrayList<Integer> weightList = new ArrayList();
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
        } else if (arguments.containsKey("random")) {
            weightList = randomWeight((Integer)arguments.get("random"));
        } else {
            weightList = readWeight(System.in);
        }

        if (weightList.size() == 0) {
            System.out.println("No entry.");
            System.exit(0);
        }

        runAlgorithm(weightList);
    }

}
