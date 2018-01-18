package ua.com.tlftgames.ssocoban.tiled;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.tiles.AnimatedTiledMapTile;
import com.badlogic.gdx.utils.Array;

public class TileAnimationCreator {
    public static Animation<TextureRegion> createFromAnimatedTiledMapTile(AnimatedTiledMapTile tile) {
        int keysCount = tile.getFrameTiles().length;
        if (keysCount == 0) {
            return null;
        }
        Array<TextureRegion> keys = new Array<TextureRegion>();
        for (int i = 0; i < keysCount; i++) {
            keys.add(tile.getFrameTiles()[i].getTextureRegion());
        }
        float keyDuration = (float) tile.getAnimationIntervals()[0] / 1000;
        boolean loop = tile.getProperties().get("loop", true, Boolean.class);

        return new Animation<TextureRegion>(keyDuration, keys, loop ? PlayMode.LOOP : PlayMode.NORMAL);
    }
}
