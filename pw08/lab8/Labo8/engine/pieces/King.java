package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.ChessGame;
import engine.movements.DiagonalMovement;
import engine.movements.HorVerMovement;
import engine.tiles.Tile;

/**
 * Represents the part corresponding to the king
 *
 * @author Graf Calvin
 * @author Sottile Alan
 * @author Hutzli Boris
 */
public class King extends Piece {

    public King(ChessGame controller, PlayerColor color) {
        super(controller, color);
        pieceType = PieceType.KING;
    }

    @Override
    public void getMovementPositions() {
        super.getMovementPositions();
        DiagonalMovement diagonal = new DiagonalMovement(controller, tile);
        attackingTiles.addAll(diagonal.possibleMovements(1));

        HorVerMovement horVer = new HorVerMovement(controller, tile);
        attackingTiles.addAll(horVer.possibleMovements(1));

        // Instead of removing after recuperating tiles, find a way to send condition to possibleMovements

        final int x = tile.getPosition().getX();
        final int y = tile.getPosition().getY();

        // Castling
        if (hasMoved) return;

        // Right side
        boolean rightCastle = true;
        for (int i = x + 1; i < ChessGame.SIZE_GAMEBOARD - 1; ++i) {
            Tile nextTile = controller.getTile(i, y);
            if (nextTile.getPiece() != null || controller.tileAttacked(getColor(), nextTile))
                rightCastle = false;
        }

        if (rightCastle) {
            Piece piece = controller.getTile(ChessGame.SIZE_GAMEBOARD - 1, y).getPiece();
            if (piece != null && !piece.hasMoved) {
                movementTiles.add(controller.getTile(x + 2, y));
                controller.addCastlingTile(controller.getTile(x + 2, y), controller.getTile(ChessGame.SIZE_GAMEBOARD - 1, y), controller.getTile(x + 1, y));
            }

        }

        // Left side
        for (int i = x - 1; i > 0; --i) {
            Tile nextTile = controller.getTile(i, y);
            if (nextTile.getPiece() != null || controller.tileAttacked(getColor(), nextTile))
                return;
        }
        Piece piece = controller.getTile(0, y).getPiece();
        if (piece != null && !piece.hasMoved) {
            movementTiles.add(controller.getTile(x - 2, y));
            controller.addCastlingTile(controller.getTile(x - 2, y), controller.getTile(0, y), controller.getTile(x - 1, y));
        }
    }
}
