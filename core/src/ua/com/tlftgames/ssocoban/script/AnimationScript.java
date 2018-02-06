package ua.com.tlftgames.ssocoban.script;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.utils.Array;

import ua.com.tlftgames.ssocoban.tiled.AnimationTileActor;
import ua.com.tlftgames.utils.scenes.scene2d.script.Script;

public class AnimationScript extends Script {
    private Array<Animation<TextureRegion>> animations;
    private float playTime = 0;

    public AnimationScript(Array<Animation<TextureRegion>> animations) {
        this.animations = animations;
    }
    
    public void reset() {
    	((AnimationTileActor) actor).setAnimation(animations.get(0));
        this.playTime = 0;
    }
    
    public void pull(int direction) {
    	((AnimationTileActor) actor).setAnimation(animations.get(direction));
    	float duration = actor.getScript(MovementScript.class).getDuration();
    	SequenceAction action = new SequenceAction();
        action.addAction(delay(duration));
        action.addAction(run(new Runnable() {
            @Override
            public void run() {
                reset();
            }
        }));
        actor.addAction(action);
    }
    
    public void exit() {
    	((AnimationTileActor) actor).setAnimation(animations.get(7));
    }

    @Override
    public void act(float delta) {
        this.playTime += delta;
        if (this.playTime >= 30) {
            ((AnimationTileActor) actor).setAnimation(animations.get(5 + (int) Math.round(Math.random())));
            this.playTime = 0;
        }
    }

}
