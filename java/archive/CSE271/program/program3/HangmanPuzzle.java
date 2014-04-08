import java.util.*;

public class HangmanPuzzle {
    // Data
    private String word;
    private ArrayList<String> guesses;
    private int score;

    public HangmanPuzzle(String word) {
        this.word = word.toUpperCase();
        this.guesses = new ArrayList<String>();
        this.score = 100;
    }

    // Constructors
    public HangmanPuzzle(String word, ArrayList<Character> guesses) {
        this(word);
        for (int i = 0; i < guesses.size(); i++) {
            String letter = (Character.toString(guesses.get(i)));
            // Store everything in upper case
            letter = letter.toUpperCase();
            if (this.guesses.indexOf(letter) == -1)
                this.guesses.add(letter);

        }
    }

    public HangmanPuzzle(String word, char[] guesses) {
        this(word);
        for (int i = 0; i < guesses.length; i++) {
            String letter = Character.toString(guesses[i]);
            // Store everything in upper case
            letter = letter.toUpperCase();
            if (this.guesses.indexOf(letter) == -1)
                this.guesses.add(letter);
        }
    }

    public HangmanPuzzle(String word, String guesses) {
        this(word);
        for (int i = 0; i < guesses.length(); i++) {
            String letter = (Character.toString(guesses.charAt(i)));
            // Store everything in upper case
            letter = letter.toUpperCase();
            if (this.guesses.indexOf(letter) == -1)
                this.guesses.add(letter);
        }
    }

    // Methods
    // @Returns the letters guessed so far, as a sorted uppercase String
    public String getGuessedLetters() {
        Collections.sort(guesses);
        String ret = "";
        for (int i = 0; i < guesses.size(); i++)
            ret += guesses.get(i).toUpperCase();
        return ret;
    }

    // @Returns the current score
    public int getScore() {
        return score;
    }

    // @Returns the correct answer (the full word)
    public String getWord() {
        return word;
    }

    // @Returns String of words that has been guessed correctly so far
    public String getWordSoFar() {
        String ret = "";
        for (int i = 0; i < word.length(); i++) {
            String letter = Character.toString(word.charAt(i));
            if (guesses.indexOf(letter.toUpperCase()) != -1)
                ret += letter + " ";
            else
                ret += "_ ";
        }
        return ret;
    }

    // make a guess and if the letter is correct, add 10 points to the score
    // If it's wrong, deduct 5 point.
    public void guessLetter(char guess) {
        if ( isSolved() || score <= 0)
            return;

        String letter = Character.toString(guess);
        // Store everything in upper case
        letter = letter.toUpperCase();

        // Add it to the ArrayList if it doesn't exist
        if (guesses.indexOf(letter) == -1)
            guesses.add(letter);
        else
            return;

        boolean flag = false;
        for (int i = 0; i < word.length(); i++) {
            // Word contains that letter
            if (Character.toString(word.charAt(i)).equals(letter)) {
                score += 10;
                flag = true;
            }
        }
        // If the word didn't contain the letter guessed
        if (flag == false)
            score -= 5;
    }

    // @Returns whether the whole word has been guessed correctly or not
    public boolean isSolved() {
        String wordSoFar = getWordSoFar();

        for (int i = 0; i < wordSoFar.length(); i++)
            if (wordSoFar.charAt(i) == '_')
                return false;

        return true;
    }

    public String toString() {
        String ret = "";
        ret = getWordSoFar() + "(" + getWord() + ")\n" + guesses.size() + " letters guessed: " + getGuessedLetters() + "\nScore: " + score;

        return ret;
    }
}
