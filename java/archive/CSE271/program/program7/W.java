import java.util.*;

public class W extends Problem {
    private ArrayList<String> correctAnswer;

    /**
     * creates a new W object with the question given
     * @param question the question given
     */
    public W(String question, Scanner input) {
        super(question);
        this.correctAnswer = getCorrectAnswer(input);
    }

    /**
     * Gets the correct answer from the file specified
     * @param input A Scanner object that reads the file
     * @return A String array containing the answers
     */
    public ArrayList<String> getCorrectAnswer(Scanner input) {
        String line = input.nextLine();
        // Split the answers and store the tokens into an array
        String[] answerTokens = line.split(" ");

        ArrayList<String> answers = new ArrayList<String>();
        // Add it to an ArrayList
        for (int i = 0; i < answerTokens.length; i++)
            answers.add(answerTokens[i].toLowerCase());

        return answers;
    }

    /**
     * Checks whether the user's input is correct or not
     * @param guess the user's guess
     * @return true if correct. false if incorrect
     */
    @Override
    public boolean isCorrect(String guess) {
        // Convert user's guess to upper case
        guess = guess.toLowerCase().trim();

        // Returns true if "guess" is found in "correctAnswer"
        return correctAnswer.indexOf(guess) != -1;
    }
}
