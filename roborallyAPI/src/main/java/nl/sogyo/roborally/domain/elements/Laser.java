package nl.sogyo.roborally.domain.elements;

import nl.sogyo.roborally.domain.Direction;
import nl.sogyo.roborally.domain.squares.Square;

public class Laser{

    Square position;
    Direction orientation;
    int firepower;

    public Laser(){
    }

    public Square getPosition(){
        return position;
    }

    public Direction getOrientation(){
        return orientation;
    }

    public int getFirepower(){
        return firepower;
    }
}