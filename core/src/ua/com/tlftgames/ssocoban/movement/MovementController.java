package ua.com.tlftgames.ssocoban.movement;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

import ua.com.tlftgames.ssocoban.level.Level;
import ua.com.tlftgames.ssocoban.movement.direction.Direction;
import ua.com.tlftgames.ssocoban.movement.direction.DirectionQueue;
import ua.com.tlftgames.ssocoban.object.GameObject;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class MovementController {
	private Level level;
	private DirectionQueue directionQueue;

	public MovementController(Level level) {
		this.level = level;
		directionQueue = new DirectionQueue();
	}

	public void addMovement(int direction) {
		this.directionQueue.add(direction);
		if (this.directionQueue.getCount() == 1) {
			this.move(this.directionQueue.getCurrent());
		}
	}

	private void move(int direction) {
		if (direction == Direction.NONE) {
			return;
		}

		final GameObject robot = level.getRobot();

		GameObject box;
		if ((box = level.getNeighbour(robot, direction)) != null && level.moveObject(box, direction) == null) {
			move(directionQueue.getNext());
			return;
		}

		SequenceAction action = level.moveObject(robot, direction);
		if (action == null) {
			move(directionQueue.getNext());
			return;
		}
		action.addAction(run(new Runnable() {
			@Override
			public void run() {
				if (level.getFloorMap()[(int) robot.getX()][(int) robot.getY()] == 2) {
					Gdx.app.exit();
				}
				move(directionQueue.getNext());
			}
		}));
	}

}
