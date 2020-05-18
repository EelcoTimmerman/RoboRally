package nl.sogyo.roborally.domain;

import nl.sogyo.roborally.domain.cards.Deck;
import nl.sogyo.roborally.domain.robots.Robot;
import nl.sogyo.roborally.domain.squares.Board;
import nl.sogyo.roborally.domain.squares.BoardFactory;

import org.junit.Test;

import nl.sogyo.roborally.domain.cards.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

public class TestDeck{
    
    @Test
    public void TestCreateDeck(){
        Deck deck = new Deck();
        assert(deck.getSize() == 84);
    }

    @Test
    public void TestCreateDeck1(){
        Deck deck = new Deck();
        List<Card> cardsInHand = deck.createHand(0);
        assert(cardsInHand.size() == 9);
        assert(deck.getSize() == 84-9);
    }

    @Test
    public void TestRobotTakesHand(){
        Robot robot = new Robot("e",1);
        Roborally roborally = new Roborally(robot);
        Deck deck = roborally.getDeck();
        robot.drawCards(deck);
        assert(deck.getSize() == 84-9);
    }

    @Test
    public void TestTwoRobotsTakeHand(){
        Robot robot1 = new Robot("e",1);
        Robot robot2 = new Robot("d",2);
        Roborally roborally = new Roborally(robot1);
        roborally.addRobot(robot2);
        Deck deck = roborally.getDeck();
        robot1.drawCards(deck);
        robot2.drawCards(deck);
        assert(deck.getSize() == 84-18);
    }

    @Test
    public void TestDeckResetsAfterRound(){
        Board board = BoardFactory.createSimpleBoard();
        Robot robot1 = new Robot("eelco",1);
        Robot robot2 = new Robot("said",2);
        Roborally roborally = new Roborally(board, robot1);
        roborally.addRobot(robot2);
        Deck deck = roborally.getDeck();
        robot1.program(0);
        robot2.program(0);
        roborally.playRoundIfAllRobotsReady();
        assertEquals(84, deck.getSize());
    }
}