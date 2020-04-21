package nl.sogyo.roborally.domain.squares;

import org.junit.jupiter.api.Test;

import nl.sogyo.roborally.domain.*;

public class TestEmptySquare{

    @Test
    public void testEmptySquareWallTrue(){
        Square emptySquare = new EmptySquare();
        emptySquare.setWalls("NESW");
        assert(emptySquare.hasWallAt(Direction.NORTH));
        assert(emptySquare.hasWallAt(Direction.EAST));
        assert(emptySquare.hasWallAt(Direction.SOUTH));
        assert(emptySquare.hasWallAt(Direction.WEST));
    }

    @Test
    public void testEmptySquareWallFalse(){
        Square emptySquare = new EmptySquare();
        assert(!emptySquare.hasWallAt(Direction.NORTH));
        assert(!emptySquare.hasWallAt(Direction.EAST));
        assert(!emptySquare.hasWallAt(Direction.SOUTH));
        assert(!emptySquare.hasWallAt(Direction.WEST));
    }

    @Test
    public void testEmptySquareTwoNorthWalls(){
        Square emptySquare = new EmptySquare();
        emptySquare.setWalls("NNO, not me.");
        assert(emptySquare.hasWallAt(Direction.NORTH));
        assert(!emptySquare.hasWallAt(Direction.EAST));
        assert(!emptySquare.hasWallAt(Direction.SOUTH));
        assert(!emptySquare.hasWallAt(Direction.WEST));
    }
}