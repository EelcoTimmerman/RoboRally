package nl.sogyo.roborally.domain.rulebooks;

import java.util.List;

import nl.sogyo.roborally.domain.Direction;
import nl.sogyo.roborally.domain.elements.Laser;
import nl.sogyo.roborally.domain.robots.Robot;
import nl.sogyo.roborally.domain.squares.Board;
import nl.sogyo.roborally.domain.squares.SlowConveyorbelt;
import nl.sogyo.roborally.domain.squares.Square;

public class RulebookSquares{

    Robot robot;
    Board board;

    public RulebookSquares(Board board, Robot robot){
        this.board = board;
        this.robot = robot;
    }

    public Robot getRobot(){
        return this.robot;
    }
    
    public Board getBoard(){
        return this.board;
    }

    public void playBoardElements(){
        int x = this.robot.getXCoordinate();
        int y = this.robot.getYCoordinate();
        if(this.board.getSquare(x, y) instanceof SlowConveyorbelt){
            SlowConveyorbelt conveyerBelt = (SlowConveyorbelt) this.board.getSquare(x, y);
            Direction directionConveyerBelt = conveyerBelt.getMovementDirection();
            if(directionConveyerBelt == Direction.NORTH){
                this.robot.setYCoordinate(y-1);
            }
            if(directionConveyerBelt == Direction.EAST){
                this.robot.setXCoordinate(x+1);
            }
            if(directionConveyerBelt == Direction.SOUTH){
                this.robot.setYCoordinate(y+1);
            }
            if(directionConveyerBelt == Direction.WEST){
                this.robot.setXCoordinate(x-1);
            }
        }
    }
}