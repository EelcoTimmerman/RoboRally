package nl.sogyo.roborally.domain.squares;

public class EmptySquare extends Square {

<<<<<<< HEAD
//    public EmptySquare(){
//
//    }
//
//    public EmptySquare(boolean northWall, boolean eastWall, boolean southWall, boolean westWall){
//        this.northWall = northWall;
//        this.eastWall = eastWall;
//        this.southWall = southWall;
//        this.westWall = westWall;
//    }
=======
    public EmptySquare(){
    }

    public EmptySquare(boolean northWall, boolean eastWall, boolean southWall, boolean westWall){
        this.northWall = northWall;
        this.eastWall = eastWall;
        this.southWall = southWall;
        this.westWall = westWall;
    }
>>>>>>> branch 'master' of git@git.sogyo.nl:academy/projects/other/roborally.git

    public Square getDestination(){
        return this;
    }
}