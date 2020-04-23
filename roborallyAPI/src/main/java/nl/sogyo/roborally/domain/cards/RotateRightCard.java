package nl.sogyo.roborally.domain.cards;

import nl.sogyo.roborally.domain.Direction;
import nl.sogyo.roborally.domain.robots.Robot;
import nl.sogyo.roborally.domain.squares.Board;

public class RotateRightCard implements ICard{

    public void doCardAction(Robot robot, Board board){
        robot.turnRight();
    }
}