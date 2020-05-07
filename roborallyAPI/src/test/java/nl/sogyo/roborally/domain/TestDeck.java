package nl.sogyo.roborally.domain;

import nl.sogyo.roborally.domain.cards.Deck;
import org.junit.Test;

import nl.sogyo.roborally.domain.cards.*;
import java.util.List;

public class TestDeck {
    
    @Test
    public void TestCreateDeck(){
        Deck deck = new Deck();
        deck.createDeck();
        List<ICard> cardsInHand = deck.getHand(0);
        assert(cardsInHand.size() == 9);
        assert(deck.getSize() == 84-9);
    }

    @Test
    public void TestCreateDeck1(){
        Deck deck = new Deck();
        deck.createDeck();
        List<ICard> cardsInHand = deck.getHand(0);
        assert(cardsInHand.size() == 9);
        assert(deck.getSize() == 84-9);
    }

    @Test
    public void TestCreateDeck2(){
        Deck deck = new Deck();
        deck.createDeck();
        assert(deck.getSize() == 84);
    }
}