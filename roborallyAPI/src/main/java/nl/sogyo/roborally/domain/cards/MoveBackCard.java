package nl.sogyo.roborally.domain.cards;

import java.util.List;

import nl.sogyo.roborally.domain.robots.Robot;
import nl.sogyo.roborally.domain.squares.Board;
import nl.sogyo.roborally.domain.squares.Pit;
import nl.sogyo.roborally.domain.squares.Square;

public class MoveBackCard implements ICard{

    public void doCardAction(Robot robot, Board board, List<Robot> robots){
        if(canMoveBackwards(robot, board)) robot.moveBackwards();    
        if(robotNotOnBoard(robot, board) || robotInPit(robot, board)) robot.respawn();
    }

    private boolean robotInPit(Robot robot, Board board){
        Square currentPosition = board.getSquare(robot.getXCoordinate(), robot.getYCoordinate());
        return (currentPosition instanceof Pit);
    }

    private boolean robotNotOnBoard(Robot robot, Board board){
        return robot.getXCoordinate() < 0 || robot.getYCoordinate() < 0 || robot.getXCoordinate() >= board.getWidth() || robot.getYCoordinate() >= board.getHeight();
    }

    private boolean canMoveBackwards(Robot robot, Board board){
        Square currentPosition = board.getSquare(robot.getXCoordinate(), robot.getYCoordinate());
        return !currentPosition.hasWallAt(robot.getOrientation().getReverse());
    }

    @Override
    public int getSpeed(){
        return 1;
    }
}