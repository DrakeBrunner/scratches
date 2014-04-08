import java.util.*;

public class S extends Problem {
    private ArrayList<String> correctAnswer;
    private ArrayList<String> choices;
    /**
     * creates a new M object with the question given
     * @param question the question given
     */
    public S(String question, boolean isRandomize, Scanner input) {
        super(question, isRandomize);
        this.correctAnswer = getCorrectAnswer(input);
    }

    /**
     * Gets the correct answer from the file specified
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
     * Determines whether the user's input is correct or not
     * @param guess the user's guess
     * @return true if correct. false if incorrect
     */
    @Override
    public boolean isCorrect(String guess) {
        // Convert user's guess to upper case
        guess = guess.toUpperCase().trim();

        // There is no way that the answer could be correct if the
        // length of user's guess and the correct answer are different
        if (guess.length() != correctAnswer.size())
            return false;

        int indexOfCorrect = 0;

        for (int i = 0; i < guess.length(); i++) {
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
     * Returns a String representation of the prompt
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
}
