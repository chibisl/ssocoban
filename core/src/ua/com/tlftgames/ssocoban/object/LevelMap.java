package ua.com.tlftgames.ssocoban.object;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import ua.com.tlftgames.ssocoban.Direction;
import ua.com.tlftgames.ssocoban.object.tile.Tile;
import ua.com.tlftgames.ssocoban.object.tile.TileActor;

public class LevelMap extends Group {
    private TileActor[][] objects;
    private int columnCount;
    private int rowCount;
    private TileActor robot;

    public LevelMap(TiledMapTileLayer layer) {
        this.addObjects(layer);
        this.addListener(new InputListener() {			
			public boolean keyDown(InputEvent event, int keycode) {
				switch(keycode) {
				case Keys.LEFT:
					LevelMap.this.moveObject(robot, Direction.left);
					break;
				case Keys.RIGHT:
					LevelMap.this.moveObject(robot, Direction.right);
					break;
				case Keys.UP:
					LevelMap.this.moveObject(robot, Direction.up);
					break;
				case Keys.DOWN:
					LevelMap.this.moveObject(robot, Direction.down);
					break;
				default:
					return false;
				}
				return true;
			}
		});
    }

    public void act(float delta) {
        super.act(delta);
        if (this.objects != null) {
        	for (int y = rowCount - 1; y >= 0; y--) {
                for (int x = 0; x < columnCount; x++) {
                    if (objects[x][y] != null) {
                    	objects[x][y].act(delta);
                    }
                }
            }
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
    	for (int y = rowCount - 1; y >= 0; y--) {
            for (int x = 0; x < columnCount; x++) {
                if (objects[x][y] != null) {
                	objects[x][y].draw(batch, 1);
                }
            }
        }
        super.draw(batch, parentAlpha);
    }

    private void addObjects(TiledMapTileLayer layer) {
        float tileWidth = layer.getTileWidth();
        float tileHeight = layer.getTileHeight();
        this.columnCount = layer.getWidth();
        this.rowCount = layer.getHeight();

        objects = new TileActor[columnCount][rowCount];

        for (int y = rowCount - 1; y >= 0; y--) {
            for (int x = 0; x < columnCount; x++) {
                Cell cell = layer.getCell(x, y);
                if (cell != null) {
                    String type = cell.getTile().getProperties().get("type", String.class);
                    TileActor object = new TileActor(new Tile(cell.getTile().getTextureRegion(), x, y, tileWidth, tileHeight));
                    if (type.contentEquals("robot")) {
                    	this.robot = object;
                    }
                    objects[x][y] = object;
                }
            }
        }
    }
    
    protected void moveObject(TileActor object, int direction) {
    	Vector2 newPosition = this.getObjectNewPosition(object, direction);
    	if (newPosition == null) {
    		return;
    	}
    	objects[(int)object.getTileX()][(int)object.getTileY()] = null;
    	object.setTilePosition(newPosition.x, newPosition.y);
		objects[(int)object.getTileX()][(int)object.getTileY()] = object;
	}
    
    protected Vector2 getObjectNewPosition(TileActor object, int direction) {
    	Vector2 newPosition = object.getTilePosition();
    	switch(direction) {
		case Direction.up:
			newPosition.y = object.getTileY() + 1;
			break;
		case Direction.down:
			newPosition.y = object.getTileY() - 1;
			break;
		case Direction.left:
			newPosition.x = object.getTileX() - 1;
			break;
		case Direction.right:
			newPosition.x = object.getTileX() + 1;
			break;
		}
    	
    	if ((newPosition.x >= 0) && (newPosition.y >= 0) && (newPosition.x < columnCount) && (newPosition.y < rowCount) && (objects[(int)newPosition.x][(int)newPosition.y] == null)) {
    		return newPosition;
    	}
    	return null;    	
    }
}
