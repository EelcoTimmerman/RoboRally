package nl.sogyo.roborally.domain.robots;

import java.util.Comparator;
import java.util.List;

import nl.sogyo.roborally.domain.Direction;
import nl.sogyo.roborally.domain.cards.DoNothingCard;
import nl.sogyo.roborally.domain.cards.Card;
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

    Direction orientation = Direction.NORTH;
    Card[] cards = {new DoNothingCard(),new DoNothingCard(),new DoNothingCard(),new DoNothingCard(),new DoNothingCard()};
    int health = 9;
    int xCoordinate;
    int yCoordinate;
    int respawnX;
    int respawnY;
    boolean ready = false;
    String name = "defaultname";
    String colour = "orange";
    ActivityLevel activitylevel = ActivityLevel.ACTIVE;
    
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
        this.health = 9;
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
        return this.cards[CardNr];
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

    public void programOneCard(Card card, int index){
        if(this.activitylevel != ActivityLevel.INACTIVE){
            this.cards[index] = card;
            this.ready = true;
        }
    }

    public void program(int[] cardnrs){
       int index = 0;
        for(int cardNr:cardnrs){
            programOneCard(cardNr, index);
            index++;
        }
        this.ready = true;

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

    public boolean isInactive(){
        return this.activitylevel == ActivityLevel.INACTIVE;
    }

    public boolean isPoweringDown(){
        return this.activitylevel == ActivityLevel.POWERINGDOWN;
    }

    public void shutDown(){
        this.activitylevel = ActivityLevel.INACTIVE;
        this.card = new DoNothingCard();
        this.health = 9;
    }

    public void activate(){
        this.activitylevel = ActivityLevel.ACTIVE;
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
        while(yCoordinate > 0){
            Square currentSquare = board.getSquare(xCoordinate, yCoordinate);
            if(currentSquare.hasWallAt(Direction.NORTH)){
                break;
            }
            yCoordinate--;
            for(Robot robot : robots){
                if(robot.isAt(xCoordinate, yCoordinate)){
                    robot.takeDamage(1);
                    yCoordinate = -1;
                }
            }
        }
    }

    private void fireEast(List<Robot> robots, Board board){
        int xCoordinate = this.xCoordinate;
        int yCoordinate = this.yCoordinate;
        while(xCoordinate < board.getWidth() - 1){
            Square currentSquare = board.getSquare(xCoordinate, yCoordinate);
            if(currentSquare.hasWallAt(Direction.EAST)){
                break;
            }
            xCoordinate++;
            for(Robot robot : robots){
                if(robot.isAt(xCoordinate, yCoordinate)){
                    robot.takeDamage(1);
                    xCoordinate = board.getWidth();
                }
            }
        }        
    }

    private void fireSouth(List<Robot> robots, Board board){
        int xCoordinate = this.xCoordinate;
        int yCoordinate = this.yCoordinate;
        while(yCoordinate < board.getHeight() - 1){
            Square currentSquare = board.getSquare(xCoordinate, yCoordinate);
            if(currentSquare.hasWallAt(Direction.SOUTH)){
                break;
            }
            yCoordinate++;
            for(Robot robot : robots){
                if(robot.isAt(xCoordinate, yCoordinate)){
                    robot.takeDamage(1);
                    yCoordinate = board.getHeight();
                }
            }
        }        
    }

    private void fireWest(List<Robot> robots, Board board){
        int xCoordinate = this.xCoordinate;
        int yCoordinate = this.yCoordinate;
        while(xCoordinate > 0){
            Square currentSquare = board.getSquare(xCoordinate, yCoordinate);
            if(currentSquare.hasWallAt(Direction.WEST)){
                break;
            }
            xCoordinate--;
            for(Robot robot : robots){
                if(robot.isAt(xCoordinate, yCoordinate)){
                    robot.takeDamage(1);
                    xCoordinate = -1;
                }
            }
        }
    }

    public void turnOnOrOff(){
        if(this.activitylevel == ActivityLevel.ACTIVE) this.activitylevel = ActivityLevel.POWERINGDOWN;
        else if(this.activitylevel == ActivityLevel.POWERINGDOWN) this.activitylevel = ActivityLevel.ACTIVE;
    }
}