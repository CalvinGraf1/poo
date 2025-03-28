package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.ChessGame;
import engine.movements.HorVerMovement;

/**
 * Represents the part corresponding to the rook
 *
 * @author Graf Calvin
 * @author Sottile Alan
 * @author Hutzli Boris
 */
public class Rook extends Piece {

    public Rook(ChessGame controller, PlayerColor color) {
        super(controller, color);
        pieceType = PieceType.ROOK;
    }

    @Override
    public void getMovementPositions() {
        super.getMovementPositions();
        HorVerMovement horVer = new HorVerMovement(controller, tile);
        attackingTiles.addAll(horVer.possibleMovements(ChessGame.SIZE_GAMEBOARD - 1));
    }
}