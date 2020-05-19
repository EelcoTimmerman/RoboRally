package nl.sogyo.roborally.domain.squares;

import java.util.List;

import nl.sogyo.roborally.domain.Direction;
import nl.sogyo.roborally.domain.robots.Robot;

public class SlowConveyorbelt extends Square{
    private Direction movementDirection;

    public SlowConveyorbelt(){
    }

    public SlowConveyorbelt(String walls){
        super(walls);
    }

    public SlowConveyorbelt(Direction direction){
        this.movementDirection = direction;
    }

    public Direction getMovementDirection(){
        return this.movementDirection;
    }

    @Override
    public String getType(){
        return "SlowConveyorbelt" + this.movementDirection;
    }

    @Override
    public void doSquareAction(Robot robot, Board board, List<Robot> robots){
        if(!hasWallAt(movementDirection)){
            turnRobotIfNecessary(robot, board);
            robot.move(movementDirection);
        }
        if(robotNotOnBoard(robot, board)||robotInPit(robot, board)) robot.respawn();
    }

    private void turnRobotIfNecessary(Robot robot, Board board){
        Square destination = getDestination(robot.getXCoordinate(), robot.getYCoordinate(), board);
        if(destination instanceof SlowConveyorbelt){
            SlowConveyorbelt destinationBelt = (SlowConveyorbelt) destination;
            Direction destinationDirection = destinationBelt.getMovementDirection();
            if(destinationDirection == movementDirection.getLeft()) robot.turnLeft();
            else if(destinationDirection == movementDirection.getRight()) robot.turnRight();
            else if(destinationDirection == movementDirection.getReverse()) robot.turnReverse();
        }
    }

    private Square getDestination(int xCoordinate, int yCoordinate, Board board){
        switch(movementDirection){
            case NORTH: yCoordinate--;
                        break;
            case EAST: xCoordinate++;
                        break;
            case SOUTH: yCoordinate++;
                        break;
            case WEST: xCoordinate--;
                        break;
        }
        if(xCoordinate < 0 || yCoordinate < 0 || xCoordinate >= board.getWidth() || yCoordinate >= board.getHeight()) return null;
        else return board.getSquare(xCoordinate, yCoordinate);
    }
    
    private boolean robotInPit(Robot robot, Board board){
        Square currentPosition = board.getSquare(robot.getXCoordinate(), robot.getYCoordinate());
        return (currentPosition instanceof Pit);
    }
    
    private boolean robotNotOnBoard(Robot robot, Board board){
        return robot.getXCoordinate() < 0 || robot.getYCoordinate() < 0 || robot.getXCoordinate() >= board.getWidth() || robot.getYCoordinate() >= board.getHeight();
    }
}