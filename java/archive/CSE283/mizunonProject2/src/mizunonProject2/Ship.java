package mizunonProject2;

/**
 * This class represents one ship. x and y represents the most top-left grid of
 * the ship.
 * 
 * @author Naoki Mizuno
 * 
 */

public class Ship {
    private int x;
    private int y;
    private int length;
    private boolean alignNorthSouth;
    private int remainingGrids;

    public Ship(int x, int y, int length, boolean alignNorthSouth) {
        this.x = x;
        this.y = y;
        this.length = length;
        this.alignNorthSouth = alignNorthSouth;

        remainingGrids = length;
    }

    public void hit() {
        remainingGrids--;
    }

    /**
     * Changes the given board so that it reflects the sunk ship.
     * 
     * @param board
     *            The board.
     */
    public void sink(int[][] board) {
        int i = x;
        int j = y;
        for (int k = 0; k < length; k++) {
            board[j][i] = BattleshipGame.STATUS_SUNK;

            if (alignNorthSouth)
                j++;
            else
                i++;
        }
    }

    public boolean isSunk() {
        return remainingGrids <= 0;
    }

    /**
     * Returns whether this ship is hit by the attack to the given coordinate.
     * 
     * @param i
     *            The x-coordinate of the attack.
     * @param j
     *            The y-coordinate of the attack.
     * @return True if hit, false if not.
     */
    public boolean isHit(int i, int j) {
        if (alignNorthSouth)
            return x == i && j >= y && j < y + length;
        else
            return i >= x && i < x + length && j == y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public boolean isAlignNorthSouth() {
        return alignNorthSouth;
    }

    public void setAlignNorthSouth(boolean alignNorthSouth) {
        this.alignNorthSouth = alignNorthSouth;
    }

    public int getRemainingGrids() {
        return remainingGrids;
    }

    public void setRemainingGrids(int remainingGrids) {
        this.remainingGrids = remainingGrids;
    }
}