package engine.tiles;

import engine.pieces.Piece;

/**
 * Represents a tile on the board
 *
 * @author Graf Calvin
 * @author Sottile Alan
 * @author Hutzli Boris
 */
public class Tile {

    /** Tile board position */
    private final Position position;

    /** Piece on the tile */
    private Piece piece;

    /**
     * Constructor
     *
     * @param position Position of tile (x,y)
     */
    public Tile(Position position) {
        this.position = position;
        piece = null;
    }

    /**
     * Returns the piece positioned on the tile
     *
     * @return Current piece
     */
    public Piece getPiece() {
        return piece;
    }

    /**
     * Position getter
     *
     * @return Tile position
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Modifies the piece positioned on the tile
     *
     * @param p New piece
     */
    public void setPiece(Piece p) {
        this.piece = p;

        if (p != null)
            p.setTile(this);
    }
}
