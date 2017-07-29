package ua.com.tlftgames.ssocoban.object;

import ua.com.tlftgames.ssocoban.component.BoxMovementComponent;
import ua.com.tlftgames.ssocoban.component.MovementComponent;
import ua.com.tlftgames.ssocoban.component.RobotMovementComponent;
import ua.com.tlftgames.ssocoban.object.tile.Tile;

public class GameObjectFactory {

    public static GameObject create(String type, Tile tile) {
        GameObject object = new GameObject(tile);
        if (type != null && type.contentEquals("robot")) {
            object.addComponent(MovementComponent.class, new RobotMovementComponent());
        } else {
            object.addComponent(MovementComponent.class, new BoxMovementComponent());
        }

        return object;
    }

}
