package nl.sogyo.roborally.domain.robots;


import nl.sogyo.roborally.domain.Direction;
import nl.sogyo.roborally.domain.cards.ICard;

public class Robot{

    Direction orientation;
    ICard card;
    int health;
    int xCoordinate;
    int yCoordinate;
    int respawnX;
    int respawnY;
    
    public Robot(){
    }

    public Robot(int xCoordinate, int yCoordinate){
        this.xCoordinate = xCoordinate;
        this.respawnX = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.respawnY = yCoordinate;
        this.orientation = Direction.NORTH;
        this.health = 9;
    }

    public Robot(int xCoordinate, int yCoordinate, Direction orientation){
        this.xCoordinate = xCoordinate;
        this.respawnX = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.respawnY = yCoordinate;
        this.orientation = orientation;
        this.health = 9;

    }

    public String getName(){
        return "testname";
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

    public void moveBackwards(){
        switch(this.orientation){
            case NORTH: this.yCoordinate++;
                        break;
            case EAST: this.xCoordinate--;
                        break;
            case SOUTH: this.yCoordinate--;
                        break;
            case WEST: this.xCoordinate++;
                        break;

        }
    }

    public void program(ICard card){
        this.card = card;
    }

    public void turnRight(){
        this.orientation = this.orientation.getRight();
    }

    public void turnLeft(){
        this.orientation = this.orientation.getLeft();
    }

    public void respawn(){
        this.xCoordinate = this.respawnX;
        this.yCoordinate = this.respawnY;
    }
}