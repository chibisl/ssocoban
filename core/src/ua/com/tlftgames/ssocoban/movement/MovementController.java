package ua.com.tlftgames.ssocoban.movement;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

import ua.com.tlftgames.ssocoban.component.AnimationComponent;
import ua.com.tlftgames.ssocoban.level.Level;
import ua.com.tlftgames.ssocoban.movement.direction.Direction;
import ua.com.tlftgames.ssocoban.movement.direction.DirectionQueue;
import ua.com.tlftgames.ssocoban.tiled.TileActor;

public class MovementController {
    private Level level;
    private DirectionQueue directionQueue;

    public MovementController(Level level) {
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
        if (direction == Direction.NONE) {
            return;
        }

        final TileActor robot = level.getRobot();

        TileActor box;
        if ((box = level.getNeighbour(robot, direction)) != null) {
            robot.addAction(sequence(run(new Runnable() {
                @Override
                public void run() {
                    robot.getComponent(AnimationComponent.class).changeAnimation(direction);
                }
            }), delay(0.2f), run(new Runnable() {
                @Override
                public void run() {
                    robot.getComponent(AnimationComponent.class).changeAnimation(0);
                }
            })));
            if (level.moveObject(box, direction) == null) {
                move(directionQueue.getNext());
                return;
            }
        }

        final SequenceAction action = level.moveObject(robot, direction);
        if (action == null) {
            move(directionQueue.getNext());
            return;
        }
        action.addAction(run(new Runnable() {
            @Override
            public void run() {
                if (level.isExit(robot.getX(), robot.getY())) {
                    robot.getComponent(AnimationComponent.class).changeAnimation(7);
                    action.addAction(delay(1.5f));
                    action.addAction(com.badlogic.gdx.scenes.scene2d.actions.Actions.run(new Runnable() {
                        @Override
                        public void run() {
                            Gdx.app.exit();
                        }
                    }));
                }
                move(directionQueue.getNext());
            }
        }));
    }

}
