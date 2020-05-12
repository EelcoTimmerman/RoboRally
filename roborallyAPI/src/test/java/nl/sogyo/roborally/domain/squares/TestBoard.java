package nl.sogyo.roborally.domain.squares;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

public class TestBoard{


    @Test
    public void testGetSquare(){
        Board board = Board.createSimpleBoard();
        ArrayList<ArrayList<Square>> list = board.getBoard();
        assertEquals(list.get(1).get(0), board.getSquare(0, 1));
    }

    @Test
    public void testWallConsistencyCheckTrue(){
        Board board = Board.createSimpleBoard();
        assert(board.hasConsistentWalls());
    }

    @Test
    public void testWallConsistencyCheckFalse(){
        Board board = Board.createWrongWalls();
        assert(!board.hasConsistentWalls());
    }

    @Test
    public void testRectangularBoardTrue(){
        Board board = Board.createSimpleBoard();
        assert(board.isRectangular());
    }
    
    @Test
    public void testRectangularBoardFalse(){
        Board board = Board.createNonRectangularBoard();
        assert(!board.isRectangular());
    }

    @Test
    public void testLaserTestBoard(){
        Board board = Board.createLaserTestBoard();
        assert(board.isRectangular());
        assert(board.hasConsistentWalls());
    }

    @Test
    public void testAllLasersOnWallTrue(){
        Board board = Board.createLaserTestBoard();
        assert(board.allLasersOnWalls());
    }

    @Test
    public void testAllLasersOnWallFalse(){
        Board board = Board.createFaultyLaserTestBoard();
        assert(!board.allLasersOnWalls());
    }

    @Test
    public void testTESTBOARD4X4(){
        Board TESTBOARD4X4 = Board.createTESTBOARD4X4();
        assert(TESTBOARD4X4.isRectangular());
        assert(TESTBOARD4X4.hasConsistentWalls());
        assert(TESTBOARD4X4.allLasersOnWalls());
    }

    public void testPitTestBoard(){
        Board pitTestBoard = Board.createPitTestBoard();        
        assert(pitTestBoard.isRectangular());
        assert(pitTestBoard.hasConsistentWalls());
        assert(pitTestBoard.allLasersOnWalls());
    }

    public void testSlowConveyorbeltTestBoard(){        
        Board slowConveyorbeltTestBoard = Board.createSlowConveyorbeltTestBoard();
        assert(slowConveyorbeltTestBoard.isRectangular());
        assert(slowConveyorbeltTestBoard.hasConsistentWalls());
        assert(slowConveyorbeltTestBoard.allLasersOnWalls());
    }

    public void testSlowConveyorbeltTestBoardWalls(){
        Board slowConveyorbeltTestBoardWalls = Board.createSlowConveyorbeltTestBoardOther();
        assert(slowConveyorbeltTestBoardWalls.isRectangular());
        assert(slowConveyorbeltTestBoardWalls.hasConsistentWalls());
        assert(slowConveyorbeltTestBoardWalls.allLasersOnWalls());
    }

    public void testGearTestBoard(){
        Board gearTestBoard = Board.createGearTestBoard();
        assert(gearTestBoard.isRectangular());
        assert(gearTestBoard.hasConsistentWalls());
        assert(gearTestBoard.allLasersOnWalls());
    }

    public void testRobotLaserTestBoard(){
        Board robotLaserTestBoard = Board.createRobotLaserWallTestBoard();
        assert(robotLaserTestBoard.isRectangular());
        assert(robotLaserTestBoard.hasConsistentWalls());
        assert(robotLaserTestBoard.allLasersOnWalls());
    }
}