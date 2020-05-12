package nl.sogyo.roborally.domain.elements;

import nl.sogyo.roborally.domain.Direction;

public class Laser{

    int xCoordinate;
    int yCoordinate;
    Direction orientation;
    int firepower;

    public Laser(){
    }

    public Laser(int xCoordinate, int yCoordinate, Direction orientation, int firepower){
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.orientation = orientation;
        this.firepower = firepower;
    }

    public Direction getOrientation(){
        return orientation;
    }

    public int getFirepower(){
        return firepower;
    }
    
    public int getxCoordinate() {
        return xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }
}