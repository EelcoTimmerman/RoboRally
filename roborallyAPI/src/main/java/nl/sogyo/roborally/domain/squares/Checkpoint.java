package nl.sogyo.roborally.domain.squares;

import nl.sogyo.roborally.domain.robots.Robot;

public class Checkpoint extends Square{

    @Override
    public String getType(){
        return "Checkpoint";
    }

    @Override
    public void doSquareAction(Robot robot, Board board){
        robot.setRespawnPoint(robot.getXCoordinate(), robot.getYCoordinate());
    }
}