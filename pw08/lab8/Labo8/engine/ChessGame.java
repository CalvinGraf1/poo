package engine;

import chess.ChessController;
import chess.ChessView;
import chess.PlayerColor;

import engine.tiles.*;
import engine.pieces.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Chess game controller
 *
 * @author Graf Calvin
 * @author Sottile Alan
 * @author Hutzli Boris
 */
public class ChessGame implements ChessController {

    /**
     * Associates a piece with a tile, used to store older piece positions
     */
    private class PastMove {

        /** Tile */
        Tile tile;

        /** Piece */
        Piece piece;

        /**
         * Constructor
         *
         * @param tile Associated tile
         * @param piece Associated piece
         */
        public PastMove(Tile tile, Piece piece) {
            this.tile = tile;
            this.piece = piece;
        }
    }

    /** Chess view */
    private ChessView view;

    /** Size of the board's side */
    public final static int SIZE_GAMEBOARD = 8;

    /** Last piece to have moved */
    private Piece lastMover = null;

    /** Tile containing the pawn to be eaten by en-passant */
    private Tile enPassantTarget = null;

    /** Determines whether pawn is to be promoted */
    private boolean promotionAsked = false;

    /** Tiles composing the game board */
    private final Tile[][] tiles = new Tile[SIZE_GAMEBOARD][SIZE_GAMEBOARD];

    /** Tiles in which the king may castle to (elements in this list, castlingRookTiles, and castlingNewRookT
     * having the same index are associated with eachother).
     */
    private final ArrayList<Tile> castlingTiles = new ArrayList<>();

    /** Tiles containing the rooks that are to be moved for the castle */
    private final ArrayList<Tile> castlingRookTiles = new ArrayList<>();

    /** Tiles of the new rook positions after castling */
    private final ArrayList<Tile> castlingNewRookTiles = new ArrayList<>();

    /** Storing kings of each side for easy access */
    private final HashMap<PlayerColor, Piece> kings = new HashMap<>();

    /** List of moves done in the same turn */
    private ArrayList<PastMove> moveHistory = new ArrayList<>();

    /** Color whose turn it is */
    private PlayerColor turn = PlayerColor.WHITE;

    /** Queen promotion choice */
    private final ChessView.UserChoice queenPromotion = () -> "Queen";

    /** Rook promotion choice */
    private final ChessView.UserChoice rookPromotion = () -> "Rook";

    /** Bishop promotion choice */
    private final ChessView.UserChoice bishopPromotion = () -> "Bishop";

    /** Knight promotion choice */
    private final ChessView.UserChoice knightPromotion = () -> "Knight";

    /**
     * Switch player turn
     */
    private void switchTurn() {
        turn = turn == PlayerColor.WHITE ? PlayerColor.BLACK : PlayerColor.WHITE;
    }

    /**
     * Last mover getter
     *
     * @return Last piece to move
     */
    public Piece getLastMover() {
        return lastMover;
    }

    /**
     * En passant target setter
     *
     * @param tile Tile which has the pawn eaten from en passant
     */
    public void setEnPassantTarget(Tile tile) {
        enPassantTarget = tile;
    }

    /**
     * Adds castling necessary tiles needed for castling, if there is a castling option
     *
     * @param tile New king tile
     * @param rookTile Tile containing rook (before castling)
     * @param newRookTile New rook tile
     */
    public void addCastlingTile(Tile tile, Tile rookTile, Tile newRookTile) {
        castlingTiles.add(tile);
        castlingRookTiles.add(rookTile);
        castlingNewRookTiles.add(newRookTile);
    }

    /**
     * Removes all the pieces from the board
     */
    public void emptyGameBoard(){
        for (int i = SIZE_GAMEBOARD - 1; i >= 0; --i) {
            for (int j = 0; j < SIZE_GAMEBOARD; ++j) {
                tiles[j][i].setPiece(null);
            }
        }
    }

    /**
     * Puts piece in a certain position both graphically and internally
     *
     * @param piece Piece to set
     * @param toX X coordinate
     * @param toY Y coordinate
     */
    public void setNewPiece(Piece piece,int toX, int toY){
        Tile to = getTile(toX, toY);
        to.setPiece(piece);
        view.putPiece(piece.getPieceType(), piece.getColor(), toX, toY);
    }

    /**
     * Removes pieces from a tile
     *
     * @param tile Removes piece from a tile graphically and internally
     */
    public void removePiece(Tile tile) {
        tile.setPiece(null);
        view.removePiece(tile.getPosition().getX(), tile.getPosition().getY());
    }

    /**
     * Initializes board
     *
     * @param view View to use
     */
    @Override
    public void start(ChessView view) {
        this.view = view;

        // Initialising the game board with i = y, j = x
        for(int i = SIZE_GAMEBOARD - 1; i >= 0; --i) {
            for(int j = 0; j < SIZE_GAMEBOARD; ++j) {
                tiles[j][i] = new Tile(new Position(j, i));
            }
        }
        view.startView();
    }

    /**
     * Gets tile from coordinate
     *
     * @param x X coordinate
     * @param y Y coordinate
     * @return Tile in coordinate
     */
    public Tile getTile(int x, int y) {
        if (x < 0 || x >= SIZE_GAMEBOARD || y < 0 || y >= SIZE_GAMEBOARD) {
            return null;
        }

        return tiles[x][y];
    }

    /**
     * Reverts moves done in the turn
     */
    private void revertMoveChanges() {
        for (PastMove previousState : moveHistory) {
            previousState.tile.setPiece(previousState.piece);
            if (previousState.piece != null)
                view.putPiece(previousState.piece.getPieceType(), previousState.piece.getColor(), previousState.tile.getPosition().getX(), previousState.tile.getPosition().getY());
            else
                view.removePiece(previousState.tile.getPosition().getX(), previousState.tile.getPosition().getY());
        }
    }

    /**
     * Moves piece from a tile to another
     *
     * @param from Piece location
     * @param to New piece location
     */
    private void movePiece(Tile from, Tile to) {
        Piece piece = from.getPiece();
        moveHistory.add(new PastMove(from, piece));
        moveHistory.add(new PastMove(to, to.getPiece()));
        setNewPiece(piece, to.getPosition().getX(), to.getPosition().getY());
        removePiece(from);
    }

    /**
     * Asks promotion from user
     *
     * @param fromX From x coordinate
     * @param fromY From y coordinate
     * @param toX To x coordinate
     * @param toY To y coordinate
     */
    public void askingPromotion(int fromX, int fromY, int toX, int toY) {
        ChessView.UserChoice choice = view.askUser("Promotion", "En quoi voudriez-vous promouvoir le pion?", queenPromotion, rookPromotion, knightPromotion, bishopPromotion);
        promotionAsked = true;

        if (choice != null) {
            switch (choice.textValue()) {
                case "Queen":
                    setNewPiece(new Queen(this, turn),toX,toY);
                    break;
                case "Rook":
                    setNewPiece(new Rook(this, turn),toX,toY);
                    break;
                case "Knight":
                    setNewPiece(new Knight(this, turn),toX,toY);
                    break;
                case "Bishop":
                    setNewPiece(new Bishop(this, turn),toX,toY);
                    break;
                default:
                    view.displayMessage("There has been an issue while selection the promotion");
                    break;
            }

            removePiece(getTile(fromX, fromY));
            switchTurn();
        }
    }

    /**
     * Determines whether a tile is attacked by a piece of another color
     *
     * @param color Color of the defender
     * @param tile Tile to check attacked
     * @return True if tile attacked
     */
    public boolean tileAttacked(PlayerColor color, Tile tile) {
        for(int i = SIZE_GAMEBOARD - 1; i >= 0; --i) {
            for(int j = 0; j < SIZE_GAMEBOARD; ++j) {
                Piece tilePiece = tiles[j][i].getPiece();
                if(tilePiece != null && tilePiece.getColor() != color && tilePiece.attackingTile(tile)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks whether the player turn's king is in check
     *
     * @return True if in check
     */
    private boolean checkCheck() {
        return tileAttacked(turn, kings.get(turn).getTile());
    }

    /**
     * Determines whether a movement can be done
     *
     * @param fromX From x coordinate
     * @param fromY From y coordinate
     * @param toX To x coordinate
     * @param toY To y coordinate
     * @return True if the move can be made
     */
    @Override
    public boolean move(int fromX, int fromY, int toX, int toY) {
        // Clears all attributes potentially modified by a previous moce
        moveHistory.clear();
        castlingTiles.clear();
        castlingRookTiles.clear();
        castlingNewRookTiles.clear();
        enPassantTarget = null;

        Tile from = getTile(fromX, fromY);
        Tile to = getTile(toX, toY);
        Piece piece = from.getPiece();

        if (piece == null || piece.getColor() != turn || !piece.move(to)) {
            return false;
        }

        // Stores the last piece that has moved
        lastMover = piece;

        // Castling
        if (castlingTiles.contains(to)) {
          int index = castlingTiles.indexOf(to);
          Tile rookTile = castlingRookTiles.get(index);
          Tile newRookTile = castlingNewRookTiles.get(index);

          movePiece(rookTile, newRookTile);
        }

        // En-passant
        if (enPassantTarget != null && enPassantTarget.getPosition().getX() == toX) {
            //Position pawnPosition = enPassantTarget.getPosition();
            moveHistory.add(new PastMove(enPassantTarget, enPassantTarget.getPiece()));
            removePiece(enPassantTarget);
            //enPassantTarget.setPiece(null);
            //view.removePiece(pawnPosition.getX(), pawnPosition.getY());
        }

        // Promotion
        if (promotionAsked) {
            promotionAsked = false;
            return true;
        }

        movePiece(from, to);

        // Check
        if (checkCheck()) {
            revertMoveChanges();
            view.displayMessage("Check");
            return false;
        }
        switchTurn();
        return true;
    }

    /**
     * Initializes pieces
     */
    @Override
    public void newGame() {
        emptyGameBoard();

        // Assigning the piece to each tile
        for (int i = 0; i < SIZE_GAMEBOARD; ++i) {
            setNewPiece(new Pawn(this, PlayerColor.WHITE),i,1);
            setNewPiece(new Pawn(this, PlayerColor.BLACK),i,6);
        }
        setNewPiece(new Rook(this, PlayerColor.WHITE),0,0);
        setNewPiece(new Knight(this, PlayerColor.WHITE),1,0);
        setNewPiece(new Bishop(this, PlayerColor.WHITE),2,0);
        setNewPiece(new Queen(this, PlayerColor.WHITE),3,0);
        setNewPiece(new King(this, PlayerColor.WHITE),4,0);
        setNewPiece(new Bishop(this, PlayerColor.WHITE),5,0);
        setNewPiece(new Knight(this, PlayerColor.WHITE),6,0);
        setNewPiece(new Rook(this, PlayerColor.WHITE),7,0);

        setNewPiece(new Rook(this, PlayerColor.BLACK),0,7);
        setNewPiece(new Knight(this, PlayerColor.BLACK),1,7);
        setNewPiece(new Bishop(this, PlayerColor.BLACK),2,7);
        setNewPiece(new Queen(this, PlayerColor.BLACK),3,7);
        setNewPiece(new King(this, PlayerColor.BLACK),4,7);
        setNewPiece(new Bishop(this, PlayerColor.BLACK),5,7);
        setNewPiece(new Knight(this, PlayerColor.BLACK),6,7);
        setNewPiece(new Rook(this, PlayerColor.BLACK),7,7);

        turn = PlayerColor.WHITE;
        kings.put(PlayerColor.WHITE, tiles[4][0].getPiece());
        kings.put(PlayerColor.BLACK, tiles[4][7].getPiece());
    }
}
