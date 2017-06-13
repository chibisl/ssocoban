package ua.com.tlftgames.ssocoban.object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class LevelMap extends Group {
    private Image[][] objects;

    public LevelMap(TiledMapTileLayer layer) {
        this.addBoxes(layer);
    }

    public void act(float delta) {
        super.act(delta);
        if (this.objects != null) {
            for (int y = 0; y < this.objects.length; y++) {
                Image[] column = this.objects[y];
                for (int x = 0; x < column.length; x++) {
                    if (column[x] != null) {
                        column[x].act(delta);
                    }
                }
            }
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        for (int y = this.objects.length - 1; y >= 0; y--) {
            Image[] column = this.objects[y];
            for (int x = 0; x < column.length; x++) {
                if (column[x] != null) {
                    column[x].draw(batch, 1);
                }
            }
        }
        super.draw(batch, parentAlpha);
    }

    private void addBoxes(TiledMapTileLayer layer) {
        float tileWidth = layer.getTileWidth();
        float tileHeight = layer.getTileHeight();
        int columnCount = layer.getWidth();
        int rowCount = layer.getHeight();

        objects = new Image[rowCount][columnCount];

        for (int y = rowCount - 1; y >= 0; y--) {
            for (int x = 0; x < columnCount; x++) {
                Cell cell = layer.getCell(x, y);
                if (cell != null) {
                    Gdx.app.log("props", cell.getTile().getProperties().get("type", String.class));
                    Image box = new Image(cell.getTile().getTextureRegion());
                    box.setPosition(x * tileWidth, y * tileHeight);
                    objects[y][x] = box;
                }
            }
        }
    }
}
