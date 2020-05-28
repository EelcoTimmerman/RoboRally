package nl.sogyo.roborally.domain.squares;

import java.util.List;

import nl.sogyo.roborally.domain.robots.Robot;

public class Checkpoint extends Square{
    
    public Checkpoint(){
    }

   public Checkpoint(String walls){
    super(walls);
 }

    @Override
    public String getType(){
        return "Checkpoint";
    }

    @Override
    public void doSquareAction(Robot robot, Board board, List<Robot> robots){
        robot.setRespawnPoint(robot.getXCoordinate(), robot.getYCoordinate());
    }
}