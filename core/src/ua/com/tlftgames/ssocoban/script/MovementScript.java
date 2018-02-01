package ua.com.tlftgames.ssocoban.script;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

import ua.com.tlftgames.ssocoban.Event;
import ua.com.tlftgames.utils.scenes.scene2d.script.Script;
import ua.com.tlftgames.utils.scenes.scene2d.script.ScriptEvent;

public class MovementScript extends Script {
    private float duration;

    public MovementScript(float duration) {
        this.duration = duration;
    }

    @Override
    public void handle(ScriptEvent event) {
        if (event.getId() != Event.MOVE) {
            return;
        }

        Vector2 newPosition = (Vector2) event.getData();

        SequenceAction action = new SequenceAction();
        action.addAction(Actions.moveTo(newPosition.x, newPosition.y, duration));
        action.addAction(Actions.run(new Runnable() {
            @Override
            public void run() {
                actor.dispatch(Event.STOP);
            }
        }));
        this.actor.addAction(action);
    }

}
