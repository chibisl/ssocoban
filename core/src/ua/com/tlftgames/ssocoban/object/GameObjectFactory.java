package ua.com.tlftgames.ssocoban.object;

import ua.com.tlftgames.ssocoban.component.MovementComponent;
import ua.com.tlftgames.ssocoban.object.tile.Tile;

public class GameObjectFactory {

    public static GameObject create(String type, Tile tile) {
        GameObject object = new GameObject(tile);
        object.addComponent(new MovementComponent(0.1f));
 
        return object;
    }

}
