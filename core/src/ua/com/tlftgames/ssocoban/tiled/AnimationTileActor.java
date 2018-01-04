package ua.com.tlftgames.ssocoban.tiled;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;

public class AnimationTileActor extends TileActor {
	private Animation<TextureRegion> animation;
	private float stateTime = 0;

	public AnimationTileActor(Animation<TextureRegion> animation, float tileWidth, float tileHeight) {
		super(tileWidth, tileHeight);
		this.animation = animation;
	}
	
	private void updateStateTime(float delta) {
		this.stateTime += delta;
		if (this.stateTime > animation.getAnimationDuration() && animation.getPlayMode() != PlayMode.NORMAL) {
			this.stateTime -= animation.getAnimationDuration();
		}
	}

	@Override
	protected TextureRegion getRegion() {
		return animation.getKeyFrame(stateTime);
	}
	
	public void reset() {
		this.stateTime = 0;
	}
	
	public float getAnimationDuration() {
		return this.animation.getAnimationDuration();
	}
	
	public boolean isAnimationFinished() {
		return this.animation.isAnimationFinished(stateTime);
	}
	
	@Override
	public void act (float delta) {
		super.act(delta);
		this.updateStateTime(delta);
	}

}
