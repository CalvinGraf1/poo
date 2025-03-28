package engine.movements;

import engine.ChessGame;
import engine.tiles.Tile;

/**
 * Represents movements
 *
 * @author Graf Calvin
 * @author Sottile Alan
 * @author Hutzli Boris
 */
public abstract class Movement {

    /** Reference tile */
    Tile tile;

    /** Game controller */
    ChessGame controller;

    /**
     * Constructor
     *
     * @param controller Instance of the game board
     * @param tile Current tile
     */
    public Movement(ChessGame controller, Tile tile) {
        this.controller = controller;
        this.tile = tile;
    }
}
