package ua.com.tlftgames.ssocoban.level;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;

import ua.com.tlftgames.ssocoban.object.GameObject;
import ua.com.tlftgames.ssocoban.object.GameObjectFactory;
import ua.com.tlftgames.utils.tiled.CellTile;
import ua.com.tlftgames.utils.tiled.TileCreator;

public class LevelFactory {

    public static Level create(TiledMapTileLayer floorLayer, TiledMapTileLayer objectLayer) {
        Level level = new Level(floorLayer.getWidth(), floorLayer.getHeight());
        level.setFloorMap(createFloorMap(floorLayer, level.getWidth(), level.getHeight()));

        int columnCount = Math.min(objectLayer.getWidth(), level.getWidth());
        int rowCount = Math.min(objectLayer.getHeight(), level.getHeight());
        float tileWidth = objectLayer.getTileWidth();
        float tileHeight = objectLayer.getTileHeight();

        GameObject[][] actors = new GameObject[level.getWidth()][level.getHeight()];

        for (int y = rowCount - 1; y >= 0; y--) {
            for (int x = 0; x < columnCount; x++) {
                Cell cell = objectLayer.getCell(x, y);
                if (cell == null) {
                    continue;
                }
                CellTile tile = TileCreator.createFromTiledMapTile(cell.getTile());
				tile.setX(x);
				tile.setY(y);
				tile.setWidth(tileWidth);
				tile.setHeight(tileHeight);
				String type = cell.getTile().getProperties().get("type", String.class);
                GameObject object = GameObjectFactory.create(type, tile);
                if (type != null && type.contentEquals("robot")) {
                    level.setRobot(object);
                }
                
                actors[x][y] = object;
            }
        }
        level.setObjectMap(actors);

        return level;
    }

    private static int[][] createFloorMap(TiledMapTileLayer floorLayer, int levelColumnCount, int levelRowCount) {
        int columnCount = Math.min(floorLayer.getWidth(), levelColumnCount);
        int rowCount = Math.min(floorLayer.getHeight(), levelRowCount);

        int[][] floorMap = new int[levelColumnCount][levelRowCount];

        for (int y = rowCount - 1; y >= 0; y--) {
            for (int x = 0; x < columnCount; x++) {
                Cell cell = floorLayer.getCell(x, y);
                if (cell == null) {
                    floorMap[x][y] = 0;
                    continue;
                }

                String type = cell.getTile().getProperties().get("type", String.class);
                floorMap[x][y] = (type != null && type.contentEquals("exit")) ? 2 : 1;
            }
        }

        return floorMap;
    }
}
