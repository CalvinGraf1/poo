package engine.movements;

import engine.ChessGame;
import engine.tiles.Tile;
import java.util.ArrayList;

/**
 * Represents diagonal movements
 *
 * @author Graf Calvin
 * @author Sottile Alan
 * @author Hutzli Boris
 */
public class DiagonalMovement extends StraightLineMovement {

    public DiagonalMovement(ChessGame controller, Tile tile) {
        super(controller, tile);
    }

    public ArrayList<Tile> possibleMovements(int distance) {
        ArrayList<Tile> possibleMovements = new ArrayList<>();
        possibleMovements.addAll(checkIfObstacle(1, 1, distance));
        possibleMovements.addAll(checkIfObstacle(-1, 1, distance));
        possibleMovements.addAll(checkIfObstacle(1, -1, distance));
        possibleMovements.addAll(checkIfObstacle(-1, -1, distance));
        return possibleMovements;
    }
}
