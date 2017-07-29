package ua.com.tlftgames.ssocoban.component;

import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

public abstract class MovementComponent extends Component {

    public abstract SequenceAction moveTo(float x, float y);

}
