package ua.com.tlftgames.ssocoban.tiled;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.tiles.AnimatedTiledMapTile;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.utils.Array;

public class TileActorCreator {
	public static TileActor createFromTiledMapTile(TiledMapTile tile, float tileWidth, float tileHeight) {
		if (tile instanceof AnimatedTiledMapTile) {
			return createFromAnimatedTiledMapTile((AnimatedTiledMapTile)tile, tileWidth, tileHeight);
		} else {
			return createFromStaticTiledMapTile((StaticTiledMapTile)tile, tileWidth, tileHeight);
		}
	}

	private static TextureRegionTileActor createFromStaticTiledMapTile(StaticTiledMapTile tile, float tileWidth, float tileHeight) {
		return new TextureRegionTileActor(tile.getTextureRegion(), tileWidth, tileHeight);
	}

	private static AnimationTileActor createFromAnimatedTiledMapTile(AnimatedTiledMapTile tile, float tileWidth, float tileHeight) {
		int keysCount = tile.getFrameTiles().length; 
		if (keysCount == 0) {
			return null;
		}
		Array<TextureRegion> keys = new Array<TextureRegion>();
		for (int i = 0; i < keysCount; i++) {
			keys.add(tile.getFrameTiles()[i].getTextureRegion());
		}
		float keyDuration = (float)tile.getAnimationIntervals()[0] / 1000;
		boolean loop = tile.getProperties().get("loop", true, Boolean.class);
		
		Animation<TextureRegion> animation = new Animation<TextureRegion>(keyDuration, keys, loop ? PlayMode.LOOP : PlayMode.NORMAL);
		return new AnimationTileActor(animation, tileWidth, tileHeight);
	}
}
