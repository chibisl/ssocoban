package ua.com.tlftgames.ssocoban.script;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.Gdx;

import ua.com.tlftgames.ssocoban.level.Level;
import ua.com.tlftgames.ssocoban.movement.direction.Direction;
import ua.com.tlftgames.ssocoban.movement.direction.DirectionQueue;
import ua.com.tlftgames.ssocoban.tiled.TileActor;
import ua.com.tlftgames.utils.scenes.scene2d.script.Script;

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
    	TileActor robot = level.getRobot();
    	if (level.isExit(robot.getPosition().x, robot.getPosition().y)) {
        	this.leaveLevel();
            return;
        }

        if (direction == Direction.NONE) {
            return;
        }
        
        robot.getScript(AnimationScript.class).pull(direction);

        TileActor box;
        if ((box = level.getNeighbour(robot, direction)) != null) {
            level.moveObject(box, direction);
        }

        level.moveObject(robot, direction);
        
        float moveDuration = robot.getScript(MovementScript.class).getDuration();
        robot.addAction(sequence(delay(moveDuration), run(new Runnable() {
        	@Override
            public void run() {
        		move(directionQueue.getNext());
            }
        })));
    }
    
    private void leaveLevel() {
    	TileActor robot = level.getRobot();
    	robot.getScript(AnimationScript.class).exit();
        robot.addAction(sequence(delay(1f), run(new Runnable() {
        	@Override
            public void run() {
                Gdx.app.exit();
            }
        })));
    }

}
