import java.util.*;
import java.io.*;

public class mizunon_homework11 {
    public static void main(String[] args) {
        weirdAlsPalindrome();
        drawLine();
        countingWords();
        drawLine();
        soccerLeagueStandings();
    }

    public static void weirdAlsPalindrome() {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter words: ");
        String user_input = input.nextLine().trim().toLowerCase();

        if (palindrome(user_input))
            System.out.println("Palindome!");
        else
            System.out.println("Not Palindrome.");
    }

    public static boolean palindrome(String user_input) {
        // Array that contains only letters
        ArrayList<Character> letters = new ArrayList<Character>();

        // Extract letters and put them into an array
        for (int i = 0; i < user_input.length(); i++) {
            if (user_input.charAt(i) >= 'a' && user_input.charAt(i) <= 'z')
                letters.add(user_input.charAt(i));
        }

        // Then, check if the array is a palindrome
        for (int i = 0; i < letters.size() / 2; i++) {
            //System.out.printf("%c : %c\n", letters.get(i), letters.get(letters.size() - 1 - i));
            if (letters.get(i) == letters.get(letters.size() - 1 - i))
                continue;
            else
                return false;
        }

        return true;
    }

    /* ------------------------------------------------------- */

    public static void countingWords() {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter words: ");
        String user_input = input.nextLine();

        System.out.printf("There are %d words in that String.\n", count(user_input));
    }

    public static int count(String user_input) {
        int word_count = 0;

        if (user_input.length() > 0)
            word_count++;

        for (int i = 0; i < user_input.length(); i++) {
            if (user_input.charAt(i) == ' ')
                word_count++;
        }

        return word_count;
    }

    /* ------------------------------------------------------- */

    public static void soccerLeagueStandings() {
        Scanner input = new Scanner(System.in);

        ArrayList<Teams> teams = new ArrayList<Teams>();

        System.out.print("File name: ");
        String filename = input.nextLine().trim();
        if (filename.length() == 0)
            filename = "TeamScores.txt";

        try {
            loadFile(teams, filename);
        }
        catch (Exception e) {
            System.out.println("Error Loading File: " + e);
        }

        // Marks the first of the letter
    }

    public static void loadFile(ArrayList<Teams> teams, String filename) throws Exception{
        BufferedReader br = new BufferedReader( new FileReader(filename) );
        String line = br.readLine();

        int word_start = 0;
        while (line != null) {
            // Add a space so that subString works at the end
            line += " ";

            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == ' ') {
                    String token = line.subString(word_start, i);
                    // If the token is a number
                    if (token.charAt(token.length() - 1) >= '0' && token.charAt(token.length() - 1) <= '9') {
                        int num = token.charAt(token.length() - 1) - '0';
                        System.out.println("Number: " + num);
                    }

                    // If the token IS found
                    if (teams.indexOf( token ) != -1)
                        teams.set(teams.indexOf( token ), token );
                    // If the token is not found, add it
                    else
                        teams.add(teams.indexOf( token ));
                    // Set team name if it doesn't exist
                }
            }
        }

        br.close();
    }

    public static void drawLine() {
        System.out.println();
        for (int i = 0; i < 40; i++)
            System.out.print("-");
        System.out.println();
    }
}
