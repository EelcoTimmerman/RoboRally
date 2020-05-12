package nl.sogyo.roborally.domain.squares;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

public class TestBoard{


    @Test
    public void testGetSquare(){
        Board board = BoardFactory.createSimpleBoard();
        ArrayList<ArrayList<Square>> list = board.getBoard();
        assertEquals(list.get(1).get(0), board.getSquare(0, 1));
    }

    @Test
    public void testWallConsistencyCheckTrue(){
        Board board = BoardFactory.createSimpleBoard();
        assert(board.hasConsistentWalls());
    }

    @Test
    public void testWallConsistencyCheckFalse(){
        Board board = BoardFactory.createWrongWalls();
        assert(!board.hasConsistentWalls());
    }

    @Test
    public void testRectangularBoardTrue(){
        Board board = BoardFactory.createSimpleBoard();
        assert(board.isRectangular());
    }
    
    @Test
    public void testRectangularBoardFalse(){
        Board board = BoardFactory.createNonRectangularBoard();
        assert(!board.isRectangular());
    }

    @Test
    public void testLaserTestBoard(){
        Board board = BoardFactory.createLaserTestBoard();
        assert(board.isRectangular());
        assert(board.hasConsistentWalls());
    }

    @Test
    public void testAllLasersOnWallTrue(){
        Board board = BoardFactory.createLaserTestBoard();
        assert(board.allLasersOnWalls());
    }

    @Test
    public void testAllLasersOnWallFalse(){
        Board board = BoardFactory.createFaultyLaserTestBoard();
        assert(!board.allLasersOnWalls());
    }

    @Test
    public void testTESTBOARD4X4(){
        Board TESTBOARD4X4 = BoardFactory.createTESTBOARD4X4();
        assert(TESTBOARD4X4.isRectangular());
        assert(TESTBOARD4X4.hasConsistentWalls());
        assert(TESTBOARD4X4.allLasersOnWalls());
    }

    public void testPitTestBoard(){
        Board pitTestBoard = BoardFactory.createPitTestBoard();        
        assert(pitTestBoard.isRectangular());
        assert(pitTestBoard.hasConsistentWalls());
        assert(pitTestBoard.allLasersOnWalls());
    }

    public void testSlowConveyorbeltTestBoard(){        
        Board slowConveyorbeltTestBoard = BoardFactory.createSlowConveyorbeltTestBoard();
        assert(slowConveyorbeltTestBoard.isRectangular());
        assert(slowConveyorbeltTestBoard.hasConsistentWalls());
        assert(slowConveyorbeltTestBoard.allLasersOnWalls());
    }

    public void testSlowConveyorbeltTestBoardWalls(){
        Board slowConveyorbeltTestBoardWalls = BoardFactory.createSlowConveyorbeltTestBoardOther();
        assert(slowConveyorbeltTestBoardWalls.isRectangular());
        assert(slowConveyorbeltTestBoardWalls.hasConsistentWalls());
        assert(slowConveyorbeltTestBoardWalls.allLasersOnWalls());
    }

    public void testGearTestBoard(){
        Board gearTestBoard = BoardFactory.createGearTestBoard();
        assert(gearTestBoard.isRectangular());
        assert(gearTestBoard.hasConsistentWalls());
        assert(gearTestBoard.allLasersOnWalls());
    }

    public void testRobotLaserTestBoard(){
        Board robotLaserTestBoard = BoardFactory.createRobotLaserWallTestBoard();
        assert(robotLaserTestBoard.isRectangular());
        assert(robotLaserTestBoard.hasConsistentWalls());
        assert(robotLaserTestBoard.allLasersOnWalls());
    }
}