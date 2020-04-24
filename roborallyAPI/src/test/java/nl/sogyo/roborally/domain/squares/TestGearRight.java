package nl.sogyo.roborally.domain.squares;

import org.junit.Test;
import nl.sogyo.roborally.domain.Direction;
import nl.sogyo.roborally.domain.robots.Robot;
import nl.sogyo.roborally.domain.rulebooks.RulebookSquares;

public class TestGearRight{

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
}