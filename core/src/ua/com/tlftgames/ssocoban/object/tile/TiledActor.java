package ua.com.tlftgames.ssocoban.object.tile;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

import ua.com.tlftgames.utils.tiled.CellTile;

public class TiledActor extends Actor {
	private Array<CellTile> tiles;

	public TiledActor(Array<CellTile> tiles) {
		this.tiles = tiles;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		if (tiles == null) {
			return;
		}
		for (CellTile tile : this.tiles) {
			batch.draw(tile.getRegion(), this.getX() + tile.getRealX(), this.getY() + tile.getRealY());
		}
	}
}
