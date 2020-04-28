package nl.sogyo.roborally.domain.cards;

import nl.sogyo.roborally.domain.Direction;
import nl.sogyo.roborally.domain.robots.Robot;
import nl.sogyo.roborally.domain.squares.Board;
import nl.sogyo.roborally.domain.squares.Square;

public class MoveOneCard implements ICard{

    public void doCardAction(Robot robot, Board board){
        if(canMoveForward(robot, board)) robot.moveForward();    
        if(robotNotOnBoard(robot, board)) robot.respawn();
    }

    private boolean robotNotOnBoard(Robot robot, Board board){
        return robot.getXCoordinate() < 0 || robot.getYCoordinate() < 0 || robot.getXCoordinate() >= board.getWidth() || robot.getYCoordinate() >= board.getHeight();
    }

    private boolean canMoveForward(Robot robot, Board board){
        Square currentPosition = board.getSquare(robot.getXCoordinate(), robot.getYCoordinate());
        return !currentPosition.hasWallAt(robot.getOrientation());
    }
}