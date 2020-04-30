package nl.sogyo.roborally.domain.squares;

import nl.sogyo.roborally.domain.robots.Robot;

public class GearRight extends Square {
    @Override
    public String getType(){
        return "GearRight";
    }

    @Override
    public void doSquareAction(Robot robot, Board board){
        robot.turnRight();
    }
}