package ua.com.tlftgames.ssocoban.controller;

import java.util.LinkedList;

import com.badlogic.gdx.math.Vector2;

import ua.com.tlftgames.ssocoban.Direction;
import ua.com.tlftgames.ssocoban.level.Level;
import ua.com.tlftgames.ssocoban.tile.TileActor;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class MovementController {
	private Level level;
	private LinkedList<Integer> directionPool;
	
	public MovementController(Level level) {
		this.level = level;
		directionPool = new LinkedList<Integer>();
	}
	
	public void addMovement(int direction) {
		this.directionPool.add(direction);
		if (this.directionPool.size() == 1) {
			this.startMoving(this.directionPool.getFirst());
		}
	}
	
	private void nextMove() {
		if (this.directionPool.isEmpty()) {
			return;
		}
		
		this.directionPool.removeFirst();
		if (!this.directionPool.isEmpty()) {		
			this.startMoving(this.directionPool.getFirst());
		}
	}
	
	private void startMoving(int direction) {
		TileActor robot = level.getRobot();
		Vector2 directionVector = Direction.getVector2ByDirection(direction);
		Vector2 newPosition = robot.getPosition().add(directionVector);
		int newX = (int)newPosition.x;
		int newY = (int)newPosition.y;
		if (!level.hasFloor(newX, newY)) {
			this.nextMove();
			return;
		}
		
		if (level.isCellOpen(newX, newY)) {
			level.moveObject(robot, newX, newY);
			robot.addAction(sequence(moveTo(newX, newY, 0.1f), run(new Runnable() {
				@Override
				public void run() {
					nextMove();
				}
			})));
			return;
		}
		
		TileActor object = level.getObject(newX, newY);
		Vector2 newObjectPosition = object.getPosition().add(directionVector);
		int newObjectX = (int)newObjectPosition.x;
		int newObjectY = (int)newObjectPosition.y;
		if (level.isCellOpen(newObjectX, newObjectY)) {
			level.moveObject(object, newObjectX, newObjectY);
			level.moveObject(robot, newX, newY);
			object.addAction(moveTo(newObjectX, newObjectY, 0.07f));
			robot.addAction(sequence(moveTo(newX, newY, 0.1f), run(new Runnable() {
				@Override
				public void run() {
					nextMove();
				}
			})));
			return;
		}
		
		nextMove();
	}
}
