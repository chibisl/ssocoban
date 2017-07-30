package ua.com.tlftgames.ssocoban.component;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

public class MovementComponent extends Component {

    private float duration;

    public MovementComponent(float duration) {
        this.duration = duration;
    }

    public SequenceAction moveTo(float x, float y) {
        SequenceAction action = new SequenceAction();
        action.addAction(Actions.moveTo(x, y, duration));
        this.getObject().addAction(action);

        return action;
    }

}
