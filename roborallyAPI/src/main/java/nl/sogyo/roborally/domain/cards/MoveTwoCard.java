package nl.sogyo.roborally.domain.cards;

import nl.sogyo.roborally.domain.Direction;
import nl.sogyo.roborally.domain.robots.Robot;
import nl.sogyo.roborally.domain.squares.Board;
import nl.sogyo.roborally.domain.squares.Square;

public class MoveTwoCard implements ICard{

    public void doMove(Robot robot, Board board){
        boolean canMove = takeAStepIfPossible(robot, board);
        if(canMove)
            takeAStepIfPossible(robot, board);
    }

    private boolean takeAStepIfPossible(Robot robot, Board board){
        int robotXCoordinate = robot.getXCoordinate();
        int robotYCoordinate = robot.getYCoordinate();

        Square currentposition = board.getSquare(robotXCoordinate, robotYCoordinate);
        if(!currentposition.hasWallAt(robot.getOrientation()))
            robot.moveForward();        
        
        boolean robotNotOnBoard = robot.getXCoordinate() < 0 || robot.getYCoordinate() < 0 || robot.getXCoordinate() >= board.getWidth() || robot.getYCoordinate() >= board.getHeight();
        if(robotNotOnBoard){
            robot.respawn();
            return false;
        }
        return true;
    }
}