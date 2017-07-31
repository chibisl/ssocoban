package ua.com.tlftgames.ssocoban.component;

import ua.com.tlftgames.ssocoban.level.Level;
import ua.com.tlftgames.utils.component.Component;

public abstract class InteractionComponent extends Component {
	private Level level;
	
	public InteractionComponent(Level level) {
		this.level = level;
	}
	
	public Level getLevel() {
		return level;
	}

	public abstract void interact();
}
