package ua.com.tlftgames.ssocoban.object.tile;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Tile {
    private TextureRegion region;
    private float x;
    private float y;

    public Tile(TextureRegion region, float x, float y) {
        this.setRegion(region);
        this.setX(x);
        this.setY(y);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public TextureRegion getRegion() {
        return region;
    }

    public void setRegion(TextureRegion region) {
        this.region = region;
    }
}
