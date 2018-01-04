package ua.com.tlftgames.ssocoban.tiled;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ua.com.tlftgames.utils.scenes.scene2d.ComponentActor;

public abstract class TileActor extends ComponentActor {
	private float tileWidth;
	private float tileHeight;

	public TileActor(float tileWidth, float tileHeight) {
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
	}

	public Vector2 getPosition() {
		return new Vector2(this.getX(), this.getY());
	}
	
	protected abstract TextureRegion getRegion();
	
	protected float getRealX() {
		return this.getX() * tileWidth + ((this.getParent() != null) ? this.getParent().getX() : 0);
	}
	
	protected float getRealY() {
		return this.getY() * tileHeight + ((this.getParent() != null) ? this.getParent().getY() : 0);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);

		batch.draw(this.getRegion(), this.getRealX(), this.getRealY());
	}
}
