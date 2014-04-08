import java.util.*;

/**
 * Guts is a game in which each player is dealt 2 cards and all players
 * immediately compare their card to determine a winnder. All players hands
 * are compared and the winner is determined by the player holding the highest
 * "pair". If no player holds a pair, the winner is determined by the player
 * holding the highest card. If players have the same high card, the value of
 * their other card is used to determine the winner. If even that doesn't
 * determine the winner, then the game is a tie.
 */

public class Guts {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int gameNumber = 1;
        int n;
        do {
            // Number of players
            n = input.nextInt();
            if (n == 0)
                break;
            input.nextLine();
            int[][] scores = new int[n][2];

            for (int i = 0; i < n; i++) {

                String line = input.nextLine();
                // Since we know the format is like 2H 2D,
                // first and third letters are the number
                String num = line.substring(0, 1);
                scores[i][0] = value(num);

                num = line.substring(3, 4);
                scores[i][1] = value(num);

                // Reorder it so that the first one is the higher
                if (scores[i][0] < scores[i][1]) {
                    int tmp = scores[i][0];
                    scores[i][0] = scores[i][1];
                    scores[i][1] = tmp;
                }
            }

            // Start comparing
            boolean isTie = false;
            // {player #, higher card, lower card}
            int[] win = new int[3];
            // Set the first to the first player and compare that
            // with the other players
            win[0] = 1;
            win[1] = scores[0][0] > scores[0][1] ? scores[0][0] : scores[0][1];
            win[2] = scores[0][0] < scores[0][1] ? scores[0][0] : scores[0][1];

            for (int i = 1; i < n; i++) {
                // If it's a pair
                if (scores[i][0] == scores[i][1]
                        // Is the current strongest hand a pair?
                        && win[1] == win[2]
                        // If so, is this score higher or equal?
                        && win[1] <= scores[i][0]) {
                    // Has it become a tie?
                    if (win[1] == scores[i][0])
                        isTie = true;
                    // There's only one strongest
                    else {
                        // Update strongest player
                        win[0] = i + 1;
                        win[1] = scores[i][0];
                        win[2] = scores[i][1];
                        // Don't worry about being a tie... for now
                        isTie = false;
                    }
                }
                else {
                    // If current strongest hand is a pair,
                    // there is no need to compare something that
                    // is not a pair
                    if (win[1] == win[2])
                        continue;
                    if (win[1] <= scores[i][0]) {
                        // Has it become a tie?
                        if (win[1] == scores[i][0])
                            isTie = true;
                        // There's only one strongest
                        else {
                            // Update strongest player
                            win[0] = i + 1;
                            win[1] = scores[i][0];
                            win[2] = scores[i][1];
                            // Don't worry about being a tie... for now
                            isTie = false;
                        }
                    }
                }
            }

            if (isTie)
                System.out.printf("Game #%d: Tie\n", gameNumber);
            else
                System.out.printf("Game #%d: Player %d\n", gameNumber, win[0]);
        } while (n > 0);

        input.close();
    }

    public static int value(String num) {
        int ret;
        if (num.equals("A"))
            ret = 14;
        else if (num.equals("K"))
            ret = 13;
        else if (num.equals("Q"))
            ret = 12;
        else if (num.equals("J"))
            ret = 11;
        else if (num.equals("T"))
            ret = 10;
        else
            ret = Integer.parseInt(num);

        return ret;
    }
}