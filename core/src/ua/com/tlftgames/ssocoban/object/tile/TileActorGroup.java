package ua.com.tlftgames.ssocoban.object.tile;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;

public class TileActorGroup extends Group {
    private TileActor[][] actors;
    private int columnCount = 0;
    private int rowCount = 0;

    public TileActorGroup(TileActor[][] actors) {
    	this.actors = actors;
    	if (actors != null) {
	    	columnCount = actors.length;
	    	rowCount = actors[0].length; 
    	}
    }

    public void act(float delta) {
        super.act(delta);
        if (this.actors == null) {
        	return;
        }
    	for (int y = rowCount - 1; y >= 0; y--) {
            for (int x = 0; x < columnCount; x++) {
                if (actors[x][y] != null) {
                	actors[x][y].act(delta);
                }
            }
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
    	if (this.actors == null) {
        	return;
        }
    	for (int y = rowCount - 1; y >= 0; y--) {
            for (int x = 0; x < columnCount; x++) {
                if (actors[x][y] != null) {
                	actors[x][y].draw(batch, 1);
                }
            }
        }
        super.draw(batch, parentAlpha);
    }
}
