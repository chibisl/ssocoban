package ua.com.tlftgames.ssocoban.object;

import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

import ua.com.tlftgames.ssocoban.object.tile.Tile;
import ua.com.tlftgames.ssocoban.object.tile.TileActor;

public abstract class MovingObject extends TileActor {

	public MovingObject(Tile tile) {
		super(tile);
	}

	public abstract SequenceAction moveTo(float x, float y);

}
