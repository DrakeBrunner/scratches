import java.util.Scanner;

/**
 * This program converts words to their plural form according
 * to the following rule.
 * 1. Words ending in "y" are changed by removing the "y"
 *      and adding "ies" to the end.
 * 2. Words ending in "ch", "sh", or "s" are changed by adding
 *      an "es" to the end.
 * 3. All other words are changed by adding an "s" to the end.
 */

public class Plural {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        for (int count_ = 0; count_ < 10; count_++) {
            String line = input.nextLine();
            String copy = line;

            if (line.charAt(line.length() - 1) == 'y')
                line = line.substring(0, line.length() - 1) + "ies";
            else if (line.length() >= 2 && (
                    line.substring(line.length() - 2).equals("ch") ||
                    line.substring(line.length() - 2).equals("sh") ||
                    line.charAt(line.length() - 1) == 's'))
                line += "es";
            else
                line += "s";

            System.out.printf("%s -> %s\n", copy, line);
        }

        input.close();
    }
}