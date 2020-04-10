package nl.sogyo.roborally.domain.robots;

import nl.sogyo.roborally.domain.Direction;
import nl.sogyo.roborally.domain.cards.ICard;
import nl.sogyo.roborally.domain.squares.Square;

public class Robot {

    Direction orientation;
    ICard card;
    Square position;
    Square respawnSquare;
    
    public Robot() {
    }

    public Robot(Square square) {
        this.position = square;
        this.orientation = Direction.NORTH;
    }

    public ICard getNextCard() {
        return card;
    }

    public Square getPosition() {
        return position;
    }

    public boolean isAt(Square square) {
        if (this.position.equals(square))
            return true;
        else
            return false;
    }
}