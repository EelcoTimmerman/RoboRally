package nl.sogyo.roborally.domain.squares;

import java.util.ArrayList;
import java.util.List;

import nl.sogyo.roborally.domain.Direction;
import nl.sogyo.roborally.domain.robots.Robot;

public class SlowConveyorbelt extends Square{
    private Direction movementDirection;
    private static List<Robot> robotsOnSlowConveyorbelt = new ArrayList<>();

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

    public static void addRobotsToSlowConveyorbeltList(Board board, List<Robot> robots){
        for(Robot robot : robots){
            Square currentPosition = board.getSquare(robot.getXCoordinate(), robot.getYCoordinate());
            if(currentPosition instanceof SlowConveyorbelt & !(robotsOnSlowConveyorbelt.contains(robot))){
                robotsOnSlowConveyorbelt.add(robot);
            }
        }
    }

    private boolean moveRobotInDirectionIfPossible(Robot robot, Direction direction, Board board, List<Robot> otherRobots){
        boolean hasMoved = true;
        SlowConveyorbelt currentPosition = (SlowConveyorbelt) board.getSquare(robot.getXCoordinate(), robot.getYCoordinate());
        boolean isBlockedByWall = currentPosition.hasWallAt(direction);
        if(!isBlockedByWall & robotsOnSlowConveyorbelt.contains(robot)){
            String robotTurnDirection = currentPosition.turnRobotIfNecessary(robot, board);
            robot.move(direction);
            if(!respawnRobotIfNecessary(robot, board)){
                Square destination = board.getSquare(robot.getXCoordinate(), robot.getYCoordinate());
                for(Robot otherRobot : otherRobots){
                    if(otherRobot.isAt(robot.getXCoordinate(), robot.getYCoordinate()) && (otherRobot != robot & destination instanceof SlowConveyorbelt)){
                        SlowConveyorbelt destinationBelt = (SlowConveyorbelt) destination;
                        hasMoved &= destinationBelt.moveRobotInDirectionIfPossible(otherRobot, destinationBelt.getMovementDirection(), board, otherRobots);
                        if(hasMoved){
                            respawnRobotIfNecessary(otherRobot, board);
                        }
                        else{
                            robot.turnBack(robotTurnDirection);
                            robot.move(direction.getReverse());
                        }
                        break;
                    } else if(otherRobot.isAt(robot.getXCoordinate(), robot.getYCoordinate()) && (otherRobot != robot & !(destination instanceof SlowConveyorbelt))){
                        robot.turnBack(robotTurnDirection);
                        robot.move(direction.getReverse());
                        hasMoved = false;
                    }
                }
            }
        }
        else{
            hasMoved = false;
        }
        if(hasMoved & robotsOnSlowConveyorbelt.contains(robot)){
            robotsOnSlowConveyorbelt.remove(robot);
        }
        return hasMoved;
    }

    private String turnRobotIfNecessary(Robot robot, Board board){
        Square destination = getDestination(robot.getXCoordinate(), robot.getYCoordinate(), board);
        String turnDirection = "";
        if(destination instanceof SlowConveyorbelt){
            SlowConveyorbelt destinationBelt = (SlowConveyorbelt) destination;
            Direction destinationDirection = destinationBelt.getMovementDirection();
            if(destinationDirection == movementDirection.getLeft()){
                robot.turnLeft();
                turnDirection = "left";
            } else if(destinationDirection == movementDirection.getRight()){
                robot.turnRight();
                turnDirection = "right";
            } else if(destinationDirection == movementDirection.getReverse()){
                robot.turnReverse();
                turnDirection = "reverse";
            }
        }
        return turnDirection;
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
    
    private boolean respawnRobotIfNecessary(Robot robot, Board board){        
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