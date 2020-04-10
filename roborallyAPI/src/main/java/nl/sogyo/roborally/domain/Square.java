package nl.sogyo.roborally.domain;

public abstract class Square {
    protected boolean northWall;
    protected boolean eastWall;
    protected boolean southWall;
    protected boolean westWall;

    protected Square northNeighbour;
    protected Square eastNeighbour;
    protected Square southNeighbour;
    protected Square westNeighbour;

    public boolean hasWallAt(Direction direction){
        switch(direction){
            case NORTH: return northWall;
            case EAST: return eastWall;
            case SOUTH: return southWall;
            case WEST: return westWall;
            default: throw new RuntimeException("Somehow there are more than four directions.");
        }
    }

    public void setNorthNeighbour(Square northNeighbour){
        this.northNeighbour = northNeighbour;
    }
	public void setEastNeighbour(Square eastNeighbour) {
		this.eastNeighbour = eastNeighbour;
	}

	public void setSouthNeighbour(Square southNeighbour) {
		this.southNeighbour = southNeighbour;
	}

	public void setWestNeighbour(Square westNeighbour) {
		this.westNeighbour = westNeighbour;
	}

    public Square getNeighbour(Direction direction){
        switch(direction){
            case NORTH: return northNeighbour;
            case EAST: return eastNeighbour;
            case SOUTH: return southNeighbour;
            case WEST: return westNeighbour;
            default: throw new RuntimeException("Somehow there are more than four directions.");
        }
    }

	public boolean hasNeighbour(Direction direction) {
		return this.getNeighbour(direction) != null;
	}

}