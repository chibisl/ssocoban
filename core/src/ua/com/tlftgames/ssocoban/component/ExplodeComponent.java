package ua.com.tlftgames.ssocoban.component;

import ua.com.tlftgames.ssocoban.level.Level;
import ua.com.tlftgames.ssocoban.movement.direction.Direction;
import ua.com.tlftgames.ssocoban.object.GameObject;

public class ExplodeComponent extends InteractionComponent {
	
	private int[] directions = {Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT};

	public ExplodeComponent(Level level) {
		super(level);
	}

	@Override
	public void interact() {
		for (int direction : directions) {
			GameObject object = getLevel().getNeighbour((GameObject)this.getObject(), direction);
			if (object != null && object.getComponent(InteractionComponent.class) instanceof ExplodeComponent) {
				object.destroy();
				((GameObject)this.getObject()).destroy();
				break;
			}
		}
	}

}
