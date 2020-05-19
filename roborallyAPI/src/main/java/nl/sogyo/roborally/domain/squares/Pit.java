package nl.sogyo.roborally.domain.squares;

import java.util.List;

import nl.sogyo.roborally.domain.robots.Robot;

public class Pit extends Square{

    public Pit(){
    }

    public Pit(String walls){
        super(walls);
    }
    
    @Override
    public String getType() {
        return "Pit";
    }

    @Override
    public void doSquareAction(Robot robot, Board board, List<Robot> robots){
    }
}