package nl.sogyo.roborally.domain;

import nl.sogyo.roborally.domain.cards.Deck;
import nl.sogyo.roborally.domain.robots.Robot;
import nl.sogyo.roborally.domain.squares.Board;
import nl.sogyo.roborally.domain.squares.BoardFactory;

import org.junit.Test;

import nl.sogyo.roborally.domain.cards.*;
import java.util.List;

public class TestDeck{
    
    @Test
    public void TestCreateDeck(){
        Deck deck = new Deck();
        assert(deck.getDeck().size() == 84);
    }

    @Test
    public void TestCreateDeck1(){
        Deck deck = new Deck();
        List<Card> cardsInHand = deck.createHand(0);
        assert(cardsInHand.size() == 9);
        assert(deck.getDeck().size() == 84-9);
    }

    @Test
    public void TestRobotTakesHand(){
        Robot robot = new Robot("e",1);
        Roborally roborally = new Roborally(robot);
        Deck deck = roborally.getDeck();
        robot.setHand(deck);
        assert(deck.getDeck().size() == 84-9);
    }

    @Test
    public void TestTwoRobotsTakeHand(){
        Robot robot1 = new Robot("e",1);
        Robot robot2 = new Robot("d",2);
        Roborally roborally = new Roborally(robot1);
        roborally.addRobot(robot2);
        Deck deck = roborally.getDeck();
        robot1.setHand(deck);
        robot2.setHand(deck);
        assert(deck.getDeck().size() == 84-18);
    }

    @Test
    public void TestPlayRoundReducesDeck(){
        Board board = BoardFactory.createSimpleBoard();
        Robot robot1 = new Robot("eelco",1);
        Robot robot2 = new Robot("said",2);
        Roborally roborally = new Roborally(board, robot1);
        roborally.addRobot(robot2);
        Deck deck = roborally.getDeck();
        robot1.program(0);
        robot2.program(0);
        roborally.playRoundIfAllRobotsReady();
        roborally.DiscardAllCards();
        assert(deck.getDeck().size() == 84-18);
        assert(deck.getDiscardPile().size() == 18);
    }

    @Test
    public void TestDeckRefillsAfterBeingEmpty(){
        Deck deck = new Deck();
        Robot r = new Robot();
        for(int i = 0;i<9;i++){
            r.setHand(deck);
            r.addHandToDiscardPile(deck);
        }
        assert(deck.getDeck().size() == 3);
        assert(deck.getDiscardPile().size() == 81);
        r.setHand(deck);
        assert(deck.getDeck().size() == 81-6);
    }

    @Test
    public void TestThatRobot2CannotDrawTheCardThatRobot1Has(){
        Board board = BoardFactory.createSimpleBoard();
        Robot robot1 = new Robot("e",1);
        Robot robot2 = new Robot("r",2);
        Roborally roborally = new Roborally(board, robot1);
        roborally.addRobot(robot2);
        Deck deck = roborally.getDeck();
        for(int i = 0;i<4;i++){
            robot1.setHand(deck);
            robot2.setHand(deck);
            robot1.addHandToDiscardPile(deck);
            robot2.addHandToDiscardPile(deck);
        }
        robot1.setHand(deck);
        List<Card> cardsR1 = robot1.getHand();
        assert(deck.getDeck().size() == 3);
        robot2.setHand(deck);
        List<Card> cardsR2 = robot2.getHand();
        assert(deck.getDeck().size() == (72-6));//discard pile minus the number of cards that r2 draws
        assert(cardsAreNotObtainedInList(cardsR1,deck.getDeck()));
        assert(cardsAreNotObtainedInList(cardsR1,cardsR2));
    }

    private boolean cardsAreNotObtainedInList(List<Card> cards, List<Card> cardList){
        for(Card card:cards){
            for(Card deckCard:cardList){
                if(card.equals(deckCard)){
                    return false;
                }
            }
        }
        return true;
    }
}