package nl.sogyo.roborally.domain;

import nl.sogyo.roborally.domain.cards.Deck;
import org.junit.Test;
import nl.sogyo.roborally.domain.cards.*;
import util.List;

public class TestDeck {
    
    @Test
    public void TestCreateDeck(){
        Deck deck = new Deck();
        deck.createDeck();
        List<ICard> cardsInHand = deck.getHand();
        assert(deck.getSize() == 84);  
    }

    @Test
    public void TestCreateDeck2(){
        Deck deck = new Deck();
        deck.createDeck();
        assert(deck.getSize() == 84);
    }


}