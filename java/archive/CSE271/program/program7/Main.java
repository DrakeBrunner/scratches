import java.util.*;
import java.io.*;

/**
 * This is the class containing the main method.
 * It reads from a file that has problems and questions written in a certain
 * format and store it into an ArrayList of Problem.
 * @author Naoki Mizuno
 */

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        boolean isRandomize = false;
        int numOfCorrectAnswer = 0;

        System.out.print("Which quiz are you taking? ");
        String fileName = input.nextLine();
        System.out.print("Do you wish to randomize the questions and choices? ");
        if (input.nextLine().equals("yes"))
            isRandomize = true;

        // Read information from file
        try {
            ArrayList<Problem> p = readFile(fileName, isRandomize);

            if (isRandomize)
                Collections.shuffle(p);

            // Start the quiz
            for (int i = 0; i < p.size(); i++) {
                // make a toString and use that to print all the prompt
                System.out.print(p.get(i).toString(i + 1));

                // Gets the user input and check if that's correct
                if (p.get(i).isCorrect(input.nextLine())) {
                    System.out.println("Correct.");
                    numOfCorrectAnswer++;
                }
                else
                    System.out.println("Incorrect.");
                System.out.println();
            }

            System.out.printf("You got %d out of %d questions correct for a final percentage of %.1f%%\n",
                    numOfCorrectAnswer, p.size(), (double)numOfCorrectAnswer / p.size() * 100);
        }
        catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    /**
     * Reads a file and store information of all the problems in ArrayList.
     * It reads the first and second line (which is always the question) and
     * create a new object according to the first line, which represents what
     * kind of question it is. Scanner object is used in order to maintain the
     * position of the current line.
     * @param fileName name of the file to read from
     * @return the ArrayList containing the information of all problems
     */
    public static ArrayList<Problem> readFile(String fileName, boolean isRandomize) throws FileNotFoundException {
        Scanner input = new Scanner(new File(fileName));

        ArrayList<Problem> p = new ArrayList<Problem>();

        while (input.hasNextLine()) {
            String attribute = input.nextLine().toUpperCase().trim();
            switch (attribute.charAt(0)) {
                case 'O':
                    p.add(new O(input.nextLine(), isRandomize, input));
                    break;
                case 'W':
                    p.add(new W(input.nextLine(), input));
                    break;
                case 'N':
                    p.add(new N(input.nextLine(), input));
                    break;
                case 'T':
                    p.add(new T(input.nextLine(), input));
                    break;
                case 'S':
                case 'M':
                    p.add(new MultipleChoice(input.nextLine(), isRandomize, input));
                    break;
            }
        }
        return p;
    }
}
