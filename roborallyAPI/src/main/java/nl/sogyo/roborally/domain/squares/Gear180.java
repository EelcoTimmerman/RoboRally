package nl.sogyo.roborally.domain.squares;

import java.util.List;

import nl.sogyo.roborally.domain.robots.Robot;

public class Gear180 extends Square {

    public Gear180(){
    }

    public Gear180(String walls){
        super(walls);
    }
    
    @Override
    public String getType(){
        return "Gear180";
    }

    @Override
    public void doSquareAction(Robot robot, Board board, List<Robot> robots){
        robot.turnReverse();
    }
}