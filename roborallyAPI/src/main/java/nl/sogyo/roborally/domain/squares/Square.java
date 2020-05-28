package nl.sogyo.roborally.domain.squares;

import java.util.List;

import nl.sogyo.roborally.domain.*;
import nl.sogyo.roborally.domain.robots.Robot;

public abstract class Square{
    protected boolean northWall;
    protected boolean eastWall;
    protected boolean southWall;
    protected boolean westWall;

    public Square(){
    }

    public Square(String walls){
		if(walls.contains("N")){
			this.northWall = true;
		}
		if(walls.contains("E")){
			this.eastWall = true;
        }
        if(walls.contains("S")){
			this.southWall = true;
		}
		if(walls.contains("W")){
			this.westWall = true;
		}
    }

    public boolean hasWallAt(Direction direction){
        switch(direction){
            case NORTH: return northWall;
            case EAST: return eastWall;
            case SOUTH: return southWall;
            case WEST: return westWall;
            default: throw new RuntimeException("Somehow there are more than four directions.");
        }
    }
    
    public abstract String getType();

    public abstract void doSquareAction(Robot robot, Board board, List<Robot> robots);
}