package ua.com.tlftgames.ssocoban.component;

import ua.com.tlftgames.ssocoban.level.Level;
import ua.com.tlftgames.ssocoban.movement.direction.Direction;
import ua.com.tlftgames.ssocoban.tiled.TileActor;

public class ExplodeComponent extends InteractionComponent {
	
	private int[] directions = {Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT};

	public ExplodeComponent(Level level) {
		super(level);
	}

	@Override
	public void interact() {
		for (int direction : directions) {
			TileActor object = getLevel().getNeighbour((TileActor)this.getObject(), direction);
			if (object != null && object.getComponent(InteractionComponent.class) instanceof ExplodeComponent) {
				object.remove();
				((TileActor)this.getObject()).remove();
				break;
			}
		}
	}

}
