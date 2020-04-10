package nl.sogyo.roborally.domain.squares;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class TestGearRight {

    @Test
    public void TestDestinationIsSelf(){
        Square gearRight = new GearRight();
        assertEquals(gearRight, gearRight.getDestination());
    }

}