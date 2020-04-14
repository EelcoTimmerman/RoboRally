package nl.sogyo.roborally.domain.squares;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;

import org.junit.Test;

public class TestSquare {

    @Test
    public void TestCreateNewRow(){
        LinkedList<LinkedList> board = BoardGenerator.generateBoard("E-E*NR*L-SN");
        assertEquals(board.size(), 2);
    }
    
    
    
    
}
