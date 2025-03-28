package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.ChessGame;
import engine.movements.LMovement;

/**
 * Represents the part corresponding to the knight
 *
 * @author Graf Calvin
 * @author Sottile Alan
 * @author Hutzli Boris
 */
public class Knight extends Piece{

    public Knight(ChessGame controller, PlayerColor color) {
        super(controller, color);
        pieceType = PieceType.KNIGHT;
    }

    @Override
    public void getMovementPositions() {
        super.getMovementPositions();
        LMovement l = new LMovement(controller, tile);
        attackingTiles.addAll(l.possibleMovements());
    }
}
 