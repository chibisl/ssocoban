package ua.com.tlftgames.ssocoban.tiled;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureRegionTileActor extends TileActor {
	private TextureRegion region;

	public TextureRegionTileActor(TextureRegion region, float tileWidth, float tileHeight) {
		super(tileWidth, tileHeight);
		this.region = region;
	}

	@Override
	protected TextureRegion getRegion() {
		return region;
	}

}
