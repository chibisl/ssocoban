package ua.com.tlftgames.ssocoban.level;

import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

import ua.com.tlftgames.ssocoban.object.MovingObject;

public class Level {
	private int width = 0;
	private int height = 0;
	private MovingObject[][] objectMap;
	private MovingObject robot;
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

	public MovingObject[][] getObjectMap() {
		return objectMap;
	}

	public void setObjectMap(MovingObject[][] objectMap) {
		this.objectMap = objectMap;
	}

	public MovingObject getRobot() {
		return this.robot;
	}

	public void setRobot(MovingObject robot) {
		this.robot = robot;
	}

	public int[][] getFloorMap() {
		return this.floorMap;
	}

	public void setFloorMap(int[][] floorMap) {
		this.floorMap = floorMap;
	}

	public SequenceAction moveObject(MovingObject object, int x, int y) {
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

	public MovingObject getObject(int x, int y) {
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
}
