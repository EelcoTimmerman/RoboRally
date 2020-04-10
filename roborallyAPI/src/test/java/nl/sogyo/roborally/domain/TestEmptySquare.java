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

    @Test
    public void TestEastNeighbour() {
        Square emptySquare = new EmptySquare();
        Square emptySquare2 = new EmptySquare();
        emptySquare.setEastNeighbour(emptySquare2);

        assert (emptySquare.hasNeighbour(Direction.EAST));
        assertEquals(emptySquare2, emptySquare.getNeighbour(Direction.EAST));
    }

    @Test
    public void TestSouthNeighbour(){
        Square emptySquare = new EmptySquare();
        Square emptySquare2 = new EmptySquare();
        emptySquare.setSouthNeighbour(emptySquare2);

        assert(emptySquare.hasNeighbour(Direction.SOUTH));
        assertEquals(emptySquare2, emptySquare.getNeighbour(Direction.SOUTH));
    }

    @Test
    public void TestWestNeighbour(){
        Square emptySquare = new EmptySquare();
        Square emptySquare2 = new EmptySquare();
        emptySquare.setWestNeighbour(emptySquare2);

        assert(emptySquare.hasNeighbour(Direction.WEST));
        assertEquals(emptySquare2, emptySquare.getNeighbour(Direction.WEST));
    }
}