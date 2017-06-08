package ua.com.tlftgames.ssocoban.object;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Tile {
	private TextureRegion region;
	private Vector2 position;
	private Vector2 absolutePosition;
	private float offset;
	
	public Tile(TextureRegion region, float x, float y, float offset) {
		this.region = region;
		this.offset = offset;
		this.position = new Vector2(x, y);
		this.absolutePosition = new Vector2(offset * x, offset * y);
	}
	
	public TextureRegion getRegion() {
		return this.region;
	}
	
	public void setPosition(Vector2 position) {
		this.position = position;
		this.position = new Vector2(offset * position.x, offset * position.y);
	}
	
	public Vector2 getPosition() {
		return this.position;
	}
	
	public void setX(float x) {
		this.position.x = x;
		this.absolutePosition.x = x * offset;
	}
	
	public void setY(float y) {
		this.position.y = y;
		this.absolutePosition.y = y * offset;
	}
	
	public float getX() {
		return this.position.x;
	}
	
	public float getY() {
		return this.position.y;
	}
	
	public Vector2 getAbsolutePosition() {
		return this.absolutePosition;
	}
	
	public float getAbsoluteX() {
		return this.absolutePosition.x;
	}
	
	public float getAbsoluteY() {
		return this.absolutePosition.y;
	}
}
