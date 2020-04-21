package nl.sogyo.roborally.domain.robots;

import nl.sogyo.roborally.domain.Direction;
import nl.sogyo.roborally.domain.cards.ICard;
import nl.sogyo.roborally.domain.squares.Square;

public class Robot{

    Direction orientation;
    ICard card;
    Square respawnSquare;
    int health;
    int xCoordinate;
    int yCoordinate;
    
    public Robot(){
    }

    public Robot(int xCoordinate, int yCoordinate){
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.orientation = Direction.NORTH;
        this.health = 9;
    }

    public ICard getNextCard(){
        return card;
    }

    public void setNextCard(ICard card){
        this.card = card;
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

    public void takeDamage(int firepower){
        this.health -= firepower;
    }

    public Direction getOrientation(){
        return this.orientation;
    }

    public void setOrientation(Direction newOrientation){
        this.orientation = newOrientation;
    }

    public boolean isAt(int xCoordinate, int yCoordinate){
        if(this.xCoordinate == xCoordinate && this.yCoordinate == yCoordinate)
            return true;
        else
            return false;
    }

    public void moveForward(){
        switch(this.orientation){
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
}