import java.util.*;

/**
 * This class represents the multiple choice question. It can be used for
 * both multiple choice questions with single answer and multiple answers.
 * @author Naoki Mizuno
 */

public class MultipleChoice extends Problem {
    private ArrayList<String> correctAnswer;
    private ArrayList<String> wrongAnswer;
    private ArrayList<String> choices;

    /**
     * creates a new object for multiple choice questions
     * @param question The question given
     */
    public MultipleChoice(String question, boolean isRandomize, Scanner input) {
        super(question, isRandomize);
        this.correctAnswer = getCorrectAnswer(input);
        // This may be confusing. Since the method only reads lines
        // until it hits an empty line, it doesn't matter if it's the
        // "correct" answer or the "wrong" answer. It just returns whatever
        // it read. I might want to rename the method for the whole program
        this.wrongAnswer = getCorrectAnswer(input);
    }

    /**
     * Gets the correct answer from the file specified
     * and add it to the ArrayList.
     * @param input A Scanner object that reads the file
     * @return An ArrayList of String containing the answers
     */
    public ArrayList<String> getCorrectAnswer(Scanner input) {
        String line;
        ArrayList<String> answers = new ArrayList<String>();

        while (!(line = input.nextLine()).equals(""))
            answers.add(line);

        return answers;
    }

    /**
     * Determines whether the user's input is correct or not by
     * using indexOf and checking whether user's choice matches the correct
     * answer. It only accepts alphabet characters.
     * @param guess the user's guess
     * @return true if correct. false if incorrect
     */
    @Override
    public boolean isCorrect(String guess) {
        // Convert user's guess to upper case
        guess = guess.toUpperCase().trim();

        // The length of guess can never be smaller than the size of choices
        if (guess.length() < choices.size())
            return false;

        for (int i = 0; i < guess.length(); i++) {
            int indexOfChoice = guess.charAt(i) - 'A';

            // Don't count non-alphabets (i.e. spaces)
            if (indexOfChoice < 0 || indexOfChoice > 26)
                return false;

            // In order to avoid IndexOutOfBoundsException
            if (indexOfChoice >= choices.size())
                return false;

            // Compare the order of choice with the order of correct answer
            if (correctAnswer.indexOf(choices.get(indexOfChoice)) == -1)
                return false;
        }

        return true;
    }

    /**
     * Returns a String representation of the prompt with the choices.
     * The format is for example,
     * B. Multiple choice question sentence
     * 1. choice 1
     * 2. choie 2
     * ...
     * Your answer:
     * @return A String representation of the prompt
     */
    @Override
    public String toString(int qNumber) {
        String ret = "";

        choices = new ArrayList<String>();

        // Make a copy of the correctAnswer
        for (int i = 0; i < correctAnswer.size(); i++)
            choices.add(correctAnswer.get(i));
        // Add the choices in wrongAnswer to that
        for (int i = 0; i < wrongAnswer.size(); i++)
            choices.add(wrongAnswer.get(i));

        ret += qNumber + ". " + getQuestion() + "\n";
        ret += choicesToString(choices);
        ret += "Your answer: ";

        return ret;
    }
}
