package ua.com.tlftgames.ssocoban.component;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.tiles.AnimatedTiledMapTile;
import com.badlogic.gdx.utils.Array;

import ua.com.tlftgames.ssocoban.tiled.AnimationTileActor;
import ua.com.tlftgames.ssocoban.tiled.TileAnimationCreator;
import ua.com.tlftgames.utils.component.Component;

public class AnimationComponent extends Component {
    private Array<Animation<TextureRegion>> animations;

    public AnimationComponent(TiledMapTileLayer layer) {
        int columnCount = layer.getWidth();
        int rowCount = layer.getHeight();

        this.animations = new Array<Animation<TextureRegion>>(columnCount * rowCount);
        for (int y = rowCount - 1; y >= 0; y--) {
            for (int x = 0; x < columnCount; x++) {
                Cell cell = layer.getCell(x, y);
                this.animations.add(
                        TileAnimationCreator.createFromAnimatedTiledMapTile((AnimatedTiledMapTile) cell.getTile()));
            }
        }
    }

    public void changeAnimation(int index) {
        ((AnimationTileActor) this.getObject()).setAnimation(animations.get(index));
    }

}
