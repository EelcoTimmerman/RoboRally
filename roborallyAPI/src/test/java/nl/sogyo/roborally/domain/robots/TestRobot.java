package nl.sogyo.roborally.domain.robots;

import org.junit.Test;

// import nl.sogyo.roborally.domain.Direction;
import nl.sogyo.roborally.domain.squares.EmptySquare;

public class TestRobot {

    @Test
    public void TestGetStartPosition(){
        EmptySquare startSquare = new EmptySquare(true, false, false, false);
        Robot robot = new Robot(startSquare);
        assert(robot.getPosition() == startSquare);
    }

    @Test
    public void TestIsAtSquare(){
        EmptySquare startSquare = new EmptySquare(true, false, false, false);
        Robot robot = new Robot(startSquare);
        assert(robot.isAt(startSquare));
    }

    @Test
    public void TestTakeDamage(){
        EmptySquare startSquare = new EmptySquare(true, false, false, false);
        Robot robot = new Robot(startSquare);
        robot.takeDamage(1);
        assert(robot.getHealth() == 8);
    }
}