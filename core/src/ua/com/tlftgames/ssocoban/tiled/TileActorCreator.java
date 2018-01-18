package ua.com.tlftgames.ssocoban.tiled;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.tiles.AnimatedTiledMapTile;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;

public class TileActorCreator {
    public static TileActor createFromTiledMapTile(TiledMapTile tile, float tileWidth, float tileHeight) {
        if (tile instanceof AnimatedTiledMapTile) {
            return createFromAnimatedTiledMapTile((AnimatedTiledMapTile) tile, tileWidth, tileHeight);
        } else {
            return createFromStaticTiledMapTile((StaticTiledMapTile) tile, tileWidth, tileHeight);
        }
    }

    private static TextureRegionTileActor createFromStaticTiledMapTile(StaticTiledMapTile tile, float tileWidth,
            float tileHeight) {
        return new TextureRegionTileActor(tile.getTextureRegion(), tileWidth, tileHeight);
    }

    private static AnimationTileActor createFromAnimatedTiledMapTile(AnimatedTiledMapTile tile, float tileWidth,
            float tileHeight) {
        Animation<TextureRegion> animation = TileAnimationCreator.createFromAnimatedTiledMapTile(tile);
        if (animation == null) {
            return null;
        }
        return new AnimationTileActor(animation, tileWidth, tileHeight);
    }
}
