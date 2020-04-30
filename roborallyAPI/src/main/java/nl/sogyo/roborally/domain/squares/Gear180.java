package nl.sogyo.roborally.domain.squares;

import nl.sogyo.roborally.domain.robots.Robot;

public class Gear180 extends Square {

    @Override
    public String getType(){
        return "Gear180";
    }

    @Override
    public void doSquareAction(Robot robot, Board board){
        robot.turnReverse();
    }
}