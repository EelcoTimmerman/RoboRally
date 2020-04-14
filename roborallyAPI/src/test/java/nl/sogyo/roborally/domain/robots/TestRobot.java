package nl.sogyo.roborally.domain.robots;

import org.junit.Test;

import nl.sogyo.roborally.domain.Direction;
// import nl.sogyo.roborally.domain.Direction;
import nl.sogyo.roborally.domain.squares.EmptySquare;

public class TestRobot {

    @Test
    public void TestGetStartPosition() {
        EmptySquare startSquare = new EmptySquare(true, false, false, false);
        Robot robot = new Robot(startSquare);
        assert(robot.getPosition() == startSquare);
    }

    @Test
    public void TestIsAtSquare() {
        EmptySquare startSquare = new EmptySquare(true, false, false, false);
        Robot robot = new Robot(startSquare);
        assert(robot.isAt(startSquare));
    }

    @Test
    public void TestTakeDamage() {
        EmptySquare startSquare = new EmptySquare(true, false, false, false);
        Robot robot = new Robot(startSquare);
        robot.takeDamage(1);
        assert(robot.getHealth() == 8);
    }

    @Test
    public void TestMoveOneNorth() {
        EmptySquare startSquare = new EmptySquare();
        EmptySquare northOfStartSquare = new EmptySquare();
        Robot robot = new Robot(startSquare);
        startSquare.setNorthNeighbour(northOfStartSquare);
        robot.moveForward();
        assert(robot.isAt(northOfStartSquare));
    }

    @Test
    public void TestMoveOneEast() {
        EmptySquare startSquare = new EmptySquare();
        EmptySquare eastOfStartSquare = new EmptySquare();
        Robot robot = new Robot(startSquare);
        robot.setOrientation(Direction.EAST);
        startSquare.setEastNeighbour(eastOfStartSquare);
        robot.moveForward();
        assert(robot.isAt(eastOfStartSquare));
    }

    @Test
    public void TestMoveOneSouth() {
        EmptySquare startSquare = new EmptySquare();
        EmptySquare southOfStartSquare = new EmptySquare();
        Robot robot = new Robot(startSquare);
        robot.setOrientation(Direction.SOUTH);
        startSquare.setSouthNeighbour(southOfStartSquare);
        robot.moveForward();
        assert(robot.isAt(southOfStartSquare));
    }

    @Test
    public void TestMoveOneWest() {
        EmptySquare startSquare = new EmptySquare();
        EmptySquare westOfStartSquare = new EmptySquare();
        Robot robot = new Robot(startSquare);
        robot.setOrientation(Direction.WEST);
        startSquare.setWestNeighbour(westOfStartSquare);
        robot.moveForward();
        assert(robot.isAt(westOfStartSquare));
    }
}