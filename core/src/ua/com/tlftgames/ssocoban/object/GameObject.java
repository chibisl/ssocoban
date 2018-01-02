package ua.com.tlftgames.ssocoban.object;

import com.badlogic.gdx.scenes.scene2d.Group;

import ua.com.tlftgames.ssocoban.object.tile.TileActor;
import ua.com.tlftgames.utils.tiled.CellTile;

public class GameObject extends TileActor {

    public GameObject(CellTile tile) {
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
