package ua.com.tlftgames.ssocoban.level;

import java.util.LinkedList;

import ua.com.tlftgames.ssocoban.Direction;
import ua.com.tlftgames.ssocoban.tile.TileActor;

public class Level {
	private LinkedList<Integer> directionPool;
	private int width = 0;
	private int height = 0;
	private TileActor[][] objectMap;
	private TileActor robot;
	private int[][] floorMap;
	
	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		directionPool = new LinkedList<Integer>();
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public TileActor[][] getObjectMap() {
		return objectMap;
	}

	public void setObjectMap(TileActor[][] objectMap) {
		this.objectMap = objectMap;
	}

	public void setRobot(TileActor robot) {
		this.robot = robot;
	}

	public void setFloorMap(int[][] floorMap) {
		this.floorMap = floorMap;
	}

	public void moveRobot(int direction) {
		this.directionPool.add(direction);
	}
	
	private void moveObject(TileActor object, int direction) {
		
		int newX = (int)object.getX() + (direction == Direction.RIGHT ? 1 : (direction == Direction.LEFT ? -1 : 0));
		int newY = (int)object.getY() + (direction == Direction.UP ? 1 : (direction == Direction.DOWN ? -1 : 0));
		
		if (!canMove(newX, newY)) {
			return;
		}
		
		objectMap[(int)object.getX()][(int)object.getY()] = null;
		object.setPosition(newX, newY);
		objectMap[newX][newY] = object;
	}
	
	private boolean canMove(int x, int y) {
		return (x >= 0) && (y >= 0) && (x < width) && (y < height) && (floorMap[x][y] == 1);
	}
	
	private boolean canMoveObject(TileActor object, int direction) {
		int newX = (int)object.getX() + (direction == Direction.RIGHT ? 1 : (direction == Direction.LEFT ? -1 : 0));
		int newY = (int)object.getY() + (direction == Direction.UP ? 1 : (direction == Direction.DOWN ? -1 : 0));
		
		return canMove(newX, newY) && this.getNeighbour(object, direction) == null;
	}
	
	private TileActor getNeighbour(TileActor object, int direction) {
		int newX = (int)object.getX() + (direction == Direction.RIGHT ? 1 : (direction == Direction.LEFT ? -1 : 0));
		int newY = (int)object.getY() + (direction == Direction.UP ? 1 : (direction == Direction.DOWN ? -1 : 0));
		
		return this.objectMap[newX][newY];
	}
	
	public void update() {
		if (directionPool.size() > 0) {
			int direction = directionPool.removeFirst();
			TileActor neighbour = getNeighbour(robot, direction);
			if (neighbour != null) {
				if (canMoveObject(neighbour, direction)) {
					this.moveObject(neighbour, direction);
				} else {
					return;
				}
			}
			this.moveObject(this.robot, direction);
		}
	}
}
