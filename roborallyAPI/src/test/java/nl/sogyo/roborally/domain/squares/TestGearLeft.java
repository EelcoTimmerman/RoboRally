package nl.sogyo.roborally.domain.squares;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class TestGearLeft {

    @Test
    public void TestDestinationIsSelf(){
        Square gearLeft = new GearLeft();
        assertEquals(gearLeft, gearLeft.getDestination());
    }

}