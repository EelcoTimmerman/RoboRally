package nl.sogyo.roborally.domain.squares;

import static org.junit.Assert.fail;

import org.junit.Test;

public class TestPit {

    @Test
    public void TestGetDestinationThrowsException(){
        Square pit = new Pit();
        try {
            Square pitDestination = pit.getDestination();
            fail("Robots should never stand on a pit after step 1.");
            
        } catch (RuntimeException e) {
        }
    }

}