package nl.sogyo.roborally.domain.robots;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

//import jdk.javadoc.internal.doclets.toolkit.resources.doclets;
import nl.sogyo.roborally.domain.Direction;
import nl.sogyo.roborally.domain.cards.*;

public class Robot{
    private final String[] colours = {"green", "black", "purple", "blue", "red", "brown"};
    List<ICard> hand = new ArrayList<ICard>();

    Direction orientation = Direction.NORTH;;
    ICard card = new DoNothingCard();
    int health = 9;
    int xCoordinate;
    int yCoordinate;
    int respawnX;
    int respawnY;
    boolean ready = false;
    String name;
    String colour;
    
    public Robot(){
    }

    public Robot(String name, int colourNr){
        this.name = name;
        if(colourNr < 6) this.colour = colours[colourNr];
        else this.colour = "orange";
    }

    public Robot(int xCoordinate, int yCoordinate){
        this.xCoordinate = xCoordinate;
        this.respawnX = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.respawnY = yCoordinate;
    }

    public Robot(int xCoordinate, int yCoordinate, Direction orientation){
        this.xCoordinate = xCoordinate;
        this.respawnX = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.respawnY = yCoordinate;
        this.orientation = orientation;
    }

    public String getColour() {
        return colour;
    }

    public String getName(){
        return name;
    }

    public ICard getCard(){
        return this.card;
    }

    public int getXCoordinate(){
        return this.xCoordinate;
    }

    public int getYCoordinate(){
        return this.yCoordinate;
    }

    public int getHealth(){
        return this.health;
    }

    public Direction getOrientation(){
        return this.orientation;
    }

    public void setOrientation(Direction newOrientation){
        this.orientation = newOrientation;
    }

    public void takeDamage(int firepower){
        this.health -= firepower;
    }

    public boolean isAt(int xCoordinate, int yCoordinate){
        if(this.xCoordinate == xCoordinate && this.yCoordinate == yCoordinate)
            return true;
        else
            return false;
    }

    public void setRespawnPoint(int xCoordinate, int yCoordinate){
        this.respawnX = xCoordinate;
        this.respawnY = yCoordinate;
    }

    public void moveForward(){
        move(this.orientation);
    }

    public void moveBackwards(){
        move(this.orientation.getReverse());
    }

    public void move(Direction direction){
        switch(direction){
            case NORTH: this.yCoordinate--;
                        break;
            case EAST: this.xCoordinate++;
                        break;
            case SOUTH: this.yCoordinate++;
                        break;
            case WEST: this.xCoordinate--;
                        break;
        }
    }

    public void program(ICard card){
        this.card = card;
        this.ready = true;
    }

    public void program(int cardnr){
        switch(cardnr){
            case 0: this.card = new MoveOneCard();
                    break;
            case 1: this.card = new RotateRightCard();
                    break;
            case 2: this.card = new RotateLeftCard();
                    break;
            case 3: this.card = new UTurnCard();
                    break;
            case 4: this.card = new MoveTwoCard();
                    break;
            case 5: this.card = new MoveThreeCard();
                    break;
            case 6: this.card = new MoveBackCard();
                    break;
            case 7: this.card = new DoNothingCard();
                    break;
            default: throw new RuntimeException("Invalid cardnr");
        }
        this.ready = true;
    }

    public void turnRight(){
        this.orientation = this.orientation.getRight();
    }

    public void turnLeft(){
        this.orientation = this.orientation.getLeft();
    }

    public void turnReverse(){
        this.orientation = this.orientation.getReverse();
    }

    public void respawn(){
        this.xCoordinate = this.respawnX;
        this.yCoordinate = this.respawnY;
    }

    public void setXCoordinate(int xCoordinate){
        this.xCoordinate = xCoordinate;
    }

    public void setYCoordinate(int yCoordinate){
        this.yCoordinate = yCoordinate;
    }

    public int getRespawnXCoordinate(){
        return this.respawnX;
    }

    public int getRespawnYCoordinate(){
        return this.respawnY;
    }

    public static Comparator<Robot> COMPARE_BY_CARD = new Comparator<Robot>(){
        @Override
        public int compare(Robot robot1, Robot robot2) {
            return robot1.getCard().getSpeed() - robot2.getCard().getSpeed();
        }
    };
    
    public static Comparator<Robot> COMPARE_BY_NAME = new Comparator<Robot>(){
        @Override
        public int compare(Robot robot1, Robot robot2) {
            return robot1.getName().compareTo(robot2.getName());
        }
    };

    public void unready(){
        this.ready = false;
    }

    public boolean isReady(){
        return this.ready;
    }

    public List<ICard> getHand(Deck deck){
        this.hand = deck.getHand(9-getHealth());
        return this.hand;
    }
    
    public List<ICard> showHand(Deck deck){
        return this.hand;
    }

    public void addHandToDiscardPile(Deck deck){
        for(ICard card:this.hand){
            deck.getDiscardPile().add(card);
        }
        this.hand.clear();
    }

}