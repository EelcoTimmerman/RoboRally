package nl.sogyo.roborally.domain.robots;

import nl.sogyo.roborally.domain.Direction;
import nl.sogyo.roborally.domain.cards.ICard;
import nl.sogyo.roborally.domain.squares.Square;

public class Robot {

    Direction orientation;
    ICard card;
    Square position;
    Square respawnSquare;
    int health;
    
    public Robot() {
    }

    public Robot(Square square) {
        this.position = square;
        this.orientation = Direction.NORTH;
        this.health = 9;
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

    public void takeDamage(int firepower) {
        this.health -= firepower;
    }

    public int getHealth() {
        return this.health;
    }
}