package nl.sogyo.roborally.domain.cards;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Deck {
   // private final List<ICard> initDeck;
    List<ICard> cardsInDeck = new ArrayList<ICard>();
    List<ICard> discardPile= new ArrayList<ICard>();
     

    public Deck(){
//        this.initDeck = new ArrayList<ICard>();
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
    
    public List<ICard> getDeck(){
        return this.cardsInDeck;
    }

    public List<ICard> getDiscardPile(){
        return this.discardPile;
    }

    private List<ICard> addCardToDeck(List<ICard> cards, String line){
        int speed = Integer.parseInt(line.split(" ")[0]);
        String type = line.split(" ", 2)[1];
        ICard card;
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
       
    public List<ICard> getHand(int damage){
        List<ICard> hand = new ArrayList<ICard>();
        for(int i = 0; i < (9-damage);i++){       
            hand.add(getRandomCard());
        }
        return hand;
    }

    private ICard getRandomCard(){
        if(this.cardsInDeck.isEmpty()) replenishDeckWithDiscardPile();
        Random rand = new Random();
        int cardIndex = rand.nextInt(cardsInDeck.size());
        ICard randCard = this.cardsInDeck.get(cardIndex);
        this.cardsInDeck.remove(randCard);
        return randCard;
    }

    // public List<ICard> resetDeck(){
    //     this.cardsInDeck.clear();
    //     for(ICard card:this.initDeck){
    //         this.cardsInDeck.add(card);
    //     }
    //     return this.cardsInDeck;
    // }

    private void replenishDeckWithDiscardPile(){
        if(this.discardPile.isEmpty()){
            throw new RuntimeException("Trying to obtain cards from an empty deck..");
        }
        for(ICard card1: this.discardPile){
            this.cardsInDeck.add(card1);
        }
        this.discardPile.clear();
        

    }

    // private void removeCardFromDeck(ICard discardedCard){
    //     for(ICard deckCard: this.cardsInDeck)
    //         if(deckCard.equals(discardedCard))
    //             this.cardsInDeck.remove(deckCard);
    // }

	public void resetDiscardPile(){
        this.discardPile.clear();
    }
    
}