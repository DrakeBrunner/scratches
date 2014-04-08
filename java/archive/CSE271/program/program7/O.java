import java.util.*;

/**
 * This class is for one word question.
 * It is a question that asks the user for one word.
 * @author Naoki Mizuno
 */

public class O extends Problem {
    private ArrayList<String> correctAnswer;
    private ArrayList<String> choices;

    /**
     * creates a new object for a one-word response question.
     * @param question the question given
     */
    public O(String question, boolean isRandomize, Scanner input) {
        super(question, isRandomize);
        this.correctAnswer = getCorrectAnswer(input);
    }

    /**
     * Gets the correct answer from the file specified until it reaches an
     * empty line, which represents the end of the correct answers.
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
     * checking whether the user's guess corresponds to one of the correct
     * answers.
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

        int indexOfCorrect = 0;
        // Anything that will not be in the input is okay here
        char previousChar = '\n';

        for (int i = 0; i < guess.length(); i++) {
            // This is to prevent indexOfCorrect from incrementing
            if (previousChar == guess.charAt(i))
                continue;
            else
                previousChar = guess.charAt(i);

            int indexOfChoice = guess.charAt(i) - 'A';
            // Don't count non-alphabets (i.e. spaces)
            if (indexOfChoice < 0 || indexOfChoice > 26)
                return false;

            // Compare the order of choice with the order of correct answer
            if (!(choices.get(indexOfChoice).equals(correctAnswer.get(indexOfCorrect++))))
                return false;
        }

        return true;
    }

    /**
     * Returns a String representation of the prompt in the format of:
     * A. The question!
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

        ret += qNumber + ". " + getQuestion() + "\n";
        ret += super.choicesToString(choices);
        ret += "Your answer: ";

        return ret;
    }
}
