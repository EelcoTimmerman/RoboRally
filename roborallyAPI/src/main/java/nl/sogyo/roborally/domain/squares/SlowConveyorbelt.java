package nl.sogyo.roborally.domain.squares;

import nl.sogyo.roborally.domain.Direction;

public class SlowConveyorbelt extends Square{
    private Direction movementDirection;

    public SlowConveyorbelt(Direction direction){
        this.movementDirection = direction;
    }

    public Direction getMovementDirection(){
        return this.movementDirection;
    }

}