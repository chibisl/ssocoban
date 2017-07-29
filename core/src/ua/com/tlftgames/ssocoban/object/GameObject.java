package ua.com.tlftgames.ssocoban.object;

import java.util.HashMap;

import ua.com.tlftgames.ssocoban.component.Component;
import ua.com.tlftgames.ssocoban.object.tile.Tile;
import ua.com.tlftgames.ssocoban.object.tile.TileActor;

public class GameObject extends TileActor {

    private HashMap<Class<? extends Component>, Object> components = new HashMap<Class<? extends Component>, Object>();

    public GameObject(Tile tile) {
        super(tile);
    }

    public void destroy() {
        this.remove();
    }

    public <T extends Component> T getComponent(Class<T> type) {
        return type.cast(components.get(type));
    }

    public <T extends Component> void addComponent(Class<T> type, T component) {
        component.setObject(this);
        components.put(type, component);
    }

    public <T extends Component> void removeComponent(Class<T> type) {
        components.remove(type);
    }

}
