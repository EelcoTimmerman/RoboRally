package nl.sogyo.roborally.domain.squares;

import java.util.List;

import nl.sogyo.roborally.domain.robots.Robot;

public class FinalCheckPoint extends Square{
    
    public FinalCheckPoint(){
    }

   public FinalCheckPoint(String walls){
    super(walls);
 }

    @Override
    public String getType(){
        return "FinalCheckPoint";
    }

    @Override
    public void doSquareAction(Robot robot, Board board, List<Robot> robots){
        robot.setRespawnPoint(robot.getXCoordinate(), robot.getYCoordinate());
    }
}