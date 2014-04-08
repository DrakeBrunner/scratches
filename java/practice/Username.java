import java.util.Scanner;
import java.io.*;

/**
 * This program uses 6 letters of one's last name at most,
 * and add an initial of the first name to make an username.
 * The maximum length of the username is 8 characters.
 *
 * NOTE: Are there supposed to be examples after the problem
 *       description in the pdf file?
 *
 *       There should be a specification that the end of input is
 *       represented by "STOP HERE"
 *
 *       Probably best if it is specified to ignore middle names that
 *       make the ID longer than 8 letters. Although it is said that
 *       "truncated to a maximum of 8 chars", the only possible thing
 *       that can be truncated is the middle name.
 */

public class Username {
    public static void main(String[] args) throws Exception {
        // Scanner input = new Scanner(System.in);
        Scanner input = new Scanner(new File("username.in"));

        // It's best to add .toLowerCase() but for the sake of checking
        // adherance, I didn't use it
        String line = input.nextLine();
        int numOfPeople = 1;

        while (!(line.equals("STOP HERE"))) {
            String username = "";
            String[] names = line.split(" ");

            /* ---------------------------------------------
            * Adherance check
            */
            // Has to be at least 2 tokens
            if (names.length < 2)
                System.out.println("The input does not contain at least a first name and a last name!");

            // Has at most 5 middle names
            if (names.length > 7)
                System.out.println("There are more than 5 middle names");

            // Should contain only uppercase letters
            if (!(line.equals(line.toUpperCase())))
                System.out.println("The input should only contain uppercase letters");

            for (int i = 0; i < names.length; i++)
                if (names[i].length() < 1 || names[i].length() > 20)
                    System.out.println("Each token (name) must be at least 1 and at most 20 in length: " + names[i]);
            // ---------------------------------------------


            // Add the last name to username (6 chars at most)
            if (names[names.length - 1].length() > 6)
                username += names[names.length - 1].substring(0, 6);
            else
                username += names[names.length - 1];

            // Add the first letter of their other names (excluding last name)
            // Break if the username reaches a length of 8
            for (int i = 0; i < names.length - 1; i++) {
                if (username.length() >= 8)
                    break;
                username += Character.toString(names[i].charAt(0));
            }

            // Print the username
            System.out.printf("Employee #%d: %s\n", numOfPeople++, username.toLowerCase());

            // Read next person
            line = input.nextLine();
        }
        input.close();
    }
}
