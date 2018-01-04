package ua.com.tlftgames.ssocoban.level;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;

import ua.com.tlftgames.ssocoban.tiled.TileActor;

public class LevelGroup extends Group {
    private Level level;

    public LevelGroup(Level level) {
    	this.level = level;
    	TileActor[][] objects = level.getObjectMap();
    	TileActor[][] walls = level.getWallMap();
    	TileActor[][] floor = level.getFloorMap();
    	TileActor[][] roof = level.getRoofMap();
    	for (int y = level.getHeight() - 1; y >= 0; y--) {
            for (int x = 0; x < level.getWidth(); x++) {
                if (objects[x][y] != null) {
                	this.addActor(objects[x][y]);
                }
                if (walls[x][y] != null) {
                	this.addActor(walls[x][y]);
                }
                if (floor[x][y] != null) {
                	this.addActor(floor[x][y]);
                }
                if (roof[x][y] != null) {
                	this.addActor(roof[x][y]);
                }
            }
        }
    	
    	this.setSize(level.getWidth() * level.getTileWidth(), level.getHeight() * level.getTileHeight());
    }

    public void act(float delta) {
        super.act(delta);
        if (this.level == null) {
        	return;
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
    	if (this.level == null) {
        	return;
        }
    	
    	this.drawLayer(level.getFloorMap(), batch, parentAlpha);
    	this.drawLayer(level.getWallMap(), batch, parentAlpha);
    	this.drawLayer(level.getObjectMap(), batch, parentAlpha);
    	this.drawLayer(level.getRoofMap(), batch, parentAlpha);
    }
    
    private void drawLayer(TileActor[][] layer, Batch batch, float parentAlpha) {
    	for (int y = level.getHeight() - 1; y >= 0; y--) {
            for (int x = 0; x < level.getWidth(); x++) {
                if (layer[x][y] != null) {
                	layer[x][y].draw(batch, parentAlpha);
                }
            }
        }
    }
    
    @Override
    public boolean removeActor (Actor actor, boolean unfocus) {
		if (actor instanceof TileActor) {
			level.removeObject((TileActor)actor);
		}
    	
		return super.removeActor(actor, unfocus);
	}
    
    @Override
    protected void setStage (Stage stage) {
		super.setStage(stage);
		if (stage != null) {
			this.setPosition((stage.getWidth() - this.getWidth()) / 2, (stage.getHeight() - this.getHeight()) / 2);
		}
	}
}
