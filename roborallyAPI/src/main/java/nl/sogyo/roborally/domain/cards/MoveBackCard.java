package nl.sogyo.roborally.domain.cards;

import java.util.List;

import nl.sogyo.roborally.domain.robots.Robot;
import nl.sogyo.roborally.domain.squares.Board;

public class MoveBackCard extends Card{

    public MoveBackCard(){
        super();
    }

    public MoveBackCard(int speed){
        super(speed);
    }

    public void doCardAction(Robot robot, Board board, List<Robot> robots){
        moveRobotInDirectionIfPossible(robot, robot.getOrientation().getReverse(), board, robots);
        respawnIfNecessary(robot, board);
    }

    public String getName(){
        return "MoveBackCard";
    }
}