package turtle;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static turtle.Directions.*;

public class TurtleTest {
    Turtle turtle;

    @BeforeEach
    void setup() {
        turtle = new Turtle();
    }

    @Test
    void turtleIsAtTheStartingPointOfTheBoard() {
        assertEquals(0, turtle.getPosition().getRowPosition());
        assertEquals(0, turtle.getPosition().getColumnPosition());
    }

    @Test
    void penIsUpAtTheDefaultState(){
        assertTrue(turtle.penIsUp());
    }

    @Test
    public void createANewTurtleAndChangeTheStateOfThePen(){
        assertTrue(turtle.penIsUp());
        turtle.movePenDown();
        assertFalse(turtle.penIsUp());
    }

    @Test
    void changeTheStateOfTheTurtleMultipleTimes(){
        assertTrue(turtle.penIsUp());
        turtle.movePenDown();
        assertFalse(turtle.penIsUp());
        turtle.movePenUp();
        assertTrue(turtle.penIsUp());
    }

    @Test
    void turtleCanMoveRight(){
        assertEquals(EAST, turtle.getCurrentDirection());
        turtle.turnRight();
        assertEquals(SOUTH, turtle.getCurrentDirection());
    }

    @Test
    void turtleCanTurnRightFourTimesAndReturnToEast(){
        assertEquals(EAST, turtle.getCurrentDirection());
        turtle.turnRight();
        assertEquals(SOUTH, turtle.getCurrentDirection());
        turtle.turnRight();
        assertEquals(WEST, turtle.getCurrentDirection());
        turtle.turnRight();
        assertEquals(NORTH, turtle.getCurrentDirection());
        turtle.turnRight();
        assertEquals(EAST, turtle.getCurrentDirection());
    }

    @Test
    void turtleCanTurnLeft(){
        assertEquals(EAST, turtle.getCurrentDirection());
        turtle.turnLeft();
        assertEquals(NORTH, turtle.getCurrentDirection());
    }

    @Test
    void  turtleCanTurnLeftFourTimesAndReturnToEast(){
        assertEquals(EAST, turtle.getCurrentDirection());
        turtle.turnLeft();
        assertEquals(NORTH, turtle.getCurrentDirection());
        turtle.turnLeft();
        assertEquals(WEST, turtle.getCurrentDirection());
        turtle.turnLeft();
        assertEquals(SOUTH, turtle.getCurrentDirection());
        turtle.turnLeft();
        assertEquals(EAST, turtle.getCurrentDirection());
    }

    @Test
    void turtleCanMoveForwardWhilePenIsUp(){
        assertEquals(0, turtle.getPosition().getRowPosition());
        assertEquals(0, turtle.getPosition().getColumnPosition());
        assertEquals(EAST, turtle.getCurrentDirection() );
        turtle.moveForward(5);
        assertEquals(0, turtle.getPosition().getRowPosition());
        assertEquals(5, turtle.getPosition().getColumnPosition());

    }

    @Test
    void turtleCanMoveForwardAndChangeDirection(){
        assertEquals(EAST, turtle.getCurrentDirection());
        turtle.moveForward(10);
        assertEquals(10,  turtle.getPosition().getColumnPosition());
        turtle.turnLeft();
        turtle.moveForward(5);
        assertEquals(5, turtle.getPosition().getRowPosition());

    }
}