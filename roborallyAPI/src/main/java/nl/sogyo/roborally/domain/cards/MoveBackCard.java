package nl.sogyo.roborally.domain.cards;

import nl.sogyo.roborally.domain.Direction;
import nl.sogyo.roborally.domain.robots.Robot;
import nl.sogyo.roborally.domain.squares.Board;

public class MoveBackCard implements ICard{

    public void doMove(Robot robot, Board board){
        Direction movementDirection = robot.getOrientation().getReverse();
        int robotXCoordinate = robot.getXCoordinate();
        int robotYCoordinate = robot.getYCoordinate();
        boolean canMove = !board.getSquare(robotXCoordinate, robotYCoordinate).hasWallAt(movementDirection);
        if(canMove){
            robot.moveBackwards();
        }
        boolean robotNotOnBoard = robot.getXCoordinate() < 0 || robot.getYCoordinate() < 0 || robot.getXCoordinate() >= board.getWidth() || robot.getYCoordinate() >= board.getHeight();
        if(robotNotOnBoard){
            robot.respawn();
        }   
    }

}