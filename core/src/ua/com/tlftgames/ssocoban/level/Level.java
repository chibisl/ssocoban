package ua.com.tlftgames.ssocoban.level;

import com.badlogic.gdx.math.Vector2;

import ua.com.tlftgames.ssocoban.movement.direction.Direction;
import ua.com.tlftgames.ssocoban.script.MovementScript;
import ua.com.tlftgames.ssocoban.tiled.TileActor;

public class Level {
    private int width = 0;
    private int height = 0;
    private float tileWidth = 0;
    private float tileHeight = 0;
    private TileActor[][] objectMap;
    private TileActor robot;
    private TileActor[][] roofMap;
    private TileActor[][] wallMap;
    private TileActor[][] floorMap;
    private Vector2 exitPosition;

    public Level(int width, int height, float tileWidth, float tileHeight) {
        this.width = width;
        this.height = height;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
    }

    private Vector2 getNewPosition(TileActor object, int direction) {
        Vector2 directionVector = Direction.getVector2ByDirection(direction);
        return object.getPosition().add(directionVector);
    }

    private boolean isValidCoordinates(int x, int y) {
        return (x >= 0 && y >= 0 && x < width && y < height);
    }

    public float getTileWidth() {
        return tileWidth;
    }

    public float getTileHeight() {
        return tileHeight;
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

    public TileActor[][] getRoofMap() {
        return roofMap;
    }

    public void setRoofMap(TileActor[][] roofMap) {
        this.roofMap = roofMap;
    }

    public TileActor[][] getWallMap() {
        return wallMap;
    }

    public void setWallMap(TileActor[][] wallMap) {
        this.wallMap = wallMap;
    }

    public TileActor[][] getFloorMap() {
        return this.floorMap;
    }

    public void setFloorMap(TileActor[][] floorMap) {
        this.floorMap = floorMap;
    }

    public Vector2 getExitPosition() {
        return this.exitPosition;
    }

    public void setExitPosition(Vector2 position) {
        this.exitPosition = position;
    }

    public boolean isExitReached() {
        return this.exitPosition.x == robot.getX() && this.exitPosition.y == robot.getY();
    }

    public boolean moveObject(TileActor object, int direction) {
        Vector2 newPosition = this.getNewPosition(object, direction);
        return this.moveObject(object, newPosition);
    }

    public boolean moveObject(TileActor object, Vector2 newPosition) {
        if (!hasFloor((int) newPosition.x, (int) newPosition.y)
                || !isCellFree((int) newPosition.x, (int) newPosition.y)) {
            return false;
        }

        this.removeObject(object);
        objectMap[(int) newPosition.x][(int) newPosition.y] = object;

        object.getScript(MovementScript.class).move(newPosition);
        return true;
    }

    public TileActor getNeighbour(TileActor object, int direction) {
        Vector2 neighbourPosition = this.getNewPosition(object, direction);
        return this.getObject((int) neighbourPosition.x, (int) neighbourPosition.y);
    }

    public TileActor getObject(int x, int y) {
        if (!isValidCoordinates(x, y)) {
            return null;
        }

        return objectMap[x][y];
    }

    public boolean hasFloor(int x, int y) {
        if (!isValidCoordinates(x, y)) {
            return false;
        }
        return floorMap[x][y] != null;
    }

    public boolean isCellFree(int x, int y) {
        if (!isValidCoordinates(x, y)) {
            return false;
        }

        return this.hasFloor(x, y) && this.getObject(x, y) == null;
    }

    public void removeObject(TileActor object) {
        this.removeObject((int) object.getX(), (int) object.getY());
    }

    public void removeObject(int x, int y) {
        if (!isValidCoordinates(x, y)) {
            return;
        }

        objectMap[x][y] = null;
    }
}
