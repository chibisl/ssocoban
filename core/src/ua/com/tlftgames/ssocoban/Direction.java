package ua.com.tlftgames.ssocoban;

import com.badlogic.gdx.Input.Keys;

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
}
