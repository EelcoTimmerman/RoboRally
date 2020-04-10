package nl.sogyo.roborally.domain.squares;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import nl.sogyo.roborally.domain.Direction;

public class TestConveyorbeltSlow {

    @Test
    public void TestDestinationNorth(){
        Square testConveyor = new SlowConveyorbelt(Direction.NORTH);
        Square emptySquare = new EmptySquare();
        testConveyor.setNorthNeighbour(emptySquare);
        assertEquals(emptySquare, testConveyor.getDestination());
    }

}