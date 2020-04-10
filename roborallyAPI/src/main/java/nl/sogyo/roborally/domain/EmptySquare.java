package nl.sogyo.roborally.domain;

public class EmptySquare implements ISquare {
    private boolean northWall;
    private boolean eastWall;
    private boolean southWall;
    private boolean westWall;

    private ISquare northNeighbour;
    private ISquare eastNeighbour;
    private ISquare southNeighbour;
    private ISquare westNeighbour;

    public boolean hasWallAt(Direction direction){
        switch(direction){
            case NORTH: return northWall;
            case EAST: return eastWall;
            case SOUTH: return southWall;
            case WEST: return westWall;
            default: throw new RuntimeException("Somehow there are more than four directions.");
        }
    }

    public EmptySquare(boolean northWall, boolean eastWall, boolean southWall, boolean westWall){
        this.northWall = northWall;
        this.eastWall = eastWall;
        this.southWall = southWall;
        this.westWall = westWall;
    }
}