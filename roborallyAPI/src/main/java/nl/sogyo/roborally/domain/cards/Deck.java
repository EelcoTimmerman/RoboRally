package nl.sogyo.roborally.domain.cards;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Deck {
    List<Card> cardsInDeck = new ArrayList<Card>();
    List<Card> discardPile= new ArrayList<Card>();
     

    public Deck(){
        ClassLoader classLoader = this.getClass().getClassLoader();
        File file = new File(classLoader.getResource("TextDeck.txt").getFile());
        try{
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine())
                addCardToDeck(cardsInDeck, sc.nextLine());
            sc.close();
        }catch(IOException e){
            System.out.println("File not found.");
        }
    }
    
    public List<Card> getDeck(){
        return this.cardsInDeck;
    }

    public List<Card> getDiscardPile(){
        return this.discardPile;
    }

    private List<Card> addCardToDeck(List<Card> cards, String line){
        int speed = Integer.parseInt(line.split(" ")[0]);
        String type = line.split(" ", 2)[1];
        Card card;
        switch(type){
            case "U Turn": card = new UTurnCard(speed); break;
            case "Rotate Left": card = new RotateLeftCard(speed); break;
            case "Rotate Right": card = new RotateRightCard(speed); break;
            case "Back Up": card = new MoveBackCard(speed); break;
            case "Move 1": card = new MoveOneCard(speed); break;
            case "Move 2": card = new MoveTwoCard(speed); break;
            case "Move 3": card = new MoveThreeCard(speed); break;
            default: card = null;
        }
        cards.add(card);
        return cards;
    }
       
    public List<Card> createHand(int damage){
        List<Card> hand = new ArrayList<Card>();
        for(int i = 0; i < (9-damage);i++){       
            hand.add(getRandomCard());
        }
        return hand;
    }

    private Card getRandomCard(){
        if(this.cardsInDeck.isEmpty()) replenishDeckWithDiscardPile();
        Random rand = new Random();
        int cardIndex = rand.nextInt(cardsInDeck.size());
        Card randCard = this.cardsInDeck.get(cardIndex);
        this.cardsInDeck.remove(randCard);
        return randCard;
    }

    private void replenishDeckWithDiscardPile(){
        if(this.discardPile.isEmpty()){
            throw new RuntimeException("Trying to obtain cards from an empty deck..");
        }
        for(Card card1: this.discardPile){
            this.cardsInDeck.add(card1);
        }
        this.discardPile.clear();
    }
    
}