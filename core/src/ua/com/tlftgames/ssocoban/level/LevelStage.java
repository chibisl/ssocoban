package ua.com.tlftgames.ssocoban.level;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.forever;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.rotateTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.AnimatedTiledMapTile;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;

import ua.com.tlftgames.ssocoban.movement.MovementController;
import ua.com.tlftgames.ssocoban.movement.direction.Direction;
import ua.com.tlftgames.ssocoban.object.tile.TileActorGroup;
import ua.com.tlftgames.ssocoban.object.tile.TiledActorFactory;
import ua.com.tlftgames.utils.scenes.scene2d.ManagedStage;

public class LevelStage extends ManagedStage {
    private String tmxPath;
    private MovementController movementController;

    public LevelStage(String name) {
        StringBuilder builder = new StringBuilder("levels/");
        this.tmxPath = builder.append(name).append(".tmx").toString();
    }

    @Override
    public void addAssets() {
        this.loadAsset(this.tmxPath, TiledMap.class);
    }

    @Override
    public void show() {
        TiledMap map = this.getAsset(this.tmxPath);

        TiledMapTileLayer floorLayer = (TiledMapTileLayer) map.getLayers().get("floor");
        Level level = LevelFactory.create(floorLayer, (TiledMapTileLayer) map.getLayers().get("objects"));
        
        this.clear();
        this.addActor(TiledActorFactory.create(floorLayer));
        this.addActor(TiledActorFactory.create((TiledMapTileLayer) map.getLayers().get("walls")));
        this.addActor(new TileActorGroup(level));
        this.addActor(TiledActorFactory.create((TiledMapTileLayer) map.getLayers().get("roof")));
        
        movementController = new MovementController(level);
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
    
    public boolean keyDown (int keycode) {
    	int direction = Direction.getDirectionByKey(keycode);
    	if (direction != Direction.NONE) {
    		movementController.addMovement(direction);
    		return true;
    	}
    	return false;
	}
    
    @Override
    public void draw() {
    	AnimatedTiledMapTile.updateAnimationBaseTime();
    	super.draw();
    }
}
