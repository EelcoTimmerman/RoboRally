package nl.sogyo.roborally.domain.cards;

import java.util.List;

import nl.sogyo.roborally.domain.robots.Robot;
import nl.sogyo.roborally.domain.squares.Board;

public class MoveOneCard extends Card{

    public void doCardAction(Robot robot, Board board, List<Robot> robots){
        moveRobotInDirectionIfPossible(robot, robot.getOrientation(), board, robots);
        respawnIfNecessary(robot, board);
        checkIfWinner(robot, board);

    }

    @Override
    public int getSpeed(){
        return 2;
    }
}