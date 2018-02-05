package ua.com.tlftgames.ssocoban.script;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.tiles.AnimatedTiledMapTile;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.utils.Array;

import ua.com.tlftgames.ssocoban.Event;
import ua.com.tlftgames.ssocoban.tiled.AnimationTileActor;
import ua.com.tlftgames.ssocoban.tiled.TileAnimationCreator;
import ua.com.tlftgames.utils.scenes.scene2d.script.Script;
import ua.com.tlftgames.utils.scenes.scene2d.script.ScriptEvent;

public class AnimationScript extends Script {
    private Array<Animation<TextureRegion>> animations;
    private float playTime = 0;

    public AnimationScript(TiledMapTileLayer layer) {
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

    @Override
    public void act(float delta) {
        this.playTime += delta;
        if (this.playTime >= 30) {
            ((AnimationTileActor) actor).setAnimation(animations.get(5 + (int) Math.round(Math.random())));
            this.playTime = 0;
        }
    }

    public void handle(ScriptEvent event) {
        SequenceAction action;
        switch (event.getId()) {
        case Event.MOVE:
            ((AnimationTileActor) actor).setAnimation(animations.get(0));
            this.playTime = 0;
            break;
        case Event.PULL:
            final int direction = (Integer) event.getData();
            action = new SequenceAction();
            action.addAction(run(new Runnable() {
                @Override
                public void run() {
                    ((AnimationTileActor) actor).setAnimation(animations.get(direction));
                }
            }));
            action.addAction(delay(0.2f));
            action.addAction(run(new Runnable() {
                @Override
                public void run() {
                    ((AnimationTileActor) actor).setAnimation(animations.get(0));
                }
            }));
            actor.addAction(action);
            break;
        case Event.EXIT:
            action = new SequenceAction();
            action.addAction(run(new Runnable() {
                @Override
                public void run() {
                    ((AnimationTileActor) actor).setAnimation(animations.get(7));
                }
            }));
            action.addAction(delay(1f));
            action.addAction(run(new Runnable() {
                @Override
                public void run() {
                    Gdx.app.exit();
                }
            }));
            actor.addAction(action);
            break;
        }
    }

}
