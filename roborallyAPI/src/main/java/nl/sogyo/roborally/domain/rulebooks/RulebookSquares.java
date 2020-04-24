package nl.sogyo.roborally.domain.rulebooks;

import java.util.List;

import nl.sogyo.roborally.domain.Direction;
import nl.sogyo.roborally.domain.elements.Laser;
import nl.sogyo.roborally.domain.robots.Robot;
import nl.sogyo.roborally.domain.squares.Board;
import nl.sogyo.roborally.domain.squares.Checkpoint;
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
        playSlowConveyerBelts(squareRobotIsPositionedOn, xCoordinateRobot, yCoordinateRobot);
        playGears(squareRobotIsPositionedOn);
        playCheckpoints(squareRobotIsPositionedOn, xCoordinateRobot, yCoordinateRobot);
    }

    public void playSlowConveyerBelts(Square squareRobotIsPositionedOn, int xCoordinateRobot, int yCoordinateRobot) {
        if(squareRobotIsPositionedOn instanceof SlowConveyorbelt){
            SlowConveyorbelt conveyerBelt = (SlowConveyorbelt) squareRobotIsPositionedOn;
            Direction directionConveyerBelt = conveyerBelt.getMovementDirection();
            switch(directionConveyerBelt){
                case NORTH: this.robot.setYCoordinate(yCoordinateRobot-1); break;
                case EAST: this.robot.setXCoordinate(xCoordinateRobot+1); break;
                case SOUTH: this.robot.setYCoordinate(yCoordinateRobot+1); break;
                case WEST: this.robot.setXCoordinate(xCoordinateRobot-1); break;
            }
        }
    }

    private void playGears(Square squareRobotIsPositionedOn) {
        if(squareRobotIsPositionedOn instanceof GearLeft) this.robot.turnLeft();
        if(squareRobotIsPositionedOn instanceof GearRight) this.robot.turnRight();
        if(squareRobotIsPositionedOn instanceof Gear180) this.robot.turnReverse();
    }
    
    private void playCheckpoints(Square squareRobotIsPositionedOn, int xCoordinateRobot, int yCoordinateRobot) {
        if(squareRobotIsPositionedOn instanceof Checkpoint) this.robot.setRespawnPoint(xCoordinateRobot, yCoordinateRobot);
    }
}