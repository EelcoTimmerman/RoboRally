package nl.sogyo.roborally.domain.cards;

import java.util.List;

import nl.sogyo.roborally.domain.Direction;
import nl.sogyo.roborally.domain.Roborally;
import nl.sogyo.roborally.domain.robots.Robot;
import nl.sogyo.roborally.domain.squares.*;

public abstract class Card{

    public abstract void doCardAction(Robot robot, Board board, List<Robot> robots);
    public abstract String getName();
    protected int speed;

    public Card() {
	}

    public Card(int speed){
        this.speed = speed;
    }

	protected boolean moveRobotInDirectionIfPossible(Robot robot, Direction direction, Board board, List<Robot> otherRobots){
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
    
    protected boolean respawnIfNecessary(Robot robot, Board board){        
        if(robotNotOnBoard(robot, board) || robotInPit(robot, board)) {
            robot.respawn();
            return true;
        }
        return false;
    }

    protected void checkIfWinner(Robot robot, Board board){
        Square currentPosition = board.getSquare(robot.getXCoordinate(), robot.getYCoordinate());
        if(currentPosition instanceof FinalCheckPoint) robot.setToWinner();;             
    }
    
    private boolean robotNotOnBoard(Robot robot, Board board){
        return robot.getXCoordinate() < 0 || robot.getYCoordinate() < 0 || robot.getXCoordinate() >= board.getWidth() || robot.getYCoordinate() >= board.getHeight();
    }
    
    private boolean robotInPit(Robot robot, Board board){
        Square currentPosition = board.getSquare(robot.getXCoordinate(), robot.getYCoordinate());
        return (currentPosition instanceof Pit);
    }

    public int getSpeed(){
        return this.speed;
    }

}