package engine.tiles;

/**
 * Represents a position on the board
 *
 * @author Graf Calvin
 * @author Sottile Alan
 * @author Hutzli Boris
 */
public class Position {

    /** X coordinate */
    private int x;

    /** Y coordinate */
    private int y;

    /** Max value */
    private final static int MAX_POS = 7;

    /** Min value */
    private final static int MIN_POS = 0;

    /**
     * Constructor
     *
     * @param x X-axis position
     * @param y Y-axis position
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the position on the X axis
     *
     * @return Position of x
     */
    public int getX() {
        return x;
    }

    /**
     * Determines the position on the X axis
     *
     * @param x New value of x
     */
    public void setX(int x) {
        if(x < MIN_POS || x > MAX_POS) return;
        this.x = x;
    }

    /**
     * Returns the position on the Y axis
     *
     * @return Position of y
     */
    public int getY() {
        return y;
    }

    /**
     * Determines the position on the Y axis
     *
     * @param y New value of y
     */
    public void setY(int y) {
        if(y < MIN_POS || y > MAX_POS) return;
        this.y = y;
    }
}
