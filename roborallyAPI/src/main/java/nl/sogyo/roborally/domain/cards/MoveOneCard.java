package nl.sogyo.roborally.domain.cards;

import java.util.List;

import nl.sogyo.roborally.domain.robots.Robot;
import nl.sogyo.roborally.domain.squares.Board;

public class MoveOneCard extends Card{

    public MoveOneCard() {
        super();
	}

    public MoveOneCard(int speed){
        super(speed);
    }

	public void doCardAction(Robot robot, Board board, List<Robot> robots){
        moveRobotInDirectionIfPossible(robot, robot.getOrientation(), board, robots);
        respawnIfNecessary(robot, board);
    }

    // public void doCardAction(Robot robot, Board board){
    //     if(canMoveForward(robot, board)) robot.moveForward();    
    //     if(robotNotOnBoard(robot, board) || robotInPit(robot, board)) robot.respawn();
    // }


    public String getName(){
        return "MoveOneCard";
    }
}