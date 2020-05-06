package nl.sogyo.roborally.domain.cards;

import java.util.*;

public class Deck {
    List<ICard> deck;

    public void createDeck(){
        deck = new ArrayList<ICard>();
        Scanner sc = new Scanner(getClass().getResourceAsStream("TextDeck.txt"));
        while(sc.hasNextLine()){
            addCardToDeck(sc.nextLine());
        };
    }

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
        deck.add(card);
    }
   
    private ICard getRandomCard(){
        Random rand = new Random();
        int cardIndex = rand.nextInt(deck.size());
        ICard randCard = this.deck.get(cardIndex);
        this.deck.remove(cardIndex);
        return randCard;
    }
}