package nl.sogyo.roborally.domain.squares;

import org.junit.Test;
import nl.sogyo.roborally.domain.Direction;
import nl.sogyo.roborally.domain.robots.Robot;
import nl.sogyo.roborally.domain.rulebooks.RulebookSquares;

public class TestCheckpoint{

    @Test
    public void testCheckpoint(){
        String boardString = "ES-X*ES-X*||*CH-X*ES-X";
        Board board = new Board(boardString);
        Robot robot = new Robot(0,0, Direction.SOUTH);
        RulebookSquares rulebookSquares = new RulebookSquares(board, robot);
        assert(robot.getRespawnXCoordinate() == 0 && robot.getRespawnYCoordinate() == 0);
        robot.moveForward();
        rulebookSquares.playBoardElements();
        assert(robot.getXCoordinate() == 0 && robot.getYCoordinate() == 1);
        assert(robot.getRespawnXCoordinate() == 0 && robot.getRespawnYCoordinate() == 1);
    }

    @Test
    public void testCheckpoint2(){
        String boardString = "ES-X*ES-X*||*ES-X*CH-X";
        Board board = new Board(boardString);
        Robot robot = new Robot(0,1, Direction.EAST);
        RulebookSquares rulebookSquares = new RulebookSquares(board, robot);
        assert(robot.getRespawnXCoordinate() == 0 && robot.getRespawnYCoordinate() == 1);
        robot.moveForward();
        rulebookSquares.playBoardElements();
        assert(robot.getRespawnXCoordinate() == 1 && robot.getRespawnYCoordinate() == 1);
    }
}