package nl.sogyo.roborally.domain.squares;

import nl.sogyo.roborally.domain.Direction;

public class SlowConveyorbelt extends Square{
    private Direction movementDirection;

    public SlowConveyorbelt(Direction direction){
        this.movementDirection = direction;

    }

    public Square getDestination() {
        return this.getNeighbour(movementDirection);
    }

}