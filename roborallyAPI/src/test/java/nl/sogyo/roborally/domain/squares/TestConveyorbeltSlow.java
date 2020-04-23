package nl.sogyo.roborally.domain.squares;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import nl.sogyo.roborally.domain.Direction;
import nl.sogyo.roborally.domain.robots.Robot;
import nl.sogyo.roborally.domain.rulebooks.RulebookSquares;

public class TestConveyorbeltSlow{

    @Test
    public void testHasMovementDirectionNORTH(){
        SlowConveyorbelt conveyorbelt = new SlowConveyorbelt(Direction.NORTH);
        assertEquals(Direction.NORTH, conveyorbelt.getMovementDirection());
    }

    @Test
    public void testHasMovementDirectionEAST(){
        SlowConveyorbelt conveyorbelt = new SlowConveyorbelt(Direction.EAST);
        assertEquals(Direction.EAST, conveyorbelt.getMovementDirection());
    }

    @Test
    public void testHasMovementDirectionSOUTH(){
        SlowConveyorbelt conveyorbelt = new SlowConveyorbelt(Direction.SOUTH);
        assertEquals(Direction.SOUTH, conveyorbelt.getMovementDirection());
    }

    @Test
    public void testHasMovementDirectionWEST(){
        SlowConveyorbelt conveyorbelt = new SlowConveyorbelt(Direction.WEST);
        assertEquals(Direction.WEST, conveyorbelt.getMovementDirection());
    }

    @Test
    public void testMovementRobotOnBeltNORTH(){
        String boardString = "ES-X*ES-X*||*CSN-X*ES-X";
        Board board = new Board(boardString);
        Robot robot = new Robot(0,1);
        RulebookSquares rulebookSquares = new RulebookSquares(board, robot);
        rulebookSquares.playBoardElements();
        assert(robot.getXCoordinate() == 0 && robot.getYCoordinate() == 0);
    }

    @Test
    public void testMovementRobotOnBeltEAST(){
        String boardString = "CSE-X*ES-X*||*ES-X*ES-X";
        Board board = new Board(boardString);
        Robot robot = new Robot(0,0);
        RulebookSquares rulebookSquares = new RulebookSquares(board, robot);
        rulebookSquares.playBoardElements();
        assert(robot.getXCoordinate() == 1 && robot.getYCoordinate() == 0);
    }

    @Test
    public void testMovementRobotOnBeltSOUTH(){
        String boardString = "CSS-X*ES-X*||*ES-X*ES-X";
        Board board = new Board(boardString);
        Robot robot = new Robot(0,0);
        RulebookSquares rulebookSquares = new RulebookSquares(board, robot);
        rulebookSquares.playBoardElements();
        assert(robot.getXCoordinate() == 0 && robot.getYCoordinate() == 1);
    }

    @Test
    public void testMovementRobotOnBeltWEST(){
        String boardString = "ES-X*CSW-X*||*ES-X*ES-X";
        Board board = new Board(boardString);
        Robot robot = new Robot(1,0);
        RulebookSquares rulebookSquares = new RulebookSquares(board, robot);
        rulebookSquares.playBoardElements();
        assert(robot.getXCoordinate() == 0 && robot.getYCoordinate() == 0);
    }
}