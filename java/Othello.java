import java.util.*;
import java.io.*;

/**
 * This class finds the place where it can flip the opponent's
 * token the most in the game of Othello.
 * @author Naoki Mizuno
 * @version 1.4.1
 */

public class Othello {
    public static final int BOARD_SIZE = 8;
    public static final char EMPTY_SQUARE = '.';
    public static final char WHITE_SQUARE = 'W';
    public static final char BLACK_SQUARE = 'B';
    public static final int WHITE = 1;
    public static final int BLACK = -1;
    public static String WHITE_PLAYER_NAME = "White";
    public static String BLACK_PLAYER_NAME = "Black";
    // This is used to avoid confusion when indicating the other player and
    // is used like "otherPlayer = player * OTHER_PLAYER"
    public static final int OTHER_PLAYER = -1;

    private int player;
    private int prev_i = -1;
    private int prev_j = -1;
    private char[][] board = new char[BOARD_SIZE][BOARD_SIZE];
    private int[][] flippable = new int[BOARD_SIZE][BOARD_SIZE];

    /**
     * If an object is created without a parameter, it assumes that the first
     * player is white.
     */
    public Othello() {
        this(1);
    }

    /**
     * Specifies the first player and prepares the board for a new game. It is
     * up to the user whether or not to "initialize" the board with 4 tokens
     * already placed on the board.
     */
    public Othello(int player) {
        this.player = player;
        clearBoard();
        init(flippable);
    }

    /**
     * Given a Scanner object, this constructor reads from the input.
     */
    public Othello(File file) {
        try {
            Scanner in = new Scanner(file);
            // First line must be 1 letter long or either "white" or "black"
            String firstLine = in.nextLine().toUpperCase();
            if (in.hasNext() && (firstLine.length() == 1
                        || firstLine.equals("WHITE")
                        || firstLine.equals("BLACK"))) {
                this.player = firstLine.charAt(0) == 'W'
                    || firstLine.charAt(0) == WHITE_SQUARE ? WHITE : BLACK;
                readBoard(in);
            }
            else {
                this.player = 1;
                readBoard(new Scanner(file));
            }

            init(flippable);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Othello(String playerAndBoard) {
        this.player = playerAndBoard.charAt(0) == 'W'
            || playerAndBoard.charAt(0) == WHITE_SQUARE ? WHITE : BLACK;

        int cnt = 1;
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (playerAndBoard.charAt(cnt) == '\n')
                    j--;
                else
                    board[i][j] = playerAndBoard.charAt(cnt++);
            }
        }
        init(flippable);
    }

    /**
     * Reads in the current board from a given Scanner object.
     * Because Java doesn't have a way to read in character-by-character,
     * it uses "" as the delimiter to read in each letter. j is decremented
     * when hitting the endline.
     * TODO: Think about reading in each line, then separating by each character
     * @param in A Scanner object to read in from.
     */
    private void readBoard(Scanner in) {
        in.useDelimiter("");
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (!in.hasNext())
                    break;

                char letter = in.next().charAt(0);
                if (letter == '\n')
                    j--;
                else
                    board[i][j] = letter;
            }
        }
    }

    /**
     * When no parameter is given, it assumes that the current turn is
     * whatever is in "player".
     */
    public boolean flip() {
        return flip(this.player);
    }

    /**
     * Given which player turn it is, this method decides the best place to
     * put the token, which is where the most token of the opponent's can be
     * flipped. If the number of tokens that can be flipped are same at more
     * than 1 place, this method prefers the left-most position. If there are
     * more than 1 such place, it prefers the top-most position.
     * @param player Which player's turn it is.
     * @return True if a token was flipped. False if the player had to pass.
     */
    public boolean flip(int player) {
        // Quick check to see if there's at least 1 empty place remaining
        if (!isFlippable(player))
            return false;

        // Fill in the 2D array for this player
        for (int i = 0; i < BOARD_SIZE; i++)
            for (int j = 0; j < BOARD_SIZE; j++)
                flippable[i][j] = checkFlippable(i, j, player);

        int max_i = -1;
        int max_j = -1;
        int max = 0;
        // Now, find where the most can be flipped
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (flippable[i][j] > max) {
                    max_i = i;
                    max_j = j;
                    max = flippable[i][j];
                }
            }
        }
        // If there's no valid place
        if (max_i == -1 || max_j == -1)
            return false;
        else {
            prev_i = max_i;
            prev_j = max_j;
        }
        // Backup
        int maxInsideX = max_i;
        int maxInsideY = max_j;

        int max_edge = checkEdge(max);
        if (max_edge == 0) {
            max_i = maxInsideX;
            max_j = maxInsideY;
        }

        flipStartingAt(max_i, max_j, player);

        // Finally, return true if at least token was flipped
        boolean hasChanged = false;
        for (int i = 0; i < BOARD_SIZE; i++)
            for (int j = 0; j < BOARD_SIZE; j++)
                if (flippable[i][j] != 0)
                    hasChanged = true;
        init(flippable);
        return hasChanged;
    }

    /**
     * Given the position, flip all the opponent's token
     * that's between the place the player is placing his/her token and the
     * other token of his/hers that will sandwich the opponent's token(s)
     * that's in between.
     * @param start_i The x-position to start from.
     * @param start_j The y-position to start from.
     * @param player Which player's turn it is.
     */
    private void flipStartingAt(int start_i, int start_j, int player) {
        // First, get the symbol
        char thisPlayer = player == WHITE ? WHITE_SQUARE : BLACK_SQUARE;
        char otherPlayer = player * OTHER_PLAYER == WHITE
            ? WHITE_SQUARE : BLACK_SQUARE;

        // First, change the place we're placing the token
        board[start_i][start_j] = thisPlayer;
        // Make a copy of the starting position
        int x = start_i;
        int y = start_j;

        for (int vectorX = -1; vectorX <= 1; vectorX++) {
            for (int vectorY = -1; vectorY <= 1; vectorY++) {
                if (vectorX == 0 && vectorY == 0)
                    continue;

                boolean hasAtLeastOne = false;
                while (x + vectorX >= 0 && x + vectorX < BOARD_SIZE
                        && y + vectorY >= 0 && y +vectorY < BOARD_SIZE) {
                    x += vectorX;
                    y += vectorY;

                    if (board[x][y] == otherPlayer)
                        hasAtLeastOne = true;
                    else if (hasAtLeastOne && board[x][y] == thisPlayer) {
                        // Flip while coming back
                        do {
                            x -= vectorX;
                            y -= vectorY;
                            board[x][y] = thisPlayer;
                        } while (board[x - vectorX][y - vectorY] == otherPlayer);
                        break;
                    }
                    else
                        break;
                }
                // Reset to starting position
                x = start_i;
                y = start_j;
            }
        }
    }

    /**
     * Same as checkFlippable(int, int, int) but uses current player as the
     * third parameter.
     * @param i The vertical index of the position.
     * @param j The horizontal index of the position.
     * @return The maximum number of tokens that can be flipped.
     */
    public int checkflippable(int i, int j) {
        return checkFlippable(i, j, this.player);
    }

    /**
     * Given the indices and the player (either black or white),
     * this method returns the maximum number of tokens that can be flipped
     * and the direction to move to acheve that.
     * Note that this method only finds how many tokens can be flipped and
     * doesn't actually change the board.
     * @param x Vertical index specified by the user.
     * @param y Horizontal index specified by the user.
     * @param player Which turn it is (WHITE or BLACK).
     * @return The number of opponent's token that can be flipped at maximum.
     */
    public int checkFlippable(int x, int y, int player) {
        // Can't place anything if something's already there so return 0
        if (board[x][y] != EMPTY_SQUARE)
            return 0;

        int num = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                // Don't even have to check
                if (i == 0 && j == 0)
                    continue;
                num += checkFlippableInDirection(x, y, i, j, player);
            }
        }

        return num;
    }

    /**
     * Given the indices and the vector in which the program should look at,
     * this method returns how many tokens can be flipped in the direction
     * specified. Returns zero if none can be flipped.
     * Third and fourth parameters represents the vector in x, y directions,
     * respectively. These should be -1, 0, or 1.
     * For example, given the following simplified board when
     * it's black's turn, this method returns 1 to a method call with
     * parameters (2, 1, 0, 1)
     * .....
     * .W...
     * .*WW.
     * @param x Vertical index of position.
     * @param y Horizontal index of position.
     * @param vectorX x vector of the direction, positive indicating "right"
     * @param vectorY y vector of the direction, positive indicating "down"
     * @param player Which player's view.
     */
    private int checkFlippableInDirection(int x, int y, int vectorX, int vectorY, int player) {
        if (board[x][y] != EMPTY_SQUARE)
            return 0;

        int tokenFlippable = 0;
        while (x + vectorX >= 0 && x + vectorX < BOARD_SIZE
                && y + vectorY >= 0 && y +vectorY < BOARD_SIZE) {
            x += vectorX;
            y += vectorY;

            // Get the symbol
            char thisPlayer = player == WHITE ? WHITE_SQUARE : BLACK_SQUARE;
            char otherPlayer = player * OTHER_PLAYER == WHITE
                ? WHITE_SQUARE : BLACK_SQUARE;

            if (board[x][y] == otherPlayer)
                tokenFlippable++;
            else if (tokenFlippable != 0 && board[x][y] == thisPlayer)
                return tokenFlippable;
            else
                break;
        }
        // Coming here means that the index(es) went out of the board
        return 0;
    }

    /**
     * Checks the board's edge and corners and use that place if at least 1
     * token can be flipped. Returns 0 if nothing on the edge can be flipped.
     * @param max The maximum found in the inside of the board.
     * @return Maximum number of tokens that can be flipped that is on the edge.
     */
    private int checkEdge(int max) {
        int max_edge = 0;
        // First, check the edge
        for (int i = 0; i < BOARD_SIZE - 1; i++) {
            if (flippable[0][i] != 0 && flippable[0][i] > max_edge) {
                prev_i = 0;
                prev_j = i;
                max_edge = flippable[0][i];
            }
            if (flippable[BOARD_SIZE - 1][i] != 0 && flippable[BOARD_SIZE - 1][i] > max_edge) {
                prev_i = BOARD_SIZE - 1;
                prev_j = i;
                max_edge = flippable[BOARD_SIZE - 1][i];
            }
            if (flippable[i][0] != 0 && flippable[i][0] > max_edge) {
                prev_i = i;
                prev_j = 0;
                max_edge = flippable[0][i];
            }
            if (flippable[i][BOARD_SIZE - 1] != 0 && flippable[i][BOARD_SIZE - 1] > max_edge) {
                prev_i = i;
                prev_j = BOARD_SIZE - 1;
                max_edge = flippable[i][BOARD_SIZE - 1];
            }
        }
        // Now check the corners
        int max_corner = 0;
        if (flippable[BOARD_SIZE - 1][BOARD_SIZE - 1] != 0 && flippable[BOARD_SIZE - 1][BOARD_SIZE - 1] > max_corner) {
            prev_i = BOARD_SIZE - 1;
            prev_j = BOARD_SIZE - 1;
            max_corner = flippable[BOARD_SIZE - 1][BOARD_SIZE - 1];
        }
        if (flippable[BOARD_SIZE - 1][0] != 0 && flippable[BOARD_SIZE - 1][0] > max_corner) {
            prev_i = BOARD_SIZE - 1;
            prev_j = 0;
            max_corner = flippable[BOARD_SIZE - 1][0];
        }
        if (flippable[0][BOARD_SIZE - 1] != 0 && flippable[0][BOARD_SIZE - 1] > max_corner) {
            prev_i = 0;
            prev_j = BOARD_SIZE - 1;
            max_corner = flippable[0][BOARD_SIZE - 1];
        }
        if (flippable[0][0] != 0 && flippable[0][0] > max_corner) {
            prev_i = BOARD_SIZE - 1;
            prev_j = BOARD_SIZE - 1;
            max_corner = flippable[0][0];
        }

        return max_corner != 0 ? max_corner : max_edge;
    }

    /**
     * A method that places the token at the place where most
     * tokens can be flipped, and then switch turns.
     * NOTE: It is up to the user of this class whether or not to check if it's even
     * possible for the player to place a token (use checkflippable() to check).
     * @return Whether or not the placing succeeded. False if the player passed.
     */
    public boolean place() {
        boolean ret = flip();
        switchTurns();
        return ret;
    }

    /**
     * Same as place(int, int, int) but uses the current player as the third
     * parameter.
     * NOTE: It is up to the user of this class whether or not to check if it's even
     * possible for the player to place a token (use checkflippable() to check).
     * @param i The vertical index of the position.
     * @param j The horizontal index of the position.
     * @return Whether or not the current player was able to place the token.
     */
    public boolean place(int i, int j) {
        return place(i, j, this.player);
    }

    /**
     * Places the token to the specified position.
     * NOTE: It is up to the user of this class whether or not to check if it's even
     * possible for the player to place a token (use checkflippable() to check).
     * @param i The vertical index of the position.
     * @param j The horizontal index of the position.
     * @param player Which player's turn it is.
     * @return True if placing succeeded. False if player skipped.
     */
    public boolean place(int i, int j, int player) {
        if (!isFlippable(i, j, player))
            return false;
        flipStartingAt(i, j, player);
        switchTurns();
        return true;
    }

    public boolean isFlippable() {
        return isFlippable(this.player);
    }

    /**
     * Checks if a player has a valid place to put the token.
     * @return True if there is at least 1 place in the board where the player can put his/her token.
     */
    public boolean isFlippable(int player) {
        for (int i = 0; i < BOARD_SIZE; i++)
            for (int j = 0; j < BOARD_SIZE; j++)
                if (isFlippable(i, j, player))
                    return true;
        return false;
    }

    /**
     * Same as checkFlippable() method but returns boolean.
     * @param i The vertical index of the position.
     * @param j The horizontal index of the position.
     * @return True if at least 1 token can be flipped. False is zero.
     */
    public boolean isFlippable(int i, int j) {
        return isFlippable(i, j, this.player);
    }

    /**
     * Same as checkFlippable() method but returns boolean.
     * @param i The vertical index of the position.
     * @param j The horizontal index of the position.
     * @param player Which player's turn it is.
     * @return True if at least 1 token can be flipped. False is zero.
     */
    public boolean isFlippable(int i, int j, int player) {
        return checkFlippable(i, j, player) != 0;
    }

    /**
     * Checks if the game is continuable. The game is no continuable when
     * neither player has a valid place to put the token.
     * @return True if the game is continuable, false if not.
     */
    public boolean isContinuable() {
        for (int i = 0; i < BOARD_SIZE; i++)
            for (int j = 0; j < BOARD_SIZE; j++)
                if (isFlippable(i, j, WHITE) || isFlippable(i, j, BLACK))
                    return true;
        return false;
    }

    /**
     * Switches turn to the other player.
     */
    public void switchTurns() {
        player *= OTHER_PLAYER;
    }

    /**
     * Returns the current player in String.
     */
    public String getPlayer() {
        return player == WHITE ? WHITE_PLAYER_NAME : BLACK_PLAYER_NAME;
    }

    /**
     * Returns the previous place that the token was placed in an array of int.
     * @return An array of length 2 with the previous position that the token was placed.
     */
    public int[] getLastChange() {
        int[] ret = {prev_i, prev_j};
        return ret;
    }

    /**
     * Returns the current state of the board.
     * @return A 2-D array representing the current state of the board.
     */
    public char[][] getBoard() {
        return board.clone();
    }

    /**
     * Simply prints out the board.
     */
    public void printBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++)
                System.out.print(board[i][j]);
            System.out.println();
        }
    }

    /**
     * Initializes the 2-dimensional array of int containing the number of
     * tokens that can be flipped during that turn.
     * @param flippable An array of int showing the number of tokens flippable.
     */
    private void init(int[][] flippable) {
        for (int i = 0; i < BOARD_SIZE; i++)
            for (int j = 0; j < BOARD_SIZE; j++)
                flippable[i][j] = 0;
    }

    /**
     * Initializes the board for a new game of Othello. It places 4 tokens at
     * the middle of the board.
     */
    public void initBoard() {
        clearBoard();
        board[BOARD_SIZE / 2 - 1][BOARD_SIZE / 2 - 1] = WHITE_SQUARE;
        board[BOARD_SIZE / 2][BOARD_SIZE / 2 - 1] = BLACK_SQUARE;
        board[BOARD_SIZE / 2][BOARD_SIZE / 2] = WHITE_SQUARE;
        board[BOARD_SIZE / 2 - 1][BOARD_SIZE / 2] = BLACK_SQUARE;
    }

    /**
     * Initializes the 2-dimensional array of char that represents the
     * current condition of the board. It initializes all the squares with '.'
     * which represents an empty square.
     */
    public void clearBoard() {
        for (int i = 0; i < BOARD_SIZE; i++)
            for (int j = 0; j < BOARD_SIZE; j++)
                board[i][j] = EMPTY_SQUARE;
    }

    /**
     * Use when debugging. Shows the "flippable" array.
     */
    private void showFlippable() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++)
                System.out.print(flippable[i][j]);
            System.out.println();
        }
    }
}
