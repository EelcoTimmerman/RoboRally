package nl.sogyo.roborally.domain;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TestEmptySquare {

    @Test
    public void TestEmptySquareWallTrue(){
        Square emptySquare = new EmptySquare(true, true, true, true);
        assert(emptySquare.hasWallAt(Direction.NORTH));
        assert(emptySquare.hasWallAt(Direction.EAST));
        assert(emptySquare.hasWallAt(Direction.SOUTH));
        assert(emptySquare.hasWallAt(Direction.WEST));
    }

    @Test
    public void TestEmptySquareWallFalse(){
        Square emptySquare = new EmptySquare(false, false, false, false);
        assert(!emptySquare.hasWallAt(Direction.NORTH));
        assert(!emptySquare.hasWallAt(Direction.EAST));
        assert(!emptySquare.hasWallAt(Direction.SOUTH));
        assert(!emptySquare.hasWallAt(Direction.WEST));
    }

    @Test
    public void TestNorthNeighbour(){
        Square emptySquare = new EmptySquare();
        Square emptySquare2 = new EmptySquare();
        emptySquare.setNorthNeighbour(emptySquare2);

        assert(emptySquare.hasNeighbour(Direction.NORTH));
        assertEquals(emptySquare2, emptySquare.getNeighbour(Direction.NORTH));
    }
}