package nl.sogyo.roborally.domain.cards;

import java.util.List;

import nl.sogyo.roborally.domain.Direction;
import nl.sogyo.roborally.domain.robots.Robot;
import nl.sogyo.roborally.domain.squares.Board;
import nl.sogyo.roborally.domain.squares.Pit;
import nl.sogyo.roborally.domain.squares.Square;

public class MoveOneCard implements ICard{

    public void doCardAction(Robot robot, Board board, List<Robot> robots){
        moveRobotInDirectionIfPossible(robot, robot.getOrientation(), board, robots);
        respawnIfNecessary(robot, board);
    }

    private boolean robotInPit(Robot robot, Board board){
        Square currentPosition = board.getSquare(robot.getXCoordinate(), robot.getYCoordinate());
        return (currentPosition instanceof Pit);
    }

    private boolean robotNotOnBoard(Robot robot, Board board){
        return robot.getXCoordinate() < 0 || robot.getYCoordinate() < 0 || robot.getXCoordinate() >= board.getWidth() || robot.getYCoordinate() >= board.getHeight();
    }

    private boolean moveRobotInDirectionIfPossible(Robot robot, Direction direction, Board board, List<Robot> otherRobots){
        boolean hasMoved = true;
        boolean isBlockedByWall = checkForWall(robot, direction, board);
        if(!isBlockedByWall){
            robot.move(direction);
            for(Robot otherRobot : otherRobots){
                if(otherRobot.isAt(robot.getXCoordinate(), robot.getYCoordinate()) && otherRobot != robot){
                    hasMoved &= moveRobotInDirectionIfPossible(otherRobot, direction, board, otherRobots);
                    if(hasMoved){
                        respawnIfNecessary(otherRobot, board);
                    }
                    else{
                        robot.move(direction.getReverse());
                    }
                    break;
                }
            }
        }
        else{
            hasMoved = false;
        }
        return hasMoved;
    }

    private boolean checkForWall(Robot robot, Direction direction, Board board){
        Square currentPosition = board.getSquare(robot.getXCoordinate(), robot.getYCoordinate());
        return currentPosition.hasWallAt(direction);
    }

    private void respawnIfNecessary(Robot robot, Board board){        
        if(robotNotOnBoard(robot, board) || robotInPit(robot, board)) robot.respawn();
    }

    @Override
    public int getSpeed(){
        return 2;
    }
}