package ua.com.tlftgames.ssocoban.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;

import ua.com.tlftgames.ssocoban.object.TiledActor;
import ua.com.tlftgames.utils.scenes.scene2d.ManagedStage;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.forever;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.rotateTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

public class LevelStage extends ManagedStage {

	@Override
	public void addAssets() {
		this.loadAsset("levels/level1.tmx", TiledMap.class);
	}

	@Override
	public void show() {		
		TiledMap map = this.getAsset("levels/level1.tmx");
		this.clear();
		
		TiledActor actor = new TiledActor((TiledMapTileLayer)map.getLayers().get("floor"));
		this.addActor(actor);
		this.addActor(new TiledActor((TiledMapTileLayer)map.getLayers().get("roof")));
	}

	@Override
	public void showLoader() {
		Image loader = new Image(new Texture(Gdx.files.internal("graphics/loader.png")));
		loader.setPosition(this.getWidth() - loader.getWidth() - 30, 30);
        loader.setOrigin(Align.center);
        loader.addAction(forever(sequence(rotateTo(-360f, 1f), rotateTo(0f))));
        this.addActor(loader);
	}

	@Override
	public void uloadAssets() {
		this.unloadAsset("levels/level1.tmx");
	}

}
