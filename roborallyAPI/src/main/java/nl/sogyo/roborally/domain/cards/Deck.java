package nl.sogyo.roborally.domain.cards;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Deck {
    final List<ICard> start_deck = new ArrayList<ICard>();
    List<ICard> discardPileForThisRound = new ArrayList<ICard>();
    List<ICard> cardsInDeck;

    public Deck(){
        ClassLoader classLoader = this.getClass().getClassLoader();
        File file = new File(classLoader.getResource("TextDeck.txt").getFile());
        try{
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine())
                addCardToDeck(sc.nextLine());
            sc.close();
        }catch(IOException e){
            System.out.println("File not found.");
        }
        this.cardsInDeck  = this.start_deck;
    }
    
    public List<ICard> getDeck(){
        return this.cardsInDeck;
    }

    public void createDeck(){this.cardsInDeck = this.start_deck;}

    private void addCardToDeck(String line){
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
        this.start_deck.add(card);
    }
       
    public List<ICard> getHand(int damage){
        List<ICard> hand = new ArrayList<ICard>();
        for(int i = 0; i < 9;i++){       
            if(i<9-damage) hand.add(getRandomCard());
            else hand.add(new DoNothingCard());
        }
        return hand;
    }

    private ICard getRandomCard(){
        if(this.cardsInDeck.isEmpty()) createNewDeckMinusDrawnCards();
        Random rand = new Random();
        int cardIndex = rand.nextInt(cardsInDeck.size());
        ICard randCard = this.cardsInDeck.get(cardIndex);
        this.cardsInDeck.remove(randCard);
        this.discardPileForThisRound.add(randCard);
        return randCard;
    }

    private void createNewDeckMinusDrawnCards(){
        this.createDeck();
        for(ICard card1: this.discardPileForThisRound)
            removeCardFromDeck(card1);
    }

    private void removeCardFromDeck(ICard discardedCard){
        for(ICard deckCard: this.cardsInDeck)
            if(deckCard.equals(discardedCard))
                this.cardsInDeck.remove(deckCard);
    }

	public void resetDiscardPile(){
        this.discardPileForThisRound.clear();
    }
    
    public List<ICard> getDiscardPile(){
        return this.discardPileForThisRound;
    }
}