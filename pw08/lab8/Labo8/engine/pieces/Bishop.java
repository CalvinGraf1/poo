package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.ChessGame;
import engine.movements.DiagonalMovement;

/**
 * Represents the part corresponding to the bishop
 *
 * @author Graf Calvin
 * @author Sottile Alan
 * @author Hutzli Boris
 */
public class Bishop extends Piece {

    public Bishop(ChessGame controller, PlayerColor color) {
        super(controller, color);
        pieceType = PieceType.BISHOP;
    }

    @Override
    public void getMovementPositions() {
        super.getMovementPositions();
        DiagonalMovement diagonal = new DiagonalMovement(controller, tile);
        attackingTiles.addAll(diagonal.possibleMovements(ChessGame.SIZE_GAMEBOARD - 1));
    }
}