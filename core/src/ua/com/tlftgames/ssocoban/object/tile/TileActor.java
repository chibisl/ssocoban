package ua.com.tlftgames.ssocoban.object.tile;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class TileActor extends Actor {
	private Tile tile;
	
	public TileActor(Tile tile) {
		this.tile = tile;
		this.setPosition(tile.getRealX(), tile.getRealY());
	}
	
	public void setTilePosition(float x, float y) {
		this.setTileX(x);
		this.setTileY(y);
	}
	
	public Vector2 getTilePosition() {
		return new Vector2(this.tile.getX(), this.tile.getY());
	}
	
	public void setTileX(float x) {
		tile.setX(x);
		this.setX(tile.getRealX());
	}
	
	public float getTileX() {
		return tile.getX();
	}
	
	public void setTileY(float y) {
		tile.setY(y);
		this.setY(tile.getRealY());
	}
	
	public float getTileY() {
		return tile.getY();
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		if (tile == null) {
			return;
		}
		batch.draw(tile.getRegion(), this.getX(), this.getY());
	}
}
