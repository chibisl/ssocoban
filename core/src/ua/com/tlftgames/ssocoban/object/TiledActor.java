package ua.com.tlftgames.ssocoban.object;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class TiledActor extends Actor {
	private TiledMapTileLayer layer;
	private float tileWidth;
	private float tileHeight;
	private int columnCount;
	private int rowCount;

	public TiledActor(TiledMapTileLayer layer) {
		this.layer = layer;
		tileWidth = layer.getTileWidth();
		tileHeight = layer.getTileHeight();
		columnCount = layer.getWidth();
		rowCount = layer.getHeight();
		this.setSize(tileWidth * columnCount, tileHeight * rowCount);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		for(int x = 0; x < columnCount; x++) {
			for(int y = 0; y < rowCount; y++) {
				this.drawCell(batch, layer.getCell(x, y), x * tileWidth, y * tileHeight);
			}
		}
	}
	
	private void drawCell(Batch batch, Cell cell, float x, float y) {
		if (cell != null) {
			batch.draw(cell.getTile().getTextureRegion(), this.getX() + x, this.getY() + y);
		}
	}
}
