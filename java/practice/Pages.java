import java.util.Scanner;
import java.io.*;

/**
 * This program converts consecutive numbers, representing page numbers, to a
 * more condense form. For example,
 * 1 2 3 4 9 10 15 20 21 22 23 35 0
 * would become
 * 1-4,9-10,15,20-23,35
 *
 * End of input is shown by the number 0 and a line with only a zero in it
 * represents the end of input.
 */

public class Pages {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(new File("pages.in"));
        //Scanner input = new Scanner(System.in);
        String line;

        while (true) {
            line = input.nextLine();
            // 0 represents the end of input
            if (line.equals("0"))
                break;


            // Break the input into tokens and parse into int
            String[] stringPages = line.split(" ");

            /* ADHERENCE CHECK!! */
            // Warn when the line doesn't end with 0
            if ((!stringPages[stringPages.length - 1].equals("0")))
                System.out.println("The last number is not 0");

            int[] pages = new int[stringPages.length];
            for (int i = 0; i < stringPages.length; i++)
                if (!(Integer.parseInt(stringPages[i]) == 0))
                    pages[i] = Integer.parseInt(stringPages[i]);

            /* ----------------------------------
             * Code that checks whether the input
             * adheres to what the write-up says
             */

            // Check if it's within 1 to 2000000000
            if (pages[0] < 1 || pages[pages.length - 2] > 2000000000)
                System.out.println("Invalid bounds (not between 1 and 2000000000)");

            // There will be at most 20 pages (take the last 0 into account)
            if (pages.length > 21)
                System.out.println("There are more than 20 pages (excluding the 0 at the end)");

            // Check if everything is in ascending order and unique
            for (int i = 1; i < pages.length - 1; i++)
                if (pages[i - 1] >= pages[i])
                    System.out.printf("pages[%d] (= %d) is not in ascending order or is not unique.\n", i, pages[i]);
            /* --------------------------------- */

            int start = 0;
            int end = 0;

            String result = "";
            for (int i = 1; i < pages.length; i++) {
                // If it's consecutive, update the end
                if (pages[i - 1] + 1 == pages[i])
                    end = i;
                else {
                    if (start < end)
                        result += pages[start] + "-" + pages[end] + ",";
                    else
                        result += pages[start] + ",";

                    start = end + 1;
                    end = start;
                }
            }
            // Remove the last ","
            result = result.substring(0, result.length() - 1);
            System.out.println(result);
        }

        input.close();
    }
}
