import java.util.Scanner;

/**
 * In order to score in darts, you need to hit section #1 in order to get
 * credit for section #2. For example, hitting section # 2, 3, 4, 5 won't be
 * valid unless you have hit section #1 before. 5, 3, 1, 4, 2, 4, 3 would
 * become a score of 3. Input is the format shown below
 * 3
 *  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17 18 19 20
 * 20 19 18 17 16 15 14 13 12 11 10  9  8  7  6  5  4  3  2  1
 *  4  2  1  4  2  3  5  6  4  5  6  7  9  8  9 10 10  9 11 13
 * 
 * And the answer is
 * #1: 20
 * #2: 1
 * #3: 11
 */

public class Darts {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int n = input.nextInt();
        input.nextLine();

        for (int i = 0; i < n; i++) {
            int[] score = new int[20];
            for (int j = 0; j < 20; j++)
                score[j] = input.nextInt();
            input.nextLine();

            int upto = 0;
            int pScore = 0;
            for (int j = 0; j < score.length; j++) {
                if (score[j] == upto + 1) {
                    pScore = score[j];
                    upto++;
                }
            }
            System.out.println("#" + (i + 1) + ": " + pScore);
        }

        input.close();
    }
}