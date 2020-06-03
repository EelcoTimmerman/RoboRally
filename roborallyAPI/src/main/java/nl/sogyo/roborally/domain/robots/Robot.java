package nl.sogyo.roborally.domain.robots;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

//import jdk.javadoc.internal.doclets.toolkit.resources.doclets;
import nl.sogyo.roborally.domain.Direction;
import nl.sogyo.roborally.domain.cards.DoNothingCard;
import nl.sogyo.roborally.domain.cards.Card;
import nl.sogyo.roborally.domain.cards.Deck;
import nl.sogyo.roborally.domain.cards.MoveBackCard;
import nl.sogyo.roborally.domain.cards.MoveOneCard;
import nl.sogyo.roborally.domain.cards.MoveThreeCard;
import nl.sogyo.roborally.domain.cards.MoveTwoCard;
import nl.sogyo.roborally.domain.cards.RotateLeftCard;
import nl.sogyo.roborally.domain.cards.RotateRightCard;
import nl.sogyo.roborally.domain.cards.UTurnCard;
import nl.sogyo.roborally.domain.squares.Board;
import nl.sogyo.roborally.domain.squares.Square;

public class Robot{
    private final String[] colours = {"green", "black", "purple", "blue", "red", "brown"};
    List<Card> hand = new ArrayList<Card>();

    Direction orientation = Direction.NORTH;
    Card[] programmedCards = {new DoNothingCard(),new DoNothingCard(),new DoNothingCard(),new DoNothingCard(),new DoNothingCard()};
    int health = 9;
    int xCoordinate;
    int yCoordinate;
    int respawnX;
    int respawnY;
    boolean ready;
    String name = "defaultname";
    String colour = "orange";
    ActivityLevel activitylevel = ActivityLevel.ACTIVE;
    boolean hasWonTheGame;
    
    public Robot(){
    }

    public Robot(String name, int colourNr){
        this.name = name;
        if(colourNr < 6) this.colour = colours[colourNr];
        else this.colour = "orange";
    }

    public Robot(int xCoordinate, int yCoordinate, String name, int colourNr){
        this(xCoordinate, yCoordinate);
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
        this(xCoordinate, yCoordinate);
        this.orientation = orientation;
    }

    public String getColour() {
        return colour;
    }

    public String getName(){
        return name;
    }

    public Card getCard(int CardNr){
        return this.programmedCards[CardNr];
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

    public ActivityLevel getActivitylevel() {
        return activitylevel;
    }

    public boolean takeDamageIfHit(int xCoordinate, int yCoordinate, int damage){
        if(this.xCoordinate == xCoordinate && this.yCoordinate == yCoordinate){
            takeDamage(damage);
            return true;
        }
        else{
            return false;
        }
    }

    public void takeDamage(int damage){
        this.health -= damage;
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

    public void programOneCard(Card card, int index){
        this.programmedCards[index] = card;
    }

    public void programOneCard(int cardnr, int index){
        switch(cardnr){
            case 0: programOneCard(new MoveOneCard(), index);
                    break;
            case 1: programOneCard(new RotateRightCard(), index);
                    break;
            case 2: programOneCard(new RotateLeftCard(), index);
                    break;
            case 3: programOneCard(new UTurnCard(), index);
                    break;
            case 4: programOneCard(new MoveTwoCard(), index);
                    break;
            case 5: programOneCard(new MoveThreeCard(), index);
                    break;
            case 6: programOneCard(new MoveBackCard(), index);
                    break;
            case 7: programOneCard(new DoNothingCard(), index);
                    break;
            default: throw new RuntimeException("Invalid cardnr");
        }
    }

    public void program(int[] cardnrs){
        if(this.activitylevel != ActivityLevel.INACTIVE){
            int cardPosition = 0;
            for(int cardNr:cardnrs){
                programOneCard(cardNr, cardPosition);
                cardPosition++;
            }
        }
        this.ready = true;
    }

    public void program(int cardnr){
        int[] cardnrs = {cardnr, 7, 7, 7, 7};
        program(cardnrs);
    }

    public void program(Card[] cards){
        if(this.activitylevel != ActivityLevel.INACTIVE){
            for(int cardPosition = 0;cardPosition<5;cardPosition++){
                programOneCard(cards[cardPosition], cardPosition);
            }
        }
         this.ready = true;
    }

    public void program(Card card){
        Card[] cards = {card, new DoNothingCard(), new DoNothingCard(), new DoNothingCard(), new DoNothingCard()};
        program(cards);
    }

    public void programFromHand(int cardnr){
        this.program(hand.get(cardnr));
    }

    public void programFromHand(int[] cardnrs){
        this.ready = true;
        if(cardnrs.length == 5){
            for(int i = 0; i < 5; i++){
                programOneCard(this.hand.get(cardnrs[i]), i);
            }
        }
        else{
            throw new RuntimeException("must program 5 cards");
        }
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

    public static Comparator<Robot> COMPARE_BY_CARD(int registernr){
        return new Comparator<Robot>() {
            @Override
            public int compare(Robot robot1, Robot robot2) {
                return robot1.getCard(registernr).getSpeed() - robot2.getCard(registernr).getSpeed();
            }
        };
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

    public void drawCards(Deck deck){
        this.hand = deck.createHand(9-getHealth());
    }
    
    public List<Card> getHand(){
        return this.hand;
    }

    public void clearHand(Deck d){
        clearHand();
    }

    public void clearHand(){
        this.hand.clear();

    }

    public boolean isInactive(){
        return this.activitylevel == ActivityLevel.INACTIVE;
    }

    public boolean isPoweringDown(){
        return this.activitylevel == ActivityLevel.POWERINGDOWN;
    }

    public void shutDown(){
        this.activitylevel = ActivityLevel.INACTIVE;
        for(int i=0;i<5;i++){
            this.programmedCards[i] = new DoNothingCard();
        }
        this.health = 9;
    }

    public void activate(){
        this.activitylevel = ActivityLevel.ACTIVE;
    }

    public void cyclePowerState(){
        if(this.activitylevel == ActivityLevel.POWERINGDOWN){
            this.shutDown();
        }
        else if(this.activitylevel == ActivityLevel.INACTIVE){
            this.activate();
        }
    }

    public void fireLaser(List<Robot> robots, Board board){
        switch(this.orientation){
            case NORTH: fireNorth(robots, board);
                        break;
            case EAST: fireEast(robots, board);
                        break;                        
            case SOUTH: fireSouth(robots, board);
                        break;            
            case WEST: fireWest(robots, board);
                        break;
        }
    }

    private void fireNorth(List<Robot> robots, Board board){
        int xCoordinate = this.xCoordinate;
        int yCoordinate = this.yCoordinate;
        Square currentSquare = board.getSquare(xCoordinate, yCoordinate);
        if(!currentSquare.hasWallAt(Direction.NORTH)){
            yCoordinate--;
            while(yCoordinate >= 0){
                boolean hitRobot = false;
                for(Robot robot : robots){
                    hitRobot |= robot.takeDamageIfHit(xCoordinate, yCoordinate, 1);
                }
                currentSquare = board.getSquare(xCoordinate, yCoordinate);
                if(hitRobot || currentSquare.hasWallAt(Direction.NORTH)){
                    break;
                }
                yCoordinate--;
            }
        }
    }

    private void fireEast(List<Robot> robots, Board board){
        int xCoordinate = this.xCoordinate;
        int yCoordinate = this.yCoordinate;
        Square currentSquare = board.getSquare(xCoordinate, yCoordinate);
        if(!currentSquare.hasWallAt(Direction.EAST)){
            xCoordinate++;
            while(xCoordinate < board.getWidth()){
                boolean hitRobot = false;
                for(Robot robot : robots){
                    hitRobot |= robot.takeDamageIfHit(xCoordinate, yCoordinate, 1);
                }
                currentSquare = board.getSquare(xCoordinate, yCoordinate);
                if(hitRobot || currentSquare.hasWallAt(Direction.EAST)){
                    break;
                }
                xCoordinate++;
            }
        }       
    }

    private void fireSouth(List<Robot> robots, Board board){
        int xCoordinate = this.xCoordinate;
        int yCoordinate = this.yCoordinate;
        Square currentSquare = board.getSquare(xCoordinate, yCoordinate);
        if(!currentSquare.hasWallAt(Direction.SOUTH)){
            yCoordinate++;
            while(yCoordinate < board.getHeight()){
                boolean hitRobot = false;
                for(Robot robot : robots){
                    hitRobot |= robot.takeDamageIfHit(xCoordinate, yCoordinate, 1);
                }
                currentSquare = board.getSquare(xCoordinate, yCoordinate);
                if(hitRobot || currentSquare.hasWallAt(Direction.SOUTH)){
                    break;
                }
                yCoordinate++;
            }
        }       
    }

    private void fireWest(List<Robot> robots, Board board){
        int xCoordinate = this.xCoordinate;
        int yCoordinate = this.yCoordinate;
        Square currentSquare = board.getSquare(xCoordinate, yCoordinate);
        if(!currentSquare.hasWallAt(Direction.WEST)){
            xCoordinate--;
            while(xCoordinate >= 0){
                boolean hitRobot = false;
                for(Robot robot : robots){
                    hitRobot |= robot.takeDamageIfHit(xCoordinate, yCoordinate, 1);
                }
                currentSquare = board.getSquare(xCoordinate, yCoordinate);
                if(hitRobot || currentSquare.hasWallAt(Direction.WEST)){
                    break;
                }
                xCoordinate--;
            }
        }  
    }

    public void turnOnOrOff(){
        if(this.activitylevel == ActivityLevel.ACTIVE) this.activitylevel = ActivityLevel.POWERINGDOWN;
        else if(this.activitylevel == ActivityLevel.POWERINGDOWN) this.activitylevel = ActivityLevel.ACTIVE;
    }

    public void setToWinner(){
        this.hasWonTheGame = true;
    }

    public boolean isWinner(){
        if(this.hasWonTheGame) return true;
        return false;
    }

	public void updateCurrentCard() {
	}

}