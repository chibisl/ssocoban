package ua.com.tlftgames.ssocoban.object;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

import ua.com.tlftgames.ssocoban.object.tile.Tile;

public class Robot extends GameObject {

	public Robot(Tile tile) {
		super(tile);
	}

	@Override
	public SequenceAction moveTo(float x, float y) {
		SequenceAction action = new SequenceAction();
		action.addAction(Actions.moveTo(x, y, 0.1f));
		this.addAction(action);

		return action;
	}

}
