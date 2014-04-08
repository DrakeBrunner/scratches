import java.util.*;

/**
 * This class is for numeric questions. The user inputs a number according
 * to the question asks. +-0.01 accuracy is accepted.
 * @author Naoki Mizuno
 */

public class N extends Problem {
    private double correctAnswer;

    /**
     * creates a new object for numeric questions.
     * @param question the question given
     */
    public N(String question, Scanner input) {
        super(question);
        this.correctAnswer = getCorrectAnswer(input);
    }

    /**
     * Gets the correct answer from the file specified and
     * stores it into the local variable.
     * @param input A Scanner object that reads the file
     * @return A correct answer in double
     */
    public double getCorrectAnswer(Scanner input) {
        String line = input.nextLine();
        double answers = Double.parseDouble(line);

        return answers;
    }

    /**
     * Determines whether the user's input is correct or not by comparing
     * it to the correct answer. Accpets accuracy of +-0.01. May throw
     * NumberFormatException exception when the user doesn't input a number
     * @param guess the user's guess
     * @return true if correct. false if incorrect
     */
    @Override
    public boolean isCorrect(String guess) throws NumberFormatException {
        try {
            // Convert user's guess to double
            double userGuess = Double.parseDouble(guess);

            // Return true if user's guess is within 0.01 of the correct answer
            return (correctAnswer + 0.01 >= userGuess && correctAnswer - 0.01 <= userGuess);
        }
        catch (NumberFormatException e) {
            return false;
        }
    }
}
