package ua.com.tlftgames.ssocoban.movement.direction;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;

public final class Direction {
	public final static int NONE = 0;
	public final static int UP = 1;
	public final static int DOWN = 2;
	public final static int LEFT = 3;
	public final static int RIGHT = 4;

	public static int getDirectionByKey(int keycode) {
		switch (keycode) {
		case Keys.LEFT:
			return LEFT;
		case Keys.RIGHT:
			return RIGHT;
		case Keys.UP:
			return UP;
		case Keys.DOWN:
			return DOWN;
		default:
			return NONE;
		}
	}
	
	public static Vector2 getVector2ByDirection(int direction) {
		Vector2 vector = new Vector2(0, 0);
		vector.x = direction == Direction.RIGHT ? 1 : (direction == Direction.LEFT ? -1 : 0);
		vector.y = direction == Direction.UP ? 1 : (direction == Direction.DOWN ? -1 : 0);
		
		return vector;
	}
}
