package nl.sogyo.roborally.domain.rulebooks;

import java.util.List;

import nl.sogyo.roborally.domain.Direction;
import nl.sogyo.roborally.domain.elements.Laser;
import nl.sogyo.roborally.domain.robots.Robot;
import nl.sogyo.roborally.domain.squares.Board;
import nl.sogyo.roborally.domain.squares.Gear180;
import nl.sogyo.roborally.domain.squares.GearLeft;
import nl.sogyo.roborally.domain.squares.GearRight;
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
        int xCoordinateRobot = this.robot.getXCoordinate();
        int yCoordinateRobot = this.robot.getYCoordinate();
        Square squareRobotIsPositionedOn = this.board.getSquare(xCoordinateRobot, yCoordinateRobot);
        playSlowConveyerBelt(squareRobotIsPositionedOn, xCoordinateRobot, yCoordinateRobot);
        playGears(squareRobotIsPositionedOn);
    }

    private void playGears(Square square) {
        if(square instanceof GearLeft){
            this.robot.turnLeft();
        }
        if(square instanceof GearRight){
            this.robot.turnRight();
        }
        if(square instanceof Gear180){
            this.robot.turnReverse();
        }
    }

    public void playSlowConveyerBelt(Square square, int xCoordinateRobot, int yCoordinateRobot) {
        if(square instanceof SlowConveyorbelt){
            SlowConveyorbelt conveyerBelt = (SlowConveyorbelt) square;
            Direction directionConveyerBelt = conveyerBelt.getMovementDirection();
            if(directionConveyerBelt == Direction.NORTH){
                this.robot.setYCoordinate(yCoordinateRobot-1);
            }
            if(directionConveyerBelt == Direction.EAST){
                this.robot.setXCoordinate(xCoordinateRobot+1);
            }
            if(directionConveyerBelt == Direction.SOUTH){
                this.robot.setYCoordinate(yCoordinateRobot+1);
            }
            if(directionConveyerBelt == Direction.WEST){
                this.robot.setXCoordinate(xCoordinateRobot-1);
            }
        }
    }
}