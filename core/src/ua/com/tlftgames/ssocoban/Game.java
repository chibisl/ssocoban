package ua.com.tlftgames.ssocoban;

import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

import ua.com.tlftgames.ssocoban.stage.LevelStage;
import ua.com.tlftgames.utils.StageGame;
import ua.com.tlftgames.utils.core.GameCore;

public class Game extends StageGame {

	public Game() {
		super(1280, 720);
	}
	
	public void create() {
		super.create();
		GameCore.getAssetManager().setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
		this.getScreen().setStage(new LevelStage());
	}

}
