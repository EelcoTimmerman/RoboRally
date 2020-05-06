package nl.sogyo.roborally.domain.cards;

import java.util.List;

import nl.sogyo.roborally.domain.robots.Robot;
import nl.sogyo.roborally.domain.squares.Board;
import nl.sogyo.roborally.domain.squares.Pit;
import nl.sogyo.roborally.domain.squares.Square;

public class MoveThreeCard implements ICard{

    public void doCardAction(Robot robot, Board board, List<Robot> robots){      
        if(canMoveForward(robot, board)) robot.moveForward();    
        if(robotNotOnBoard(robot, board) || robotInPit(robot, board)) robot.respawn();
        else{
            if(canMoveForward(robot, board)) robot.moveForward();    
            if(robotNotOnBoard(robot, board) || robotInPit(robot, board)) robot.respawn();
            else{
                if(canMoveForward(robot, board)) robot.moveForward();    
                if(robotNotOnBoard(robot, board) || robotInPit(robot, board)) robot.respawn();
            }
        }
    }

    private boolean robotInPit(Robot robot, Board board){
        Square currentPosition = board.getSquare(robot.getXCoordinate(), robot.getYCoordinate());
        return (currentPosition instanceof Pit);
    }
    
    private boolean robotNotOnBoard(Robot robot, Board board){
        return robot.getXCoordinate() < 0 || robot.getYCoordinate() < 0 || robot.getXCoordinate() >= board.getWidth() || robot.getYCoordinate() >= board.getHeight();
    }

    private boolean canMoveForward(Robot robot, Board board){
        Square currentPosition = board.getSquare(robot.getXCoordinate(), robot.getYCoordinate());
        return !currentPosition.hasWallAt(robot.getOrientation());
    }

    @Override
    public int getSpeed(){
        return 3;
    }
}