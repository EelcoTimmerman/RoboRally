package nl.sogyo.roborally.domain;

// import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TestDirection {

    @Test
    public void TestDirectionNames(){
        String northName = Direction.NORTH.toString();
        String eastName = Direction.EAST.toString();
        String southName = Direction.SOUTH.toString();
        String westName = Direction.WEST.toString();

        assertEquals("North", northName);
        assertEquals("East", eastName);
        assertEquals("South", southName);
        assertEquals("West", westName);
    }

}