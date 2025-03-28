package engine.movements;

import engine.ChessGame;
import engine.tiles.Tile;
import java.util.ArrayList;

/**
 * Represents L shaped movements (knight movement)
 *
 * @author Graf Calvin
 * @author Sottile Alan
 * @author Hutzli Boris
 */
public class LMovement extends Movement{

    public LMovement(ChessGame controller, Tile tile) {
        super(controller, tile);
    }

    public ArrayList<Tile> possibleMovements() {
        ArrayList<Tile> possibleMovements = new ArrayList<>();
        final int x = tile.getPosition().getX();
        final int y = tile.getPosition().getY();

        possibleMovements.add(controller.getTile(x - 2, y + 1));
        possibleMovements.add(controller.getTile(x - 2, y - 1));
        possibleMovements.add(controller.getTile(x - 1, y + 2));
        possibleMovements.add(controller.getTile(x - 1, y - 2));
        possibleMovements.add(controller.getTile(x + 2, y + 1));
        possibleMovements.add(controller.getTile(x + 2, y - 1));
        possibleMovements.add(controller.getTile(x + 1, y + 2));
        possibleMovements.add(controller.getTile(x + 1, y - 2));

        return possibleMovements;
    }
}
