package nl.sogyo.roborally.domain.squares;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import nl.sogyo.roborally.domain.Direction;
import nl.sogyo.roborally.domain.robots.Robot;

public class TestConveyorbeltSlow {

    @Test
    public void TestDestinationNorth(){
        Square testConveyor = new SlowConveyorbelt(Direction.NORTH);
        Square emptySquare = new EmptySquare();
        testConveyor.setNorthNeighbour(emptySquare);
        assertEquals(emptySquare, testConveyor.getDestination());
    }

    @Test
    public void TestRobotDestionationNorth() {
        Square startConveyer = new SlowConveyorbelt(Direction.NORTH);
        Square northOfStartConveyer = new EmptySquare();
        Robot robot = new Robot(startConveyer);
        startConveyer.setNorthNeighbour(northOfStartConveyer);
        Square destination = robot.getPosition().getDestination();
        assertEquals(northOfStartConveyer, destination);
    }
}