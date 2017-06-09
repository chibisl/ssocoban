package ua.com.tlftgames.ssocoban.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;

import ua.com.tlftgames.ssocoban.object.TiledActor;
import ua.com.tlftgames.utils.scenes.scene2d.ManagedStage;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.forever;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.rotateTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;

public class LevelStage extends ManagedStage {
	private String name;
	private String tmxPath;
	private TiledActor floor;
	private TiledActor walls;
	private TiledActor roof;
	private Image[][] objects;
	
	public LevelStage(String name) {
		this.name = name;
		StringBuilder builder = new StringBuilder("levels/");
		this.tmxPath = builder.append(this.name).append(".tmx").toString();
	}

	@Override
	public void addAssets() {
		this.loadAsset(this.tmxPath, TiledMap.class);
	}

	@Override
	public void show() {		
		TiledMap map = this.getAsset(this.tmxPath);
		this.clear();
		
		TiledMapTileLayer floorLayer = (TiledMapTileLayer)map.getLayers().get("floor");
		
		this.floor = new TiledActor(floorLayer);
		this.walls = new TiledActor((TiledMapTileLayer)map.getLayers().get("walls"));
		this.addBoxes((TiledMapTileLayer)map.getLayers().get("boxes"));
		this.roof = new TiledActor((TiledMapTileLayer)map.getLayers().get("roof"));
	}

	@Override
	public void showLoader() {
		Image loader = new Image(new Texture(Gdx.files.internal("graphics/loader.png")));
		loader.setPosition(this.getWidth() - loader.getWidth() - 30, 30);
        loader.setOrigin(Align.center);
        loader.addAction(forever(sequence(rotateTo(-360f, 1f), rotateTo(0f))));
        this.addActor(loader);
	}

	@Override
	public void uloadAssets() {
		this.unloadAsset(this.tmxPath);
	}
	
	private void addBoxes(TiledMapTileLayer layer) {
		float tileWidth = layer.getTileWidth();
		float tileHeight = layer.getTileHeight();
		int columnCount = layer.getWidth();
		int rowCount = layer.getHeight();
		
		objects = new Image[rowCount][columnCount];
		
		for(int y = rowCount - 1; y >= 0; y--) {
			for(int x = 0; x < columnCount; x++) {
				Cell cell = layer.getCell(x, y);
				if (cell != null) {
					final Image box = new Image(cell.getTile().getTextureRegion());
					box.setPosition(x * tileWidth, y * tileHeight);
					objects[y][x] = box;
					if (y == 0 && x == 8) {
						box.addAction(sequence(moveTo(box.getX(), box.getY() + 4 * tileHeight, 2), run(new Runnable() {

							@Override
							public void run() {
								objects[0][8] = null;
								objects[4][5] = box;
							}
							
						}), moveTo(box.getX() - 5 * tileWidth,  box.getY() + 4 * tileHeight, 2)));
					}
				}
			}
		}
	}
	
	public void act (float delta) {
		super.act(delta);
		if (this.objects != null) {
			for(int x = 0; x < this.objects.length; x++) {
				Image[] row = this.objects[x];
				for(int y = 0; y < row.length; y++) {
					if (row[y] != null) {
						row[y].act(delta);;
					}
				}
			} 		
		}
	}
	
	public void draw () {
		Camera camera = getViewport().getCamera();
		camera.update();

		if (!getRoot().isVisible()) return;

		Batch batch = this.getBatch();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		if (this.floor != null) {
			this.floor.draw(batch, 1);
			this.walls.draw(batch, 1);
			for(int y = this.objects.length -1; y >= 0; y--) {
				Image[] column = this.objects[y];
				for(int x = 0; x < column.length; x++) {
					if (column[x] != null) {
						column[x].draw(batch, 1);
					}
				}
			}			
			this.roof.draw(batch, 1);
		}
		getRoot().draw(batch, 1);
		batch.end();
	}

}
