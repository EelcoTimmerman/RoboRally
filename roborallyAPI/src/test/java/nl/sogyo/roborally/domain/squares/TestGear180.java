package nl.sogyo.roborally.domain.squares;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class TestGear180 {

    @Test
    public void TestDestinationIsSelf(){
        Square gear180 = new Gear180();
        assertEquals(gear180, gear180.getDestination());
    }
}