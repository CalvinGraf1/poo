package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.ChessGame;
import engine.tiles.Tile;

/**
 * Represents the part corresponding to the pawn
 *
 * @author Graf Calvin
 * @author Sottile Alan
 * @author Hutzli Boris
 */
public class Pawn extends Piece {

    public Pawn(ChessGame controller, PlayerColor color) {
        super(controller, color);
        pieceType = PieceType.PAWN;
    }

    /** Check if the piece has already move */
    private boolean firstMove = false;

    @Override
    public void getMovementPositions() {
        super.getMovementPositions();
        final int x = tile.getPosition().getX();
        final PlayerColor color = tile.getPiece().getColor();

        int y = tile.getPosition().getY();
        if(color == PlayerColor.WHITE) y += 1;
        else y -= 1;

        Tile newTile = controller.getTile(x , y);
        if (newTile != null && newTile.getPiece() == null) movementTiles.add(newTile);

        for (int i = -1; i < 2; i += 2) {
            newTile = controller.getTile(x + i, y);

            // pour en passant
            Tile enPassantTile = controller.getTile(x + i, tile.getPosition().getY());

            if (newTile != null) {
                if (newTile.getPiece() != null && newTile.getPiece().getColor() != color)
                    attackingTiles.add(newTile);
                //movementTiles.add(newTile);

                if (enPassantTile.getPiece() != null && controller.getLastMover() == enPassantTile.getPiece() &&
                        enPassantTile.getPiece().getColor() != color &&
                        enPassantTile.getPiece().getPieceType() == PieceType.PAWN &&
                        ((Pawn)enPassantTile.getPiece()).firstMove) {
                    controller.setEnPassantTarget(enPassantTile);
                    movementTiles.add(newTile);
                }
            }
        }

        if(!hasMoved) {
            if(color == PlayerColor.WHITE) ++y;
            else --y;
            newTile = controller.getTile(x, y);
            if (newTile != null && newTile.getPiece() == null) movementTiles.add(newTile);
        }
    }

    @Override
    public boolean move(Tile newTile) {
        if (!super.move(newTile)) return false;

        // Ask promotion if the pawn is on the right tile
        if(newTile.getPosition().getY() == ChessGame.SIZE_GAMEBOARD - 1 || newTile.getPosition().getY() == 0){
            controller.askingPromotion(tile.getPosition().getX(),tile.getPosition().getY(),newTile.getPosition().getX(),
                    newTile.getPosition().getY());
        }
        firstMove = !hasMoved;
        return true;
    }
}