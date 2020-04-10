package nl.sogyo.roborally.domain.robots;

import org.junit.Test;

import nl.sogyo.roborally.domain.Direction;
import nl.sogyo.roborally.domain.squares.EmptySquare;

public class TestRobot {

    @Test
    public void TestGetStartPosition(){
        EmptySquare startSquare = new EmptySquare(true, false, false, false);
        Robot robot = new Robot(startSquare);
        assert(robot.getPosition() == startSquare);
    }
}