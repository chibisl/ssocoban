package ua.com.tlftgames.ssocoban.object.tile;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

import ua.com.tlftgames.utils.scenes.scene2d.ComponentActor;

public class TileActor extends ComponentActor {
	private Tile tile;

	public TileActor(Tile tile) {
		this.tile = tile;
		this.setPosition(tile.getX(), tile.getY());
	}

	@Override
	public void setPosition(float x, float y, int alignment) {
		super.setPosition(x, y, alignment);
		tile.setX(x);
		tile.setY(y);
	}

	@Override
	public void setPosition(float x, float y) {
		super.setPosition(x, y);
		tile.setX(x);
		tile.setY(y);
	}

	public Vector2 getPosition() {
		return new Vector2(this.getX(), this.getY());
	}

	@Override
	public void setX(float x) {
		super.setX(x);
		tile.setX(x);
	}

	@Override
	public void setY(float y) {
		super.setY(y);
		tile.setY(y);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		if (tile == null) {
			return;
		}
		batch.draw(tile.getRegion(), this.tile.getRealX(), this.tile.getRealY());
	}
}
