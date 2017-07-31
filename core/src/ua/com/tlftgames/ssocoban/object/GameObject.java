package ua.com.tlftgames.ssocoban.object;

import com.badlogic.gdx.scenes.scene2d.Group;

import ua.com.tlftgames.ssocoban.object.tile.Tile;
import ua.com.tlftgames.ssocoban.object.tile.TileActor;

public class GameObject extends TileActor {

    public GameObject(Tile tile) {
        super(tile);
    }

    public void destroy() {
        this.remove();
    }
    
    @Override
    public void setParent(Group group) {
    	super.setParent(group);
    }

}
