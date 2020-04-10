package nl.sogyo.roborally.domain;

public class EmptySquare extends Square {

    public EmptySquare(boolean northWall, boolean eastWall, boolean southWall, boolean westWall){
        this.northWall = northWall;
        this.eastWall = eastWall;
        this.southWall = southWall;
        this.westWall = westWall;
    }

    public EmptySquare(){}

    public Square getDestination(){
        return this;
    }
}