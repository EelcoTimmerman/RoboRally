package nl.sogyo.roborally.domain.robots;

import org.junit.Test;

import nl.sogyo.roborally.domain.Direction;
// import nl.sogyo.roborally.domain.Direction;
import nl.sogyo.roborally.domain.squares.EmptySquare;
import nl.sogyo.roborally.domain.squares.Square;

public class TestRobot {

    @Test
    public void TestGetStartPosition(){
        Square startSquare = new EmptySquare();
        Robot robot = new Robot(startSquare);
        assert(robot.getPosition() == startSquare);
    }

    @Test
    public void TestIsAtSquare(){
        Square startSquare = new EmptySquare();
        Robot robot = new Robot(startSquare);
        assert(robot.isAt(startSquare));
    }

    @Test
    public void TestTakeDamage(){
        Square startSquare = new EmptySquare();
        Robot robot = new Robot(startSquare);
        robot.takeDamage(1);
        assert(robot.getHealth() == 8);
    }

    @Test
    public void TestMoveOneNorth() {
        Square startSquare = new EmptySquare();
        Square northOfStartSquare = new EmptySquare();
        Robot robot = new Robot(startSquare);
        startSquare.setNorthNeighbour(northOfStartSquare);
        robot.moveForward();
        assert(robot.isAt(northOfStartSquare));
    }

    @Test
    public void TestMoveOneEast() {
        Square startSquare = new EmptySquare();
        Square eastOfStartSquare = new EmptySquare();
        Robot robot = new Robot(startSquare);
        robot.setOrientation(Direction.EAST);
        startSquare.setEastNeighbour(eastOfStartSquare);
        robot.moveForward();
        assert(robot.isAt(eastOfStartSquare));
    }

    @Test
    public void TestMoveOneSouth() {
        Square startSquare = new EmptySquare();
        Square southOfStartSquare = new EmptySquare();
        Robot robot = new Robot(startSquare);
        robot.setOrientation(Direction.SOUTH);
        startSquare.setSouthNeighbour(southOfStartSquare);
        robot.moveForward();
        assert(robot.isAt(southOfStartSquare));
    }

    @Test
    public void TestMoveOneWest() {
        Square startSquare = new EmptySquare();
        Square westOfStartSquare = new EmptySquare();
        Robot robot = new Robot(startSquare);
        robot.setOrientation(Direction.WEST);
        startSquare.setWestNeighbour(westOfStartSquare);
        robot.moveForward();
        assert(robot.isAt(westOfStartSquare));
    }

}