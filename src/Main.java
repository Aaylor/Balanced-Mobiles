import algorithms.*;
import helpers.CLIParser;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

class Main {

    /**
     * Read weight in the given input (could be a file or STDIN)
     * @param stream stream
     * @return the weight list
     */
    private static ArrayList<Integer> readWeight(InputStream stream) {
        int lineNumber = 1;
        ArrayList<Integer> weightList = new ArrayList<>();
        BufferedReader bis = new BufferedReader(new InputStreamReader(stream));

        try {
            String input;
            String prompt = " ~~~> ";
            do {
                if (CLIParser.Options.readSTDIN && CLIParser.Options.prompt)
                    System.out.print(prompt);

                input = bis.readLine();

                if (input == null || input.matches("^\\s*$"))
                    break;

                try {
                    int weight = Integer.parseInt(input.trim());

                    if (weight == 0)
                        continue;

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
    private static ArrayList<Integer> randomWeight(int size) {
        ArrayList<Integer> weightList = new ArrayList<>();
        Random generator = new Random();

        Integer sum = 0;

        for (int i = 0; i < size; i++) {
            Integer generated = (generator.nextInt(CLIParser.Options.maxNumber) + 1) % Integer.MAX_VALUE;
            if (sum + generated < 0)
                return weightList;

            sum += generated;
            weightList.add(generated);
        }

        return weightList;
    }

    /**
     * Run the correct algorithm, according to the CLIParser args parsing.
     * @param weightList weight list
     */
    private static void runAlgorithm(ArrayList<Integer> weightList) {
        AbstractAlgorithms algorithm;

        switch(CLIParser.Options.algo) {
            case OrderedDynamicAlgorithm:
                algorithm = new OrderedDynamicAlgorithm(weightList);
                break;
            case OrderedAlgorithm:
                algorithm = new OrderedAlgorithm(weightList);
                break;
            case OrderedAlgorithm2:
                algorithm = new OrderedAlgorithm2(weightList);
                break;
            case UnorderedAlgorithm1:
                algorithm = new UnorderedAlgorithm(weightList);
                break;
            case UnorderedAlgorithm2:
                algorithm = new UnorderedAlgorithm2(weightList);
                break;
            default:
                algorithm = new OrderedDynamicAlgorithm(weightList);
                break;
        }

        algorithm.run();
    }


    public static void main(String[] args) {

        ArrayList<Integer> weightList = new ArrayList<>();
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
