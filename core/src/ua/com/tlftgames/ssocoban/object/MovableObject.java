package ua.com.tlftgames.ssocoban.object;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class MovableObject extends Image {
	private int mapX = 0;
	private int mapY = 0;
	private float tileWidth = 0;
	private float tileHeigth = 0;
	
	public MovableObject(TextureRegion textureRegion, float tileWidth, float tileHeight) {
		super(textureRegion);
		this.tileWidth = tileWidth;
		this.tileHeigth = tileHeight;
	}
	
	public void setMapPosition(int mapX, int mapY) {
		this.mapX = mapX;
		this.mapY = mapY;
		this.setPosition(mapX * tileWidth, mapY * tileHeigth);
	}
	
	public Vector2 getMapPosition() {
		return new Vector2(this.mapX, this.mapY);
	}

	public int getMapX() {
		return mapX;
	}

	public void setMapX(int mapX) {
		this.mapX = mapX;
		this.setX(mapX * tileWidth);
	}

	public int getMapY() {
		return mapY;
	}

	public void setMapY(int mapY) {
		this.mapY = mapY;
		this.setY(mapY * tileHeigth);
	}
}
