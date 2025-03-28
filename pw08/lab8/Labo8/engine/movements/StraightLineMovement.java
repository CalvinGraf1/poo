package engine.movements;

import engine.ChessGame;
import engine.tiles.Tile;
import java.util.ArrayList;

/**
 * Represents line movements
 *
 * @author Graf Calvin
 * @author Sottile Alan
 * @author Hutzli Boris
 */
public abstract class StraightLineMovement extends Movement {

    public StraightLineMovement(ChessGame controller, Tile tile) {
        super(controller, tile);
    }

    /**
     * Calculates all the tiles where it is possible to move
     *
     * @param distance Distance to check
     * @return List containing all the possible tiles to move in each direction
     */
    abstract ArrayList<Tile> possibleMovements(int distance);

    /**
     * Check if the tiles are accessible or if there is an obstacle
     *
     * @param xDirection The direction of the X axis
     * @param yDirection The direction of the Y axis
     * @param distance Distance to check
     * @return List containing all the possible tiles to move in one direction
     */
    public ArrayList<Tile> checkIfObstacle(int xDirection, int yDirection, int distance) {
        ArrayList<Tile> possibleTiles = new ArrayList<>();
        for (int i = 1; i < distance + 1; ++i) {
            int newX = tile.getPosition().getX() + i * xDirection;
            int newY = tile.getPosition().getY() + i * yDirection;
            Tile newTile = controller.getTile(newX, newY);

            if (newTile != null) {
                possibleTiles.add(newTile);
                if (newTile.getPiece() != null) break;
            }
        }
        return possibleTiles;
    }
}
