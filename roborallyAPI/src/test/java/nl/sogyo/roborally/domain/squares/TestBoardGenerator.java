package nl.sogyo.roborally.domain.squares;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;
import java.util.LinkedList;

public class TestBoardGenerator {

    @Test
    public void TestCreateNewRow() {
        LinkedList<LinkedList> board = BoardGenerator.generateBoard("E-E*NR*L-SN");
        assertEquals(board.size(), 2);
    }

}