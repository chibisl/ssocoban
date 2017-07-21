package ua.com.tlftgames.ssocoban.level;

import ua.com.tlftgames.ssocoban.tile.TileActor;

public class Level {
	private int width = 0;
	private int height = 0;
	private TileActor[][] objectMap;
	private TileActor robot;
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

	public TileActor[][] getObjectMap() {
		return objectMap;
	}

	public void setObjectMap(TileActor[][] objectMap) {
		this.objectMap = objectMap;
	}
	
	public TileActor getRobot() {
		return this.robot;
	}

	public void setRobot(TileActor robot) {
		this.robot = robot;
	}
	
	public int[][] getFloorMap() {
		return this.floorMap;
	}

	public void setFloorMap(int[][] floorMap) {
		this.floorMap = floorMap;
	}
	
	public boolean moveObject(TileActor object, int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) {
			return false;
		}
		int objectX = (int)object.getX();
		int objectY = (int)object.getY();
		if (objectX >= 0 && objectY >= 0 && objectX < width && objectY < height) {
			objectMap[(int)object.getX()][(int)object.getY()] = null;
		}
		objectMap[x][y] = object;
		
		return true;
	}
	
	public TileActor getObject(int x, int y) {
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
	
	public boolean isCellOpen(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) {
			return false;
		}
		
		return this.hasFloor(x, y) && this.getObject(x, y) == null;
	}
}
