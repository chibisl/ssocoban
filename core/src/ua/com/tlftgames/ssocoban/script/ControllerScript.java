package ua.com.tlftgames.ssocoban.script;

import ua.com.tlftgames.ssocoban.Event;
import ua.com.tlftgames.ssocoban.level.Level;
import ua.com.tlftgames.ssocoban.movement.direction.Direction;
import ua.com.tlftgames.ssocoban.movement.direction.DirectionQueue;
import ua.com.tlftgames.ssocoban.tiled.TileActor;
import ua.com.tlftgames.utils.scenes.scene2d.script.Script;
import ua.com.tlftgames.utils.scenes.scene2d.script.ScriptEvent;

public class ControllerScript extends Script {
    private Level level;
    private DirectionQueue directionQueue;

    public ControllerScript(Level level) {
        this.level = level;
        directionQueue = new DirectionQueue();
    }

    public void addMovement(int direction) {
        this.directionQueue.add(direction);
        if (this.directionQueue.getCount() == 1) {
            this.move(this.directionQueue.getCurrent());
        }
    }

    private void move(final int direction) {
        final TileActor robot = level.getRobot();
        if (level.isExit(robot.getPosition().x, robot.getPosition().y)) {
            robot.dispatch(Event.EXIT);
        }

        if (direction == Direction.NONE) {
            return;
        }

        TileActor box;
        if ((box = level.getNeighbour(robot, direction)) != null) {
            robot.dispatch(new ScriptEvent(Event.PULL, new Integer(direction)));
            level.moveObject(box, direction);
        }

        if (!level.moveObject(robot, direction)) {
            this.move(this.directionQueue.getNext());
        }
    }

    @Override
    public void handle(ScriptEvent event) {
        if (event.getId() == Event.STOP) {
            this.move(this.directionQueue.getNext());
        }
    }

}
