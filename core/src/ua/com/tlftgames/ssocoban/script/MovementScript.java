package ua.com.tlftgames.ssocoban.script;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import ua.com.tlftgames.utils.scenes.scene2d.script.Script;

public class MovementScript extends Script {
    private float duration;

    public MovementScript(float duration) {
        this.duration = duration;
    }
    
    public float getDuration() {
    	return this.duration;
    }
    
    public void move(Vector2 newPosition) {
        this.actor.addAction(Actions.moveTo(newPosition.x, newPosition.y, duration));
    }

}
