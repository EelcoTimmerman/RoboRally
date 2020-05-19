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

    public SlowConveyorbelt(Direction direction, String walls){
        super(walls);
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
        moveRobotInDirectionIfPossible(robot, movementDirection, board, robots);
    }

    private boolean moveRobotInDirectionIfPossible(Robot robot, Direction direction, Board board, List<Robot> otherRobots){
        boolean hasMoved = true;
        Square currentPosition = board.getSquare(robot.getXCoordinate(), robot.getYCoordinate());
        boolean isBlockedByWall = currentPosition.hasWallAt(direction);
        if(!isBlockedByWall){
            turnRobotIfNecessary(robot, board);
            robot.move(direction);
            if(!respawnIfNecessary(robot, board)){
                Square destination = board.getSquare(robot.getXCoordinate(), robot.getYCoordinate());
                for(Robot otherRobot : otherRobots){
                    if(otherRobot.isAt(robot.getXCoordinate(), robot.getYCoordinate()) && (otherRobot != robot & destination instanceof SlowConveyorbelt)){
                        SlowConveyorbelt destinationBelt = (SlowConveyorbelt) destination;
                        hasMoved &= moveRobotInDirectionIfPossible(otherRobot, destinationBelt.getMovementDirection(), board, otherRobots);
                        if(hasMoved){
                            respawnIfNecessary(otherRobot, board);
                        }
                        else{
                            robot.move(direction.getReverse());
                        }
                        break;
                    } else if(otherRobot.isAt(robot.getXCoordinate(), robot.getYCoordinate()) && (otherRobot != robot & !(destination instanceof SlowConveyorbelt))){
                        robot.move(direction.getReverse());
                    }
                }
            }
        }
        else{
            hasMoved = false;
        }
        return hasMoved;
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
    
    private boolean respawnIfNecessary(Robot robot, Board board){        
        if(robotNotOnBoard(robot, board) || robotInPit(robot, board)) {
            robot.respawn();
            return true;
        }
        return false;
    }
    
    private boolean robotNotOnBoard(Robot robot, Board board){
        return robot.getXCoordinate() < 0 || robot.getYCoordinate() < 0 || robot.getXCoordinate() >= board.getWidth() || robot.getYCoordinate() >= board.getHeight();
    }
    
    private boolean robotInPit(Robot robot, Board board){
        Square currentPosition = board.getSquare(robot.getXCoordinate(), robot.getYCoordinate());
        return (currentPosition instanceof Pit);
    }
}