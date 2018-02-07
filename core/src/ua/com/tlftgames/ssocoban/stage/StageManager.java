package ua.com.tlftgames.ssocoban.stage;

import com.badlogic.gdx.Gdx;

import ua.com.tlftgames.utils.StageScreen;

public class StageManager {
	private StageScreen screen;
	
	public StageManager(StageScreen screen) {
		this.screen = screen;
	}
	
	public void startLevel(int level) {
		this.screen.setStage(new LevelStage(this, "level" + level));
	}
	
	public void nextLevel() {
		Gdx.app.exit();
	}
	
	
}
