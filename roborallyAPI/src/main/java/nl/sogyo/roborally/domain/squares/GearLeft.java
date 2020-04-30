package nl.sogyo.roborally.domain.squares;

import nl.sogyo.roborally.domain.robots.Robot;

public class GearLeft extends Square {
    @Override
    public String getType(){
        return "GearLeft";
    }

    @Override
    public void doSquareAction(Robot robot, Board board){
        robot.turnLeft();
    }
}