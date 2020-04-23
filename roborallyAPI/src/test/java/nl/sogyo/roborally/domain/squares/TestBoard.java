package nl.sogyo.roborally.domain.squares;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.Test;
import nl.sogyo.roborally.domain.*;

public class TestBoard{

    @Test
    public void testGenerateFirstSquareWithWalls(){
        String boardString = "ES-ES*ES-SW*||*ES-NE*ES-NW";
        Board board = new Board(boardString);
        Square firstSquare = board.getSquare(0,0);
        assert(firstSquare instanceof EmptySquare);
        assert(firstSquare.hasWallAt(Direction.EAST));
        assert(firstSquare.hasWallAt(Direction.SOUTH));
    }

    @Test
    public void testEvenRowsAndColsMakeARectangularSquare(){
        String boardString = "ES-X*ES-X*||*ES-X*ES-X";
        Board board = new Board(boardString);
        ArrayList<ArrayList<Square>> list = board.getBoard();
        Square firstSquare = board.getSquare(0,0);
        assert(firstSquare instanceof EmptySquare);
        assert(list.size() == 2);
        assert(list.get(0).size() == 2);
        assert(list.get(1).size() == 2);
    }

    @Test
    public void testUnevenColsMakeNotARectangularSquare(){
        String boardString = "ES-X*ES-X*||*ES-X*ES-X*ES-X";
        assertThrows(RuntimeException.class, () -> {
            new Board(boardString);
        });
    }

    @Test
    public void testUnevenRowsMakeNotARectangularSquare(){
        String boardString = "ES-X*ES-X*||*ES-X*ES-X*||*ES-X";
        assertThrows(RuntimeException.class, () -> {
            new Board(boardString);
        });
    }

    @Test
    public void testGenerateSquares(){
        String boardString = "ES-ES*ES-SW*||*ES-NE*ES-NW";
        Board board = new Board(boardString);
        Square firstSquare = board.getSquare(0,0);
        Square secondSquare = board.getSquare(0,1);
        Square thirdSquare = board.getSquare(1,0);
        Square fourthSquare = board.getSquare(1,1);
        assert(firstSquare instanceof EmptySquare);
        assert(secondSquare instanceof EmptySquare);
        assert(thirdSquare instanceof EmptySquare);
        assert(fourthSquare instanceof EmptySquare);
    }

    @Test
    public void testCoherentWalls(){
        String boardStringCorrect = "ES-ES*ES-SW*||*ES-NE*ES-NW";
        String boardStringWrong = "ES-E*ES-X";
        String boardStringWrong2 = "ES-E*ES-EW*ES-X";
        String boardStringWrong3 = "ES-ES*ES-SW*||*ES-NE*ES-W";
        assert(new Board(boardStringCorrect) instanceof Board);
        assertThrows(RuntimeException.class, () -> {
            new Board(boardStringWrong);
        });
        assertThrows(RuntimeException.class, () -> {
            new Board(boardStringWrong2);
        });
        assertThrows(RuntimeException.class, () -> {
            new Board(boardStringWrong3);
        });
    }

    @Test
    public void testAllWallsAtEdgesOfBoard(){
        String boardString = "ES-NW*ES-NE*||*ES-WS*ES-ES";
        assert(new Board(boardString) instanceof Board);
    }

    @Test
    public void testLargeBoard(){
        String boardString = "ES-X*ES-X*ES-N*ES-X*ES-N*ES-X*ES-X*ES-N*ES-X*ES-N*ES-X*ES-X*||*";
        boardString += "ES-X*PT-X*PT-X*ES-X*ES-X*ES-X*ES-X*ES-X*ES-X*PT-X*PT-X*ES-X*||*";
        boardString += "ES-W*PT-X*GR-X*CSE-X*CSE-X*CSE-S*CSE-X*CSE-X*CSE-X*GR-X*PT-X*ES-E*||*";
        boardString += "ES-X*ES-X*CSN-X*GL-X*CSW-X*CSW-N*CSW-X*CSW-X*GL-X*CSS-X*ES-X*ES-X*||*";
        boardString += "ES-W*ES-X*CSN-X*CSS-X*ES-X*ES-X*PT-X*PT-X*CSN-X*CSS-X*ES-X*ES-E";
        Board board = new Board(boardString);
        ArrayList<ArrayList<Square>> list = board.getBoard();
        assert(board instanceof Board);
        assert(list.size() == 5);
        assert(list.get(0).size() == 12);
    }

    @Test
    public void testCreateNewRow(){
        String boardString = "ES-S*||*GL-N";
        Board board = new Board(boardString);
        ArrayList<ArrayList<Square>> list = board.getBoard();
        Square firstSquare = board.getSquare(0,0);
        Square secondSquare = board.getSquare(0,1);
        assert(firstSquare instanceof EmptySquare);
        assert(secondSquare instanceof GearLeft);
        assert(firstSquare.hasWallAt(Direction.SOUTH));
        assert(secondSquare.hasWallAt(Direction.NORTH));
        assert(list.size() == 2);
        assert(list.get(0).size() == 1);
    }

    @Test
    public void testGetSquare(){
        String boardString = "ES-X*ES-X*ES-N*ES-X*ES-N*ES-X*ES-X*ES-N*ES-X*ES-N*ES-X*ES-X*||*";
        boardString += "ES-X*PT-X*PT-X*ES-X*ES-X*ES-X*ES-X*ES-X*ES-X*PT-X*PT-X*ES-X*||*";
        boardString += "ES-W*PT-X*GR-X*CSE-X*CSE-X*CSE-S*CSE-X*CSE-X*CSE-X*GR-X*PT-X*ES-E*||*";
        boardString += "ES-X*ES-X*CSN-X*GL-X*CSW-X*CSW-N*CSW-X*CSW-X*GL-X*CSS-X*ES-X*ES-X*||*";
        boardString += "ES-W*ES-X*CSN-X*CSS-X*ES-X*ES-X*PT-X*PT-X*CSN-X*CSS-X*ES-X*ES-E";
        Board board = new Board(boardString);
        ArrayList<ArrayList<Square>> list = board.getBoard();
        assertEquals(list.get(4).get(3), board.getSquare(3, 4));
    }
}