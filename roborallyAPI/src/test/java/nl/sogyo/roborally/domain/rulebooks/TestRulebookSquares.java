package nl.sogyo.roborally.domain.rulebooks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import nl.sogyo.roborally.domain.Direction;
import nl.sogyo.roborally.domain.robots.Robot;
import nl.sogyo.roborally.domain.squares.Board;

public class TestRulebookSquares {

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
    
    @Test
    public void testConveyorbeltWall(){
        String boardString = "ES-X*CSW-W*||*ES-X*ES-X";
        Board board = new Board(boardString);
        Robot robot = new Robot(1,0);
        RulebookSquares rulebookSquares = new RulebookSquares(board, robot);
        rulebookSquares.playBoardElements();
        assert(robot.getXCoordinate() == 1 && robot.getYCoordinate() == 0);
    }

    @Test
    public void testConveyorbeltPit(){
        String boardString = "PT-X*CSW-X*||*ES-X*ES-X";
        Board board = new Board(boardString);
        Robot robot = new Robot(1,0);
        robot.setRespawnPoint(0, 1);
        RulebookSquares rulebookSquares = new RulebookSquares(board, robot);
        rulebookSquares.playBoardElements();
        assert(robot.getXCoordinate() == 0 && robot.getYCoordinate() == 1);
    }

    @Test
    public void testConveyorbeltOffBoard(){
        String boardString = "CSW-X*CSW-X*||*ES-X*ES-X";
        Board board = new Board(boardString);
        Robot robot = new Robot(0,0);
        robot.setRespawnPoint(1, 1);
        RulebookSquares rulebookSquares = new RulebookSquares(board, robot);
        rulebookSquares.playBoardElements();
        assert(robot.getXCoordinate() == 1 && robot.getYCoordinate() == 1);
    }
    
    @Test
    public void testGearReverse(){
        String boardString = "180-X*ES-X*||*ES-X*ES-X";
        Board board = new Board(boardString);
        Robot robot = new Robot(0,0);
        RulebookSquares rulebookSquares = new RulebookSquares(board, robot);
        assert(robot.getOrientation().equals(Direction.NORTH));
        rulebookSquares.playBoardElements();
        assert(robot.getOrientation().equals(Direction.SOUTH));
    }
    
    @Test
    public void testGearRight(){
        String boardString = "GR-X*ES-X*||*ES-X*ES-X";
        Board board = new Board(boardString);
        Robot robot = new Robot(0,0);
        RulebookSquares rulebookSquares = new RulebookSquares(board, robot);
        assert(robot.getOrientation().equals(Direction.NORTH));
        rulebookSquares.playBoardElements();
        assert(robot.getOrientation().equals(Direction.EAST));
    }
    
    @Test
    public void testGearLeft(){
        String boardString = "GL-X*ES-X*||*ES-X*ES-X";
        Board board = new Board(boardString);
        Robot robot = new Robot(0,0);
        RulebookSquares rulebookSquares = new RulebookSquares(board, robot);
        assert(robot.getOrientation().equals(Direction.NORTH));
        rulebookSquares.playBoardElements();
        assert(robot.getOrientation().equals(Direction.WEST));
    }
    
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