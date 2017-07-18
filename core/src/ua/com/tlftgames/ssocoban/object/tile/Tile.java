package ua.com.tlftgames.ssocoban.object.tile;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Tile {
    private TextureRegion region;
    private float x;
    private float y;
    private float width;
    private float height;

    public Tile(TextureRegion region, float x, float y, float width, float height) {
    	this.region = region;
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
        return region;
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
