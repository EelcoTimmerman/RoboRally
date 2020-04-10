package nl.sogyo.roborally.domain.robots;

import nl.sogyo.roborally.domain.Direction;
import nl.sogyo.roborally.domain.cards.ICard;
import nl.sogyo.roborally.domain.squares.Square;

public class Robot {

    Direction direction;
    ICard card;
    Square square;
    Square startSquare;
    
    public Robot() {
    }

    public Robot(Square square) {
        this.square = square;
        this.direction = Direction.NORTH;
    }

    public ICard getNextCard() {
        return card;
    }

    public Square getPosition() {
        return square;
    }

    public boolean isAt(Square square) {
        if (this.square.equals(square))
            return true;
        else
            return false;
    }
}