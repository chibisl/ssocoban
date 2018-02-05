package ua.com.tlftgames.ssocoban.level;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.forever;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.rotateTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;

import ua.com.tlftgames.ssocoban.movement.direction.Direction;
import ua.com.tlftgames.ssocoban.script.ControllerScript;
import ua.com.tlftgames.utils.scenes.scene2d.ManagedStage;

public class LevelStage extends ManagedStage {
    private String tmxPath;
    private Level level;

    public LevelStage(String name) {
        StringBuilder builder = new StringBuilder("maps/");
        this.tmxPath = builder.append(name).append(".tmx").toString();
    }

    @Override
    public void addAssets() {
        this.loadAsset(this.tmxPath, TiledMap.class);
        this.loadAsset("maps/animations.tmx", TiledMap.class);
    }

    @Override
    public void show() {
        TiledMap map = this.getAsset(this.tmxPath);
        TiledMap animationMap = this.getAsset("maps/animations.tmx");
        TiledMapTileLayer animationLayer = (TiledMapTileLayer) animationMap.getLayers().get(0);
        level = LevelFactory.create(map.getLayers(), animationLayer);

        this.clear();
        this.addActor(new LevelGroup(level));
    }

    @Override
    public void showLoader() {
        Image loader = new Image(new Texture(Gdx.files.internal("graphics/loader.png")));
        loader.setPosition((this.getWidth() - loader.getWidth()) / 2, (this.getHeight() - loader.getHeight()) / 2);
        loader.setOrigin(Align.center);
        loader.addAction(forever(sequence(rotateTo(-360f, 1f), rotateTo(0f))));
        this.addActor(loader);
    }

    @Override
    public void uloadAssets() {
        this.unloadAsset(this.tmxPath);
        this.unloadAsset("maps/animations.tmx");
    }

    public boolean keyDown(int keycode) {
        int direction = Direction.getDirectionByKey(keycode);
        if (direction != Direction.NONE) {
            this.level.getRobot().getScript(ControllerScript.class).addMovement(direction);
            return true;
        }
        return false;
    }
}
