package ua.com.tlftgames.ssocoban.object;

import ua.com.tlftgames.ssocoban.component.MovementComponent;
import ua.com.tlftgames.ssocoban.object.tile.Tile;

public class GameObjectFactory {

    public static GameObject create(String type, Tile tile) {
        GameObject object = new GameObject(tile);
        boolean isRobot = (type != null && type.contentEquals("robot"));
        object.addComponent(MovementComponent.class, new MovementComponent(isRobot ? 0.1f : 0.07f));
 
        return object;
    }

}
