package nl.sogyo.roborally.domain.squares;

import nl.sogyo.roborally.domain.robots.Robot;

public class FinalCheckPoint extends Square{
    
    public FinalCheckPoint(){
    }

   public FinalCheckPoint(String walls){
    super(walls);
 }

    @Override
    public String getType(){
        return "FinalCheckpoint";
    }

    @Override
    public void doSquareAction(Robot robot, Board board){
        robot.setRespawnPoint(robot.getXCoordinate(), robot.getYCoordinate());
    }
}