package ua.com.tlftgames.ssocoban.component;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

public class BoxMovementComponent extends MovementComponent {

    @Override
    public SequenceAction moveTo(float x, float y) {
        SequenceAction action = new SequenceAction();
        action.addAction(Actions.moveTo(x, y, 0.07f));
        this.getObject().addAction(action);

        return action;
    }

}
