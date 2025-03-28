package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.ChessGame;
import engine.tiles.Tile;
import java.util.ArrayList;

/**
 * Represents a piece
 *
 * @author Graf Calvin
 * @author Sottile Alan
 * @author Hutzli Boris
 */
public abstract class Piece {

    /** Type of piece */
    PieceType pieceType;

    /** Color of piece */
    PlayerColor pieceColor;

    /** Tile where the piece is located */
    Tile tile;

    /** Game controller */
    ChessGame controller;

    /** Indicates if the piece has already moved */
    boolean hasMoved = false;

    /** List of tiles where the piece can move */
    ArrayList<Tile> movementTiles = new ArrayList<>();

    /** List of tiles where the piece can attack */
    ArrayList<Tile> attackingTiles = new ArrayList<>();

    /**
     * Constructor
     *
     * @param controller Instance of the game board
     * @param color Color of the piece
     */
    public Piece(ChessGame controller, PlayerColor color) {
        this.controller = controller;
        this.pieceColor = color;
    }

    /**
     * Setter of tile
     * @param tile Tile where the piece is located
     */
    public void setTile(Tile tile) {
        this.tile = tile;
    }

    /**
     * Getter of tile
     * @return Tile where the piece is located
     */
    public Tile getTile() {
        return tile;
    }

    /**
     * Indicate that the piece has moved
     */
    public void setMoved() {
        this.hasMoved = true;
    }

    /**
     * Getter of the type of the piece
     * @return Type of the piece
     */
    public PieceType getPieceType() {
        return pieceType;
    }

    /**
     * Getter of the color of the piece
     * @return Color of the piece
     */
    public PlayerColor getColor() {
        return pieceColor;
    }

    /**
     * Checks if this tile can be attacked
     * @param tile Tile to check
     * @return True if the tile can be attacked
     */
    public boolean attackingTile(Tile tile) {
        getMovementPositions();
        return attackingTiles.contains(tile);
    }

    /**
     * Add the tiles where the piece can move and attack to the corresponding lists
     */
    public void getMovementPositions() {
        movementTiles.clear();
        attackingTiles.clear();
    }

    /**
     * Determines if the piece can move to the new tile
     * @param newTile The tile to move to
     * @return True if the piece can move
     */
    public boolean move(Tile newTile) {
        getMovementPositions();
        return (movementTiles.contains(newTile) || attackingTiles.contains(newTile)) &&
                (newTile.getPiece() == null || newTile.getPiece().getColor() != this.pieceColor);
    }
}
 