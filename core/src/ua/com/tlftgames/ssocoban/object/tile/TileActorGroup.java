package ua.com.tlftgames.ssocoban.object.tile;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

import ua.com.tlftgames.ssocoban.level.Level;
import ua.com.tlftgames.ssocoban.object.GameObject;

public class TileActorGroup extends Group {
    private Level level;

    public TileActorGroup(Level level) {
    	this.level = level;
    	GameObject[][] objects = level.getObjectMap();
    	for (int y = level.getHeight() - 1; y >= 0; y--) {
            for (int x = 0; x < level.getWidth(); x++) {
                if (objects[x][y] != null) {
                	objects[x][y].setParent(this);
                }
            }
        }
    }

    public void act(float delta) {
        super.act(delta);
        if (this.level == null) {
        	return;
        }
        GameObject[][] objects = level.getObjectMap();
    	for (int y = level.getHeight() - 1; y >= 0; y--) {
            for (int x = 0; x < level.getWidth(); x++) {
                if (objects[x][y] != null) {
                	objects[x][y].act(delta);
                }
            }
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
    	if (this.level == null) {
        	return;
        }
    	
    	GameObject[][] objects = level.getObjectMap();
    	for (int y = level.getHeight() - 1; y >= 0; y--) {
            for (int x = 0; x < level.getWidth(); x++) {
                if (objects[x][y] != null) {
                	objects[x][y].draw(batch, 1);
                }
            }
        }
        super.draw(batch, parentAlpha);
    }
    
    @Override
    public boolean removeActor (Actor actor, boolean unfocus) {
		if (actor instanceof GameObject) {
			level.removeObject((GameObject)actor);
		}
    	
		return super.removeActor(actor, unfocus);
	}
}
