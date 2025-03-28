package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.ChessGame;
import engine.movements.DiagonalMovement;
import engine.movements.HorVerMovement;

/**
 * Represents the part corresponding to the queen
 *
 * @author Graf Calvin
 * @author Sottile Alan
 * @author Hutzli Boris
 */
public class Queen extends Piece {

    public Queen(ChessGame controller, PlayerColor color) {
        super(controller, color);
        pieceType = PieceType.QUEEN;
    }

    @Override
    public void getMovementPositions() {
        super.getMovementPositions();
        DiagonalMovement diagonal = new DiagonalMovement(controller, tile);
        attackingTiles.addAll(diagonal.possibleMovements(ChessGame.SIZE_GAMEBOARD - 1));

        HorVerMovement horVer = new HorVerMovement(controller, tile);
        attackingTiles.addAll(horVer.possibleMovements(ChessGame.SIZE_GAMEBOARD - 1));
    }
}
