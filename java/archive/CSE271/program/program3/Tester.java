import java.util.*;

public class Tester {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Starting test...");
        /* String guessed = "ABC"; */
        ArrayList<Character> guessed = new ArrayList<Character>();
        guessed.add('a');
        guessed.add('B');
        guessed.add('c');
        /* char[] guessed = {'A', 'B', 'c'}; */
        HangmanPuzzle test = new HangmanPuzzle("banana", guessed);
        while (!test.isSolved()) {
            System.out.println(test.toString());
            System.out.println("getWordSoFar" + test.getWordSoFar());
            System.out.print("Guess: ");
            String guess = input.nextLine();
            test.guessLetter(guess.charAt(0));
        }
        System.out.println("Congratulations you guessed 'em all right!");
        System.out.println(test.toString());

        /* ArrayList<Character> arraylist = new ArrayList<Character>(); */
        /* arraylist.add('h'); */
        /* arraylist.add('e'); */
        /* arraylist.add('l'); */
        /* arraylist.add('l'); */
        /* char[] arraylist = {'h', 'e', 'l', 'l', 'o'}; */

        /* System.out.println("testing getGuessedLetters"); */
        /* System.out.println(test.getGuessedLetters()); */

        /* System.out.println("testing getWordSoFar"); */
        /* System.out.println(test.getWordSoFar()); */

        /* System.out.println("testing getWord"); */
        /* System.out.println(test.getWord()); */

        /* System.out.println("testing isSolved"); */
        /* if (test.isSolved()) */
        /*     System.out.println("true"); */
        /* else */
        /*     System.out.println("false"); */

        /* System.out.println("testing getScore"); */
        /* System.out.println(test.getScore()); */
        /* System.out.println("guessing g 4 times"); */
        /* test.guessLetter('g'); */
        /* test.guessLetter('g'); */
        /* test.guessLetter('g'); */
        /* test.guessLetter('g'); */
        /* System.out.println(test.getScore()); */

        /* // This should complete the game */
        /* System.out.println("testing guessLetter"); */
        /* test.guessLetter('s'); */

        /* System.out.println("\nShould complete the game\n"); */

        /* System.out.println("testing getGuessedLetters"); */
        /* System.out.println(test.getGuessedLetters()); */

        /* System.out.println("testing getWordSoFar"); */
        /* System.out.println(test.getWordSoFar()); */

        /* System.out.println("testing getWord"); */
        /* System.out.println(test.getWord()); */

        /* System.out.println("testing isSolved"); */
        /* if (test.isSolved()) */
        /*     System.out.println("true"); */
        /* else */
        /*     System.out.println("false"); */

        /* System.out.println("testing getScore"); */
        /* System.out.println(test.getScore()); */

    }
}
