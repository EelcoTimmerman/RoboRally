package nl.sogyo.roborally.domain.elements;

import java.util.List;

import nl.sogyo.roborally.domain.Direction;
import nl.sogyo.roborally.domain.robots.Robot;
import nl.sogyo.roborally.domain.squares.Board;
import nl.sogyo.roborally.domain.squares.Square;

public class Laser{

    int xCoordinate;
    int yCoordinate;
    Direction orientation;
    int firepower;

    public Laser(){
    }

    public Laser(int xCoordinate, int yCoordinate, Direction orientation, int firepower){
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.orientation = orientation;
        this.firepower = firepower;
    }

    public Direction getOrientation(){
        return orientation;
    }

    public int getFirepower(){
        return firepower;
    }
    
    public int getxCoordinate(){
        return xCoordinate;
    }

    public int getyCoordinate(){
        return yCoordinate;
    }

    public void fire(List<Robot> robots, Board board){
        switch(this.orientation){
            case NORTH: fireNorth(robots, board);
                        break;
            case EAST: fireEast(robots, board);
                        break;                        
            case SOUTH: fireSouth(robots, board);
                        break;            
            case WEST: fireWest(robots, board);
                        break;
        }
    }
    

    private void fireNorth(List<Robot> robots, Board board){
        int xCoordinate = this.xCoordinate;
        int yCoordinate = this.yCoordinate;
        while(yCoordinate >= 0){
            boolean hitRobot = false;
            for(Robot robot : robots){
                hitRobot |= robot.takeDamageIfHit(xCoordinate, yCoordinate, this.firepower);
            }
            Square currentSquare = board.getSquare(xCoordinate, yCoordinate);
            if(hitRobot || currentSquare.hasWallAt(Direction.NORTH)){
                break;
            }            
            yCoordinate--;
        }
    }

    private void fireEast(List<Robot> robots, Board board){
        int xCoordinate = this.xCoordinate;
        int yCoordinate = this.yCoordinate;
        while(xCoordinate < board.getWidth()){
            boolean hitRobot = false;
            for(Robot robot : robots){
                hitRobot |= robot.takeDamageIfHit(xCoordinate, yCoordinate, this.firepower);
            }
            Square currentSquare = board.getSquare(xCoordinate, yCoordinate);
            if(hitRobot || currentSquare.hasWallAt(Direction.EAST)){
                break;
            }
            xCoordinate++;
        }        
    }

    private void fireSouth(List<Robot> robots, Board board){
        int xCoordinate = this.xCoordinate;
        int yCoordinate = this.yCoordinate;
        while(yCoordinate < board.getHeight()){
            boolean hitRobot = false;
            for(Robot robot : robots){
                hitRobot |= robot.takeDamageIfHit(xCoordinate, yCoordinate, this.firepower);
            }
            Square currentSquare = board.getSquare(xCoordinate, yCoordinate);
            if(hitRobot || currentSquare.hasWallAt(Direction.SOUTH)){
                break;
            }
            yCoordinate++;
        }
    }

    private void fireWest(List<Robot> robots, Board board){
        int xCoordinate = this.xCoordinate;
        int yCoordinate = this.yCoordinate;
        while(xCoordinate >= 0){
            boolean hitRobot = false;
            for(Robot robot : robots){
                hitRobot |= robot.takeDamageIfHit(xCoordinate, yCoordinate, this.firepower);
            }
            Square currentSquare = board.getSquare(xCoordinate, yCoordinate);
            if(hitRobot || currentSquare.hasWallAt(Direction.WEST)){
                break;
            }
            xCoordinate--;
        }
    }
}