import java.util.*;

// Program 3 for CSE174
// a.k.a. Match-up Program
// Made by Naoki Mizuno
// ver 1.2.2 (November 27th, 2012)

public class mizunon_Program_03 {
    // Default board size is 4 x 4
    static int[][] board = new int[4][4];
    // Debugging mode
    static boolean debug = false;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Debugging Mode? (y/N) ");
        if (input.nextLine().toLowerCase().equals("y"))
            debug = true;

        boolean continue_loop = true;
        while (continue_loop) {
            switch (menu()) {
                case 1:
                    startGame();            break;
                case 2:
                    board = changeSize();   break;
                case 3:
                    readMe();               break;
                case 4:
                    continue_loop = false;
            }
        }
    }

    public static void startGame() {
        clearBoard();
        initializeBoard();

        do {
            drawBoard();

            try {
                setMark( getPlace() );
            }
            catch (Exception e) {
                System.out.println("It seems like you entered the wrong position!");
            }

        } while (!checkWinner());
    }

    public static int menu() {
        Scanner input = new Scanner(System.in);

        System.out.println("+----------------------+");
        System.out.println("| 1. Start New Game    |");
        System.out.println("| 2. Change Board Size |");
        System.out.println("| 3. Read Me           |");
        System.out.println("| 4. Exit              |");
        System.out.println("+----------------------+");
        System.out.printf("Current Board Size: %d x %d\n\n", board[0].length, board.length);

        System.out.print("Choice: ");
        int choice = input.nextInt();
        input.nextLine();

        return choice;
    }

    public static int[][] changeSize() {
        Scanner input = new Scanner(System.in);

        int width, height;
        while (true) {
            System.out.print("Resize to (separate by space): ");
            width = input.nextInt();
            height = input.nextInt();
            if (width * height % 2 == 0 && width * height >= 2)
                break;

            System.out.println("Remember, width * height must be even and greater than one!");
        }

        int[][] ret = new int [height][width];

        return ret;
    }

    public static void drawBoard() {
        if (debug) {
            System.out.println("#########################");
            System.out.println("# DEBUGGING MODE IS ON! #");
            System.out.println("#########################");
        }

        String[] w_scale = new String[board[0].length];
        String[] h_scale = new String[board.length];
        // The character that is actually drawn
        char draw_this;

        getScale(w_scale, "w");
        getScale(h_scale, "h");

        // Top Scale
        System.out.print("     ");
        for (int i = 0; i < board[0].length; i++)
            System.out.printf("%4s", w_scale[i]);

        // Line below that
        System.out.println();
        System.out.print("     ");
        for (int i = 0; i < board[0].length; i++)
            System.out.print("----");
        System.out.println();

        // Left scale and the content
        for (int i = 0; i < board.length; i++) {
            System.out.printf("%3s |", h_scale[i]);

            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] > 0) {
                    draw_this = '.';
                    System.out.printf("%4s", draw_this);
                }
                else if (abs(board[i][j]) <= 'z' - 'a' + 1) {
                    draw_this = (char)('a' + abs(board[i][j]) - 1);
                    System.out.printf("%4s", draw_this);
                }
                else
                    System.out.printf("%4d", abs(board[i][j]));
            }

            System.out.println();
        }
        System.out.println();


        // For debugging purpose
        if (debug) {
            for (int i = 0; i < board.length; i++) {
                System.out.printf("%3s | ", h_scale[i]);

                for (int j = 0; j < board[0].length; j++) {
                    if (abs(board[i][j]) <= 'z' - 'a' + 1) {
                        draw_this = (char)('a' + abs(board[i][j]) - 1);
                        System.out.printf("%3s", draw_this);
                    }
                    else
                        System.out.printf("%3d", abs(board[i][j]));
                    // Add ! if it's matched
                    if (board[i][j] < 0)
                        System.out.print("!");
                    else
                        System.out.print(" ");
                }

                System.out.println();
            }
            // Line
            System.out.print("     ");
            for (int i = 0; i < board[0].length; i++)
                System.out.print("----");
            System.out.println();
            // Bottom Scale
            System.out.print("     ");
            for (int i = 0; i < board[0].length; i++)
                System.out.printf("%4s", w_scale[i]);
            System.out.println();
        }
        // END OF DEBUGGING
    }

    public static int abs(int num) {
        return (num > 0) ? num : num * -1;
    }

    public static void getScale(String[] scale, String w_or_h) {
        String[] front_letter = {"", "a", "s", "d", "f"};
        String[] last_letter = {"a", "s", "d", "f"};

        // If width was sent over
        if (w_or_h.charAt(0) == 'w') {
            front_letter[1] = last_letter[0] = "j";
            front_letter[2] = last_letter[1] = "k";
            front_letter[3] = last_letter[2] = "l";
            front_letter[4] = last_letter[3] = ";";
        }

        int count = 0;
        for (int i = 0; i < front_letter.length; i++) {
            for (int j = 0; j < front_letter.length; j++) {
                for (int k = 0; k < last_letter.length; k++) {
                    scale[count++] = front_letter[i] + front_letter[j] + last_letter[k];
                    if (count == scale.length)
                        return;
                }
            }
        }
    }

    public static int[] getPlace() {
        Scanner input = new Scanner(System.in);

        System.out.print("Place marker separated by space (e.g. \"js la\"): ");
        String user_input = input.nextLine().trim();

        int[] ret = new int[2];
        int word_separator = 0;

        for (int ret_index = 0; ret_index < 2; ret_index++) {
            String[] w_pos = new String[3];
            String[] h_pos = new String[3];

            int w_count = 0;
            int h_count = 0;
            while (word_separator < user_input.length()) {
                char c = user_input.charAt(word_separator);
                // Break if it's the separator (which is space)
                if (c == ' ') {
                    // Move cursor to the next letter in
                    // order for the next loop to start smoothly
                    word_separator++;
                    break;
                }
                else if ((c == 'j' || c == 'k' || c == 'l' || c == ';')
                        && w_count < w_pos.length)
                    w_pos[w_count++] = String.valueOf(c);
                else if ((c == 'a' || c == 's' || c == 'd' || c == 'f')
                        && h_count < h_pos.length)
                    h_pos[h_count++] = String.valueOf(c);
                word_separator++;
            }

            int w_index = 0;
            for (int i = 0; i < w_pos.length; i++) {
                if (w_pos[i] == null)
                    break;
                else if (w_pos[i].equals("j"))
                    w_index = 4 * w_index + 1;
                else if (w_pos[i].equals("k"))
                    w_index = 4 * w_index + 2;
                else if (w_pos[i].equals("l"))
                    w_index = 4 * w_index + 3;
                else if (w_pos[i].equals(";"))
                    w_index = 4 * w_index + 4;
                else
                    break;
            }
            w_index--;

            int h_index = 0;
            for (int i = 0; i < h_pos.length; i++) {
                if (h_pos[i] == null)
                    break;
                else if (h_pos[i].equals("a"))
                    h_index = 4 * h_index + 1;
                else if (h_pos[i].equals("s"))
                    h_index = 4 * h_index + 2;
                else if (h_pos[i].equals("d"))
                    h_index = 4 * h_index + 3;
                else if (h_pos[i].equals("f"))
                    h_index = 4 * h_index + 4;
            }
            h_index--;

            ret[ret_index] = h_index * 100 + w_index;
        }

        // Return value can be inturpreted as
        // WWXXYYZZ where user selected board[WW][XX] board[YY][ZZ]
        return ret;
    }

    public static boolean checkWinner() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] > 0)
                    return false;
            }
        }
        System.out.println("CONGRATULATIONS!!!\nYou Win!!!!!");
        return true;
    }

    public static void clearBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++)
                board[i][j] = 0;
        }
    }

    public static void setMark(int[] place) throws Exception {
        int width_val1 = place[0] % 100;
        int height_val1 = (int)(place[0] / 100);
        int width_val2 = place[1] % 100;
        int height_val2 = (int)(place[1] / 100);

        if (place[0] == place[1]) {
            System.out.println("Those are the same!");
            return;
        }
        if (board[height_val1][width_val1] < 0 || board[height_val2][width_val2] < 0) {
            System.out.println("You already got one or both places correct!");
            return;
        }

        // If the content of the 2 places match
        if (board[height_val1][width_val1] == board[height_val2][width_val2]) {
            // Change the value in the board to negative and mark as "found"
            board[height_val1][width_val1] *= -1;
            board[height_val2][width_val2] *= -1;
        }
    }

    public static void initializeBoard() {
        // 1st loop: (int)(2 / 2) = 1
        // 2nd loop: (int)(3 / 2) = 1
        // 3rd loop: (int)(4 / 2) = 2 ...and so on
        int num = 2;

        for (int i = 0; i < board.length * board[0].length; i++) {
                int random_i = (int)(Math.random() * board.length);
                int random_j = (int)(Math.random() * board[0].length);

                if (board[random_i][random_j] == 0) {
                    board[random_i][random_j] = (int)(num / 2);
                    board[random_i][random_j] = (int)(num / 2);
                    num++;
                    continue;
                }
                else
                    // Do loop again
                    i--;
        }
    }

    public static void readMe() {
        System.out.println("  _____                _   __  __");
        System.out.println(" |  __ \\              | | |  \\/  |");
        System.out.println(" | |__) |___  __ _  __| | | \\  / | ___ ");
        System.out.println(" |  _  // _ \\/ _` |/ _` | | |\\/| |/ _ \\");
        System.out.println(" | | \\ \\  __/ (_| | (_| | | |  | |  __/");
        System.out.println(" |_|  \\_\\___|\\__,_|\\__,_| |_|  |_|\\___|");
        System.out.println();
        System.out.println();
        System.out.println("How to use");
        System.out.println("    If you want to change the board size, choose 2 in the menu.");
        System.out.println("    Make sure to separate the 2 numbers when typing. (e.g. 10 10)");
        System.out.println("    Also, make the product even so that the program can make pairs of");
        System.out.println("    letters and numbers.");
        System.out.println();
        System.out.println("    Make sure to separate the position (e.g. aj ks). It doesn't matter if");
        System.out.println("    you type the rows first or the columns first.");
        System.out.println("    For example,");
        System.out.println("        'aj ks' and 'ks aj' are same.");
        System.out.println("    Additionally,");
        System.out.println("        'kaka ja;f' will be recognized as 'kkaa j;af' (meaning you don't have");
        System.out.println("        to type exactly as shown in the scale as long as you keep the order of");
        System.out.println("    appearance)");
        System.out.println();
        System.out.println("    You can turn on the debugging mode at the beginning to show the data");
        System.out.println("    that the program keeps track of will be displayed.");
        System.out.println("    In debugging mode, the table displayed on the bottom is the one that");
        System.out.println("    the computer keeps track of. '!' is added to the matched letters / numbers");
        System.out.println();
        System.out.println("Features");
        System.out.println("    1. Board size can be up to 68x68 (and it's relatively easy to expand)");
        System.out.println("    2. Using asdf and jkl; instead of numbers and letters");
        System.out.println("    3. Determines position from the order of appearance (as mentioned above)");
        System.out.println("    4. Doesn't crach when user inputs invalid position");
        System.out.println("    5. Debugging mode");
        System.out.println();
    }
}
