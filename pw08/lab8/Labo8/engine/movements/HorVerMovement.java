package engine.movements;

import engine.ChessGame;
import engine.tiles.Tile;
import java.util.ArrayList;

/**
 * Represents horizontal and vertical movements
 *
 * @author Graf Calvin
 * @author Sottile Alan
 * @author Hutzli Boris
 */
public class HorVerMovement extends StraightLineMovement {

    public HorVerMovement(ChessGame controller, Tile tile) {
        super(controller, tile);
    }

    public ArrayList<Tile> possibleMovements(int distance) {
        ArrayList<Tile> possibleMovements = new ArrayList<>();
        possibleMovements.addAll(checkIfObstacle(1, 0, distance));
        possibleMovements.addAll(checkIfObstacle(0, 1, distance));
        possibleMovements.addAll(checkIfObstacle(0, -1, distance));
        possibleMovements.addAll(checkIfObstacle(-1, 0, distance));
        return possibleMovements;
    }
}
