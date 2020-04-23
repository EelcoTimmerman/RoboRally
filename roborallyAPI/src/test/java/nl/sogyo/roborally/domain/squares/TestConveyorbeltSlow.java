package nl.sogyo.roborally.domain.squares;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import nl.sogyo.roborally.domain.Direction;

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
}