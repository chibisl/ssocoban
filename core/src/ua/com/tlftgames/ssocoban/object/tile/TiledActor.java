package ua.com.tlftgames.ssocoban.object.tile;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

public class TiledActor extends Actor {
	private Array<Tile> tiles;	

	public TiledActor(Array<Tile> tiles) {
		this.tiles = tiles;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		if (tiles == null) {
			return;
		}
		for(Tile tile : this.tiles) {
			batch.draw(tile.getRegion(), this.getX() + tile.getRealX(), this.getY() + tile.getRealY());
		}
	}
}
