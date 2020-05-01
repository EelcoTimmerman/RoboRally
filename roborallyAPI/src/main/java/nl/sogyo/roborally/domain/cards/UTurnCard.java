package nl.sogyo.roborally.domain.cards;

import nl.sogyo.roborally.domain.robots.Robot;
import nl.sogyo.roborally.domain.squares.Board;

public class UTurnCard implements ICard{
    
    public void doCardAction(Robot robot, Board board){      
        robot.turnRight();
        robot.turnRight();
    }

    @Override
    public int getSpeed(){
        return 7;
    }
}