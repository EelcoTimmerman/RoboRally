package nl.sogyo.roborally.domain;

import nl.sogyo.roborally.domain.cards.Deck;
import org.junit.Test;

public class TestDeck {
    
    @Test
    public void TestCreateDeck(){
        Deck deck = new Deck();
        deck.createDeck();
    }
}