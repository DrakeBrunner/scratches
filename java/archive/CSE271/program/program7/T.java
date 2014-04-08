import java.util.*;

/**
 * This class is for true/false questions.
 * The user has to enter (exactly) either "true" or "false".
 * @author Naoki Mizuno
 */

public class T extends Problem {
    private String correctAnswer;

    /**
     * creates a new object for true/false questions.
     * Boolean could have been used, but for simplicity and consistancy,
     * String is used for the correctAnswer.
     * @param question the question given
     */
    public T(String question, Scanner input) {
        super(question);
        this.correctAnswer = getCorrectAnswer(input);
    }

    /**
     * Gets the correct answer from the file specified as a String.
     * @param input A Scanner object that reads the file
     * @return whether the answer to this question is true or false
     */
    public String getCorrectAnswer(Scanner input) {
        String line = input.nextLine();
        return line.toLowerCase();
    }

    /**
     * Determines whether the user's input is correct or not by comparing
     * user's guess to the correct answer.
     * @param guess the user's guess
     * @return true if correct. false if incorrect
     */
    @Override
    public boolean isCorrect(String guess) {
        return guess.toLowerCase().trim().equals(correctAnswer);
    }
}
