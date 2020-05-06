package nl.sogyo.roborally.domain;

import nl.sogyo.roborally.domain.cards.Deck;

import java.util.List;

import org.junit.Test;
import nl.sogyo.roborally.domain.cards.*;

public class TestDeck {
    
    @Test
    public void TestCreateDeck(){
        Deck deck = new Deck();
        deck.createDeck();
        assert(deck.getSize() == 84);  
    }

    @Test
    public void TestCreateDeck2(){
        Deck deck = new Deck();
        deck.createDeck();
        List<ICard> cardsInHand = deck.getHand(0);
        assert(cardsInHand.size() == 9);
    }


}