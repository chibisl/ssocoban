package ua.com.tlftgames.ssocoban.movement;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

import ua.com.tlftgames.ssocoban.level.Level;
import ua.com.tlftgames.ssocoban.movement.direction.Direction;
import ua.com.tlftgames.ssocoban.movement.direction.DirectionQueue;
import ua.com.tlftgames.ssocoban.object.MovingObject;

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
		
		MovingObject robot = level.getRobot();
		
		Vector2 directionVector = Direction.getVector2ByDirection(direction);
		Vector2 newPosition = robot.getPosition().add(directionVector);
		final int newX = (int) newPosition.x;
		final int newY = (int) newPosition.y;
		
		MovingObject box;
		Vector2 newBoxPosition = newPosition.add(directionVector);
		if ((box = level.getObject(newX, newY)) != null && level.moveObject(box, (int)newBoxPosition.x, (int)newBoxPosition.y) == null) {
			move(directionQueue.getNext());
			return;
		}
		
		SequenceAction action = level.moveObject(robot, newX, newY);
		if (action == null) {
			move(directionQueue.getNext());
			return;
		}		
		action.addAction(run(new Runnable() {
			@Override
			public void run() {
				if (level.getFloorMap()[newX][newY] == 2) {
					Gdx.app.exit();
				}
				move(directionQueue.getNext());
			}
		}));			
	}

}
