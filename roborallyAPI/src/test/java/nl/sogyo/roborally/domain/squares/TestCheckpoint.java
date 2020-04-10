package nl.sogyo.roborally.domain.squares;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class TestCheckpoint {

    @Test
    public void TestDestinationIsSelf(){
        Square checkpoint = new Checkpoint();
        assertEquals(checkpoint, checkpoint.getDestination());
    }

}