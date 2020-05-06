package nl.sogyo.roborally.domain.cards;

import java.util.List;

import nl.sogyo.roborally.domain.robots.Robot;
import nl.sogyo.roborally.domain.squares.Board;

public class DoNothingCard extends Card {

    @Override
    public void doCardAction(Robot robot, Board board, List<Robot> robots) {
    }

    @Override
    public int getSpeed(){
        return 0;
    }

}