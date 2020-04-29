package nl.sogyo.roborally.domain.robots;

import org.junit.Test;

import nl.sogyo.roborally.domain.Direction;
import nl.sogyo.roborally.domain.cards.ICard;
import nl.sogyo.roborally.domain.cards.MoveBackCard;
import nl.sogyo.roborally.domain.cards.MoveOneCard;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestRobot{

    @Test
    public void testGetStartPosition(){
        Robot robot = new Robot(0,1);
        assertEquals(0, robot.getXCoordinate());
        assertEquals(1, robot.getYCoordinate());
    }

    @Test
    public void testIsAtSquare(){
        Robot robot = new Robot(2,3);
        assert(robot.isAt(2,3));
    }

    @Test
    public void testTakeDamage(){
        Robot robot = new Robot(2,2);
        robot.takeDamage(1);
        assert(robot.getHealth() == 8);
    }

    @Test
    public void testMoveOneNorth() {
        Robot robot = new Robot(2,3);
        robot.setOrientation(Direction.NORTH);
        robot.moveForward();
        assert(robot.isAt(2,2));
    }

    @Test
    public void testMoveOneEast() {
        Robot robot = new Robot(2,3);
        robot.setOrientation(Direction.EAST);
        robot.moveForward();
        assert(robot.isAt(3,3));
    }

    @Test
    public void testMoveOneSouth() {
        Robot robot = new Robot(2,3);
        robot.setOrientation(Direction.SOUTH);
        robot.moveForward();
        assert(robot.isAt(2,4));
    }

    @Test
    public void testMoveOneWest() {
        Robot robot = new Robot(2,3);
        robot.setOrientation(Direction.WEST);
        robot.moveForward();
        assert(robot.isAt(1,3));
    }

    @Test
    public void testProgramRobotMoveForward(){
        Robot robot = new Robot();
        ICard card1 = new MoveOneCard();
        robot.program(card1);
        ICard card2 = robot.getCard();
        assertEquals(card1, card2);
    }

    @Test
    public void testProgramRobotMoveBackward(){
        Robot robot = new Robot();
        robot.program(6);
        assertTrue(robot.getCard() instanceof MoveBackCard);
    }

}