package nl.sogyo.roborally.domain.cards;

import java.util.List;

import nl.sogyo.roborally.domain.robots.Robot;
import nl.sogyo.roborally.domain.squares.Board;

public class MoveTwoCard extends Card{

    public void doCardAction(Robot robot, Board board, List<Robot> robots){
        boolean hasMoved = moveRobotInDirectionIfPossible(robot, robot.getOrientation(), board, robots);
        boolean hasRespawned = respawnIfNecessary(robot, board);
        if(hasMoved && !hasRespawned){
            moveRobotInDirectionIfPossible(robot, robot.getOrientation(), board, robots);
            respawnIfNecessary(robot, board);
        }
    }

    @Override
    public int getSpeed(){
        return 4;
    }
}