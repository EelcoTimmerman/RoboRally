package nl.sogyo.roborally.domain.squares;

import nl.sogyo.roborally.domain.robots.Robot;

public class Pit extends Square {
    @Override
    public String getType() {
        return "Pit";
    }

    @Override
    public void doSquareAction(Robot robot, Board board){
    }
}