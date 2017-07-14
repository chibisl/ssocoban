package ua.com.tlftgames.ssocoban.object.tile;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

public class TiledActor extends Actor {
	private Array<Tile> tiles = new Array<Tile>();	

	public TiledActor(TiledMapTileLayer layer) {
		float tileWidth = layer.getTileWidth();
		float tileHeight = layer.getTileHeight();
		int columnCount = layer.getWidth();
		int rowCount = layer.getHeight();
		
		for(int x = 0; x < columnCount; x++) {
			for(int y = 0; y < rowCount; y++) {
				this.createTile(layer.getCell(x, y), x * tileWidth, y * tileHeight);
			}
		}
		
		this.setSize(tileWidth * columnCount, tileHeight * rowCount);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		for(Tile tile : this.tiles) {
			batch.draw(tile.getRegion(), this.getX() + tile.getX(), this.getY() + tile.getY());
		}
	}
	
	private void createTile(Cell cell, float x, float y) {
		if (cell != null) {
			this.tiles.add(new Tile(cell.getTile().getTextureRegion(), x, y));
		}
	}
}
