package ua.com.tlftgames.ssocoban.utils;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.utils.Array;

import ua.com.tlftgames.ssocoban.tile.Tile;
import ua.com.tlftgames.ssocoban.tile.TiledActor;

public class TiledActorFactory {

	public static TiledActor create(TiledMapTileLayer layer) {
		float tileWidth = layer.getTileWidth();
		float tileHeight = layer.getTileHeight();
		int columnCount = layer.getWidth();
		int rowCount = layer.getHeight();
		
		Array<Tile> tiles = new Array<Tile>();
		
		for(int x = 0; x < columnCount; x++) {
			for(int y = 0; y < rowCount; y++) {
				Cell cell = layer.getCell(x, y);
				if (cell != null) {
					tiles.add(new Tile(cell.getTile().getTextureRegion(), x, y, tileWidth, tileHeight));
				}
			}
		}
		
		TiledActor actor = new TiledActor(tiles);
		
		actor.setSize(tileWidth * columnCount, tileHeight * rowCount);
		
		return actor;
	}
}
