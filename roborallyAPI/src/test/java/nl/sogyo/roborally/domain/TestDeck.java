package nl.sogyo.roborally.domain;

import nl.sogyo.roborally.domain.cards.Deck;
import nl.sogyo.roborally.domain.robots.Robot;

import org.junit.Test;

import nl.sogyo.roborally.domain.cards.*;
import java.util.List;

public class TestDeck{
    
    @Test
    public void TestCreateDeck(){
        Deck deck = new Deck();
        System.out.print(deck.getDeck().size());
        assert(deck.getDeck().size() == 84);
    }

    @Test
    public void TestCreateDeck1(){
        Deck deck = new Deck();
        List<ICard> cardsInHand = deck.getHand(0);
        assert(cardsInHand.size() == 9);
        assert(deck.getDeck().size() == 84-9);
    }

    @Test
    public void TestResetDeck(){
        Robot robot = new Robot();
        Roborally roborally = new Roborally(robot);
        List<ICard> cardsInHand = roborally.getHandOfCards(robot);
        assert(cardsInHand.size() == 9);
        cardsInHand = roborally.getHandOfCards(robot);
        roborally.playRoundIfAllRobotsReady();
        cardsInHand = roborally.getHandOfCards(robot);
        roborally.playRoundIfAllRobotsReady();
        cardsInHand = roborally.getHandOfCards(robot);
    }
}