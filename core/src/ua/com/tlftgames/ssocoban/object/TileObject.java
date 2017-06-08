package ua.com.tlftgames.ssocoban.object;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class TileObject extends Actor {
	private Tile[][] tileMap;

	public TileObject(Tile[][] tileMap) {
		this.tileMap = tileMap;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		for (int i = 0; i < tileMap.length; i++) {
			for (int j = 0; j < tileMap[i].length; j++) {
				if (tileMap[i][j] != null) {
					batch.draw(tileMap[i][j].getRegion(), this.getX() + tileMap[i][j].getAbsoluteX(),
							this.getY() + tileMap[i][j].getAbsoluteY());
				}
			}
		}
	}
}
