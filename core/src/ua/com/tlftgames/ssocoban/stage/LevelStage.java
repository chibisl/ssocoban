package ua.com.tlftgames.ssocoban.stage;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.forever;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.rotateTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;

import ua.com.tlftgames.ssocoban.factory.TiledActorFactory;
import ua.com.tlftgames.ssocoban.object.LevelMap;
import ua.com.tlftgames.utils.scenes.scene2d.ManagedStage;

public class LevelStage extends ManagedStage {
    private String name;
    private String tmxPath;

    public LevelStage(String name) {
        this.name = name;
        StringBuilder builder = new StringBuilder("levels/");
        this.tmxPath = builder.append(this.name).append(".tmx").toString();
    }

    @Override
    public void addAssets() {
        this.loadAsset(this.tmxPath, TiledMap.class);
    }

    @Override
    public void show() {
        TiledMap map = this.getAsset(this.tmxPath);
        this.clear();
        TiledActorFactory factory = new TiledActorFactory();

        TiledMapTileLayer floorLayer = (TiledMapTileLayer) map.getLayers().get("floor");
        this.addActor(factory.createActor(floorLayer));
        
        this.addActor(factory.createActor((TiledMapTileLayer) map.getLayers().get("walls")));
        
        LevelMap level = new LevelMap((TiledMapTileLayer) map.getLayers().get("objects"));
        this.addActor(level);
        setKeyboardFocus(level);
        
        this.addActor(factory.createActor((TiledMapTileLayer) map.getLayers().get("roof")));
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
    }

}
