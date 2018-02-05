package ua.com.tlftgames.ssocoban.level;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;

import ua.com.tlftgames.ssocoban.script.AnimationScript;
import ua.com.tlftgames.ssocoban.script.ControllerScript;
import ua.com.tlftgames.ssocoban.script.MovementScript;
import ua.com.tlftgames.ssocoban.tiled.TileActor;
import ua.com.tlftgames.ssocoban.tiled.TileActorCreator;

public class LevelFactory {

    public static Level create(MapLayers layers, final MapLayer animations) {

        TiledMapTileLayer floorLayer = (TiledMapTileLayer) layers.get("floor");
        TiledMapTileLayer objectLayer = (TiledMapTileLayer) layers.get("objects");
        TiledMapTileLayer wallLayer = (TiledMapTileLayer) layers.get("wall");
        TiledMapTileLayer rooflLayer = (TiledMapTileLayer) layers.get("roof");

        final Level level = new Level(floorLayer.getWidth(), floorLayer.getHeight(), floorLayer.getTileWidth(),
                floorLayer.getTileHeight());
        level.setRoofMap(createMap(rooflLayer, level.getWidth(), level.getHeight()));
        level.setWallMap(createMap(wallLayer, level.getWidth(), level.getHeight()));
        level.setFloorMap(createMap(floorLayer, level.getWidth(), level.getHeight(), new TileUpdater() {
            @Override
            public void update(Cell cell, TileActor actor) {
                String type = cell.getTile().getProperties().get("type", String.class);
                if (type != null && type.contentEquals("exit")) {
                    level.setExitPosition(actor.getPosition());
                }
            }
        }));
        level.setObjectMap(createMap(objectLayer, level.getWidth(), level.getHeight(), new TileUpdater() {
            @Override
            public void update(Cell cell, TileActor actor) {
                actor.addScript(new MovementScript(0.2f));
                String type = cell.getTile().getProperties().get("type", String.class);
                if (type != null && type.contentEquals("robot")) {
                    actor.addScript(new AnimationScript((TiledMapTileLayer) animations));
                    actor.addScript(new ControllerScript(level));
                    level.setRobot(actor);
                }
            }
        }));

        return level;
    }

    private static TileActor[][] createMap(TiledMapTileLayer layer, int levelColumnCount, int levelRowCount) {
        return createMap(layer, levelColumnCount, levelRowCount, null);
    }

    private static TileActor[][] createMap(TiledMapTileLayer layer, int levelColumnCount, int levelRowCount,
            TileUpdater tileUpdater) {

        int columnCount = Math.min(layer.getWidth(), levelColumnCount);
        int rowCount = Math.min(layer.getHeight(), levelRowCount);

        float tileWidth = layer.getTileWidth();
        float tileHeight = layer.getTileHeight();

        TileActor[][] actors = new TileActor[levelColumnCount][levelRowCount];
        for (int y = rowCount - 1; y >= 0; y--) {
            for (int x = 0; x < columnCount; x++) {
                Cell cell = layer.getCell(x, y);
                if (cell == null) {
                    continue;
                }
                TileActor object = TileActorCreator.createFromTiledMapTile(cell.getTile(), tileWidth, tileHeight);
                object.setX(x);
                object.setY(y);
                if (tileUpdater != null) {
                    tileUpdater.update(cell, object);
                }
                actors[x][y] = object;
            }
        }

        return actors;
    }

    private interface TileUpdater {
        public void update(Cell cell, TileActor actor);
    }
}
