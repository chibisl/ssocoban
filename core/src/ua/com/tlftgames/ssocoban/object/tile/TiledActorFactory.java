package ua.com.tlftgames.ssocoban.object.tile;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.utils.Array;

import ua.com.tlftgames.utils.tiled.CellTile;
import ua.com.tlftgames.utils.tiled.TileCreator;

public class TiledActorFactory {

	public static TiledActor create(TiledMapTileLayer layer) {
		float tileWidth = layer.getTileWidth();
		float tileHeight = layer.getTileHeight();
		int columnCount = layer.getWidth();
		int rowCount = layer.getHeight();

		Array<CellTile> tiles = new Array<CellTile>();
		for (int x = 0; x < columnCount; x++) {
			for (int y = 0; y < rowCount; y++) {
				Cell cell = layer.getCell(x, y);
				if (cell != null) {
					CellTile tile = TileCreator.createFromTiledMapTile(cell.getTile());
					tile.setX(x);
					tile.setY(y);
					tile.setWidth(tileWidth);
					tile.setHeight(tileHeight);
					tiles.add(tile);
				}
			}
		}

		TiledActor actor = new TiledActor(tiles);

		actor.setSize(tileWidth * columnCount, tileHeight * rowCount);

		return actor;
	}
}
