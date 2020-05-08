package nl.sogyo.roborally.domain.cards;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Deck {
    List<ICard> cardsInDeck;

    public Deck(){
        this.cardsInDeck = this.createDeck();
    }
    
    public List<ICard> getDeck(){
        return this.cardsInDeck;
    }

    private List<ICard> createDeck(){
        List<ICard> cardsInDeck = new ArrayList<ICard>();
        ClassLoader classLoader = this.getClass().getClassLoader();
        File file = new File(classLoader.getResource("TextDeck.txt").getFile());
        try{
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine())
                cardsInDeck = addCardToDeck(cardsInDeck, sc.nextLine());
            sc.close();
        }catch(IOException e){
            System.out.println("File not found.");
        }
        return cardsInDeck;
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
        Random rand = new Random();
        int cardIndex = rand.nextInt(cardsInDeck.size());
        ICard randCard = this.cardsInDeck.get(cardIndex);
        this.cardsInDeck.remove(randCard);
        return randCard;
    }

    public void resetDeck(){
        this.cardsInDeck = this.createDeck();
    }

    // private void createNewDeckMinusDrawnCards(){
    //     this.cardsInDeck  = this.start_deck; // hier zit de fout, cardsInDeck/start_deck blijft 0
    //     this.createDeck();
    //     for(ICard card1: this.discardPileForThisRound)
    //         removeCardFromDeck(card1);
    // }

    // private void removeCardFromDeck(ICard discardedCard){
    //     for(ICard deckCard: this.cardsInDeck)
    //         if(deckCard.equals(discardedCard))
    //             this.cardsInDeck.remove(deckCard);
    // }

	// public void resetDiscardPile(){
    //     this.discardPileForThisRound.clear();
    // }
    
    // public List<ICard> getDiscardPile(){
    //     return this.discardPileForThisRound;
    // }
}