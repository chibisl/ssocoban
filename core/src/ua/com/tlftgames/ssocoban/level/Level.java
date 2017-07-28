package ua.com.tlftgames.ssocoban.level;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

import ua.com.tlftgames.ssocoban.movement.direction.Direction;
import ua.com.tlftgames.ssocoban.object.GameObject;

public class Level {
	private int width = 0;
	private int height = 0;
	private GameObject[][] objectMap;
	private GameObject robot;
	private int[][] floorMap;

	public Level(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public GameObject[][] getObjectMap() {
		return objectMap;
	}

	public void setObjectMap(GameObject[][] objectMap) {
		this.objectMap = objectMap;
	}

	public GameObject getRobot() {
		return this.robot;
	}

	public void setRobot(GameObject robot) {
		this.robot = robot;
	}

	public int[][] getFloorMap() {
		return this.floorMap;
	}

	public void setFloorMap(int[][] floorMap) {
		this.floorMap = floorMap;
	}

	public SequenceAction moveObject(GameObject object, int direction) {
		Vector2 newPosition = this.getNewPosition(object, direction);
		return this.moveObject(object, (int) newPosition.x, (int) newPosition.y);
	}

	public SequenceAction moveObject(GameObject object, int x, int y) {
		if (!hasFloor(x, y) || !isCellFree(x, y)) {
			return null;
		}
		int objectX = (int) object.getX();
		int objectY = (int) object.getY();
		if (objectX >= 0 && objectY >= 0 && objectX < width && objectY < height) {
			objectMap[(int) object.getX()][(int) object.getY()] = null;
		}
		objectMap[x][y] = object;

		return object.moveTo(x, y);
	}

	public GameObject getNeighbour(GameObject object, int direction) {
		Vector2 neighbourPosition = this.getNewPosition(object, direction);
		return this.getObject((int) neighbourPosition.x, (int) neighbourPosition.y);
	}

	public GameObject getObject(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) {
			return null;
		}

		return objectMap[x][y];
	}

	public boolean hasFloor(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) {
			return false;
		}
		return floorMap[x][y] > 0;
	}

	public boolean isCellFree(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) {
			return false;
		}

		return this.hasFloor(x, y) && this.getObject(x, y) == null;
	}

	private Vector2 getNewPosition(GameObject object, int direction) {
		Vector2 directionVector = Direction.getVector2ByDirection(direction);
		return object.getPosition().add(directionVector);
	}
}
