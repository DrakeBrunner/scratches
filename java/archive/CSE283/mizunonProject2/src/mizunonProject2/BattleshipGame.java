package mizunonProject2;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class is used by both the client and the server.
 * The purpose of this class is to gather the same resources used by both
 * classes in one place.
 * 
 * @author Naoki Mizuno
 * 
 */

public class BattleshipGame {
    // Maximum number of guesses the client can make
    public static final int MAX_GUESS = 40;

    // Port used for this program
    public static final int PORT_NUMBER = 32100;

    /* Grid status */
    public static final int STATUS_EMPTY = 0;
    public static final int STATUS_DESTROYER = 1;
    public static final int STATUS_CRUISER = 2;
    public static final int STATUS_BATTLESHIP = 3;
    public static final int STATUS_AIRCRAFT_CARRIER = 4;
    public static final int STATUS_MISS = 5;
    public static final int STATUS_HIT = 6;
    public static final int STATUS_SUNK = 7;

    /* What to show in grids */
    public static final char GRID_EMPTY = '-';
    public static final char GRID_DESTROYER = 'D';
    public static final char GRID_CRUISER = 'C';
    public static final char GRID_BATTLESHIP = 'B';
    public static final char GRID_AIRCRAFT_CARRIER = 'A';
    public static final char GRID_MISS = 'M';
    public static final char GRID_HIT = 'H';
    public static final char GRID_SUNK = 'S';

    /* Attack (move) status */
    public static final int RESULT_MISS = 10;
    public static final int RESULT_HIT = 20;
    public static final int RESULT_SUNK = 30;
    public static final int RESULT_ILLEGAL = 40;

    /* Game status */
    public static final int GAME_CONTINUE = 10;
    public static final int GAME_WIN_CLIENT = 20;
    public static final int GAME_WIN_SERVER = 30;
    public static final int GAME_ILLEGAL_EXIT = 40;

    private int[][] allies;
    private int[][] enemy;
    private ArrayList<Ship> ships;

    public BattleshipGame() {
        this(10);
    }

    public BattleshipGame(int n) {
        allies = new int[n][n];
        enemy = new int[n][n];
        ships = new ArrayList<Ship>();

        initBoard();
    }

    /**
     * Initializes the board with all the grids set to EMPTY.
     */
    public void initBoard() {
        for (int j = 0; j < allies.length; j++) {
            for (int i = 0; i < allies[j].length; i++) {
                allies[j][i] = STATUS_EMPTY;
                enemy[j][i] = STATUS_EMPTY;
            }
        }
    }

    /**
     * Prints out the status of the own board.
     */
    public void printAllies() {
        printBoard(allies);
    }

    /**
     * Prints out the status of the attack.
     */
    public void printEnemy() {
        printBoard(enemy);
    }

    /**
     * Prints out the status of the given board. This method can be used to
     * display both the classes own deployment (own) and the result of attack
     * (enemy). However, there is no check whether the grids are consistent (for
     * example, no error when DESTROYER and HIT grids are on the same board).
     * The character '?' is shown when the value on the board does not match any
     * of the constants defined in this class.
     */
    private void printBoard(int[][] board) {
        for (int j = 0; j < board.length; j++) {
            System.out.print("|");

            for (int i = 0; i < board[j].length; i++) {
                // Invalid values in the board when this is shown
                char grid = '?';

                switch (board[j][i]) {
                    case STATUS_EMPTY:
                        grid = GRID_EMPTY;
                        break;
                    case STATUS_DESTROYER:
                        grid = GRID_DESTROYER;
                        break;
                    case STATUS_CRUISER:
                        grid = GRID_CRUISER;
                        break;
                    case STATUS_BATTLESHIP:
                        grid = GRID_BATTLESHIP;
                        break;
                    case STATUS_AIRCRAFT_CARRIER:
                        grid = GRID_AIRCRAFT_CARRIER;
                        break;
                    case STATUS_MISS:
                        grid = GRID_MISS;
                        break;
                    case STATUS_HIT:
                        grid = GRID_HIT;
                        break;
                    case STATUS_SUNK:
                        grid = GRID_SUNK;
                        break;
                }

                System.out.print(String.format("%c|", grid));
            }
            System.out.println();
        }
    }

    /**
     * Places a ship with the given length starting from the given coordinate.
     * This method returns false if the shipCategory is invalid.
     * 
     * @param shipCategory
     *            The category of the ship deployed.
     * @param x
     *            The x-coordinate of the most top-left grid of the ship.
     * @param y
     *            The y-coordinate of the most top-left grid of the ship.
     * @param length
     *            The length of the ship.
     * @param alignNorthSouth
     *            The direction of the ship. The ship will be placed vertically
     *            if true, horizontally if false.
     * @return True if the ship is successfully deployed. False if deployment
     *         failed. Deployment fails when trying to deploy outside war area
     *         (i.e. ship goes out of array boundary).
     */
    public boolean deployShip(int shipCategory, int x, int y,
            int length, boolean alignNorthSouth) {

        // Check if the ship can be inside array
        if (alignNorthSouth) {
            if (y + length > allies.length)
                return false;
        }
        else {
            if (x + length > allies[0].length)
                return false;
        }

        // Check if there are no ships overlapping
        int i = x;
        int j = y;
        for (int k = 0; k < length; k++) {
            if (allies[j][i] != STATUS_EMPTY)
                return false;

            if (alignNorthSouth)
                j++;
            else
                i++;
        }

        // Now actually change grid status
        i = x;
        j = y;
        for (int k = 0; k < length; k++) {
            allies[j][i] = shipCategory;
            if (alignNorthSouth)
                j++;
            else
                i++;
        }

        // Add this ship to the ArrayList
        ships.add(new Ship(x, y, length, alignNorthSouth));

        return true;
    }

    /**
     * Sets the result of the attack to the given coordinates. If the result is
     * RESULT_ILLEGAL, no change is made.
     * 
     * @param result
     *            The result of the attack. Either RESULT_HIT, RESULT_MISS, or
     *            RESULT_SUNK.
     * @param x
     *            The x-coordinate of the attack.
     * @param y
     *            The y-coordinate of the attack.
     */
    public void setResultOfAttack(int result, int x, int y) {
        // Set sink as hit
        // TODO: change all grids of the ship to 'S'
        if (result == BattleshipGame.RESULT_SUNK)
            enemy[y][x] = resultToStatus(BattleshipGame.RESULT_HIT);

        else if (result != BattleshipGame.RESULT_ILLEGAL)
            enemy[y][x] = resultToStatus(result);
    }

    /**
     * Returns the result of the attack from the enemy. Updates the map of
     * allies if the coordinate given is valid. Attacking coordinates that has
     * already been attacked results in RESULT_ILLEGAL, therefore, immediately
     * terminates the game.
     * 
     * @param x
     *            The x-coordinate (column).
     * @param y
     *            The y-coordinate (row).
     * @return The result of the attack. Either RESULT_HIT, RESULT_MISS,
     *         RESULT_SUNK, or RESULT_ILLEGAL.
     */
    public int attacked(int x, int y) {
        // Check if the given coordinate is within the board
        if (x < 0 || x >= allies.length || y < 0 || y >= allies.length)
            return RESULT_ILLEGAL;
        // Attack to a place that's already attacked is illegal
        else if (allies[y][x] == STATUS_HIT
                || allies[y][x] == STATUS_SUNK
                || allies[y][x] == STATUS_MISS)
            return RESULT_ILLEGAL;

        int result = RESULT_MISS;

        // Check each ship if it's hit or not
        Iterator<Ship> it = ships.iterator();
        while (it.hasNext()) {
            Ship ship = it.next();

            if (ship.isHit(x, y)) {
                // hit() method decrements the count. Only do that if it's the
                // first hit
                if (allies[y][x] != BattleshipGame.STATUS_HIT)
                    ship.hit();
                result = RESULT_HIT;
                // Update map
                allies[y][x] = resultToStatus(result);

                if (ship.isSunk()) {
                    result = RESULT_SUNK;
                    // Update allies map
                    ship.sink(allies);
                }

                return result;
            }
        }

        allies[y][x] = STATUS_MISS;
        return result;
    }

    /**
     * Accepts the result of an attack and converts to the status code. The
     * status GRID_EMPTY is returned when the result is invalid.
     * 
     * @param result
     *            The result of an attack. Either RESULT_HIT, RESULT_MISS, or
     *            RESULT_SUNK.
     * @return The status code. Either HIT, MISS, or SUNK. Returns EMPTY when
     *         invalid result is given.
     */
    private int resultToStatus(int result) {
        int grid = STATUS_EMPTY;

        switch (result) {
            case RESULT_HIT:
                grid = STATUS_HIT;
                break;
            case RESULT_MISS:
                grid = STATUS_MISS;
                break;
            case RESULT_SUNK:
                grid = STATUS_SUNK;
                break;
            default:
                System.err.println("Invalid result of attack");
        }

        return grid;
    }

    /**
     * Checks whether all the ships are sunk.
     * 
     * @return True if all the ships are down. False otherwise.
     */
    public boolean allShipsSunk() {
        Iterator<Ship> it = ships.iterator();
        while (it.hasNext())
            if (!it.next().isSunk())
                return false;

        return true;
    }

    public int getBoardSize() {
        return allies.length;
    }
}