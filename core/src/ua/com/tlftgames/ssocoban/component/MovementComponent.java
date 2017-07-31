package ua.com.tlftgames.ssocoban.component;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

import ua.com.tlftgames.utils.component.Component;

public class MovementComponent extends Component {

    private float duration;

    public MovementComponent(float duration) {
        this.duration = duration;
    }

    public SequenceAction moveTo(final float x, final float y) {
        SequenceAction action = new SequenceAction();
        action.addAction(Actions.moveTo(x, y, duration));
        this.getObject().addAction(action);
        if (this.getObject().hasComponent(InteractionComponent.class)) {
        	action.addAction(run(new Runnable() {
				@Override
				public void run() {
					getObject().getComponent(InteractionComponent.class).interact();
				}
        	}));
        }

        return action;
    }

}
