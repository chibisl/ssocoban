package ua.com.tlftgames.ssocoban.tile;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTile;

public class Tile {
    private TiledMapTile tiledMapTile;
    private float x;
    private float y;
    private float width;
    private float height;

    public Tile(TiledMapTile tiledMapTile, float x, float y, float width, float height) {
    	this.tiledMapTile = tiledMapTile;
    	this.x = x;
    	this.y = y;
    	this.width = width;
    	this.height = height;
    }
    
    public void setX(float x) {
    	this.x = x;
    }

    public float getX() {
        return x;
    }
    
    public void setY(float y) {
    	this.y = y;
    }

    public float getY() {
        return y;
    }

    public TextureRegion getRegion() {
        return tiledMapTile.getTextureRegion();
    }

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}
	
	public float getRealX() {
		return x * width;
	}
	
	public float getRealY() {
		return y * height;
	}
}
