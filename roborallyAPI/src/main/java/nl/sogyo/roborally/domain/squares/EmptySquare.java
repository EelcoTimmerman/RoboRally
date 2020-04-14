package nl.sogyo.roborally.domain.squares;

public class EmptySquare extends Square {

    public EmptySquare(){
        this.northWall = false;
        this.eastWall = false;
        this.southWall = false;
        this.westWall = false;
    }

    public EmptySquare(boolean northWall, boolean eastWall, boolean southWall, boolean westWall){
        this.northWall = northWall;
        this.eastWall = eastWall;
        this.southWall = southWall;
        this.westWall = westWall;
    }

    public Square getDestination(){
        return this;
    }
}