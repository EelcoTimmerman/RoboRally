package nl.sogyo.roborally.domain.cards;

import nl.sogyo.roborally.domain.robots.Robot;
import nl.sogyo.roborally.domain.squares.Board;

public class DoNothingCard implements ICard {

    @Override
    public void doCardAction(Robot robot, Board board) {
    }

    @Override
    public int getSpeed(){
        return 0;
    }

}