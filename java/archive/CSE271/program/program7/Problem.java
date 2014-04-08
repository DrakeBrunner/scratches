import java.util.*;

/**
 * This class has the main components that the quizes have, such as questions
 * and whether the question is randomized.
 * @author Naoki Mizuno
 */

public abstract class Problem {
    private String question;
    private boolean isRandomize;

    /**
     * Receives a String of the question and creates a problem object.
     * isRandomize is used for Order (O class) and MultipleChoice questions
     * in order to determine whether to randomize the choices.
     * @param question The question from the file
     * @param isRandomize whether it should be randomized
     */
    public Problem(String question, boolean isRandomize) {
        this.question = question;
        this.isRandomize = isRandomize;
    }

    /**
     * Creates a new object with the question with isRandomize set to false.
     * This is used for other questions (single word, true/false, numeric)
     * that it doesn't matter if it should be randomized or not.
     * @param question The question from a file
     */
    public Problem(String question) {
        this(question, false);
    }

    /**
     * Getter method that returns the question of this object
     * @return the question of the object
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Getter method that returns the boolean for isRandomize
     * @return the value for isRandomize
     */
    public boolean getIsRandomize() {
        return isRandomize;
    }

    /**
     * Show the choices with a leading alphabet. This method is used by
     * problems that has multiple choices.
     * @param choices An ArrayList of choices available for this question
     */
    public String choicesToString(ArrayList<String> choices) {
        String ret = "";

        char choiceLetter = 'A';

        // Shuffle it if it's told to randomize
        if (getIsRandomize())
            Collections.shuffle(choices);

        for (int i = 0; i < choices.size(); i++)
            ret += String.format("%c. %s\n", choiceLetter++, choices.get(i));

        return ret;
    }

    /**
     * Checks whether user's input is correct or not.
     * Left up to the child classes
     * @param guess user's guess in String
     * @return true if correct. false if incorrect
     */
    public abstract boolean isCorrect(String guess);

    /**
     * Returns a String representation of the prompt so that the user
     * can input his/her answer. The format is for example,
     * A. Whatever the question is
     * Your answer:
     * @return A String representation of the prompt
     */
    public String toString(int qNumber) {
        String ret = "";
        ret += qNumber + ". " + question + "\n";
        ret += "Your answer: ";
        return ret;
    }
}
