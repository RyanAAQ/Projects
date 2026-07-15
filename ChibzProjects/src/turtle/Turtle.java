package turtle;

import static turtle.Directions.*;

public class Turtle {
    private boolean penIsUp = true;
    private TurtlePosition position;
    private Directions currentDirection;

    public Turtle(){
        position = new TurtlePosition();
        currentDirection = EAST;

    }

    public TurtlePosition getPosition() {
        return position;
    }

    public Directions getCurrentDirection() {
        return currentDirection;
    }

    public boolean penIsUp(){
        return penIsUp;
    }

    public void movePenDown(){
        penIsUp = false;
    }

    public void movePenUp(){
        penIsUp  = true;
    }

    public void turnRight(){
        currentDirection = currentDirection.getRight();
    }

    public void turnLeft(){
        currentDirection = currentDirection.getLeft();
    }

    public void moveForward(int steps) {
        switch (currentDirection) {
            case EAST  -> position.increaseColumnBy(steps);
            case WEST  -> position.increaseColumnBy(steps);
            case SOUTH -> position.increaseRowBy(steps);
            case NORTH -> position.increaseRowBy(steps);
        }
    }
}
