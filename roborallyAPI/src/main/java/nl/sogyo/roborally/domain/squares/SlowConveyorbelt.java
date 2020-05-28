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

    public static void clearListRobotsOnSlowConveyorbelt(){
        robotsOnSlowConveyorbelt.clear();
    }

    private boolean moveRobotInDirectionIfPossible(Robot robot, Direction direction, Board board, List<Robot> otherRobots){
        boolean canMove = true;
        SlowConveyorbelt currentPosition = (SlowConveyorbelt) board.getSquare(robot.getXCoordinate(), robot.getYCoordinate());
        boolean isBlockedByWall = currentPosition.hasWallAt(direction);
        boolean robotHasNotAlreadyMovedOnBelt = robotsOnSlowConveyorbelt.contains(robot);
        if(!isBlockedByWall & robotHasNotAlreadyMovedOnBelt & !robotsHaveSameDestination(robot, board)){
            Square destination = getDestination(robot.getXCoordinate(), robot.getYCoordinate(), board);
            if(destination != null){
                boolean otherRobotAtDestination = false;
                for(Robot otherRobot : otherRobots){
                    otherRobotAtDestination = checkIfOtherRobotIsAtDestination(otherRobot, board, destination);
                    if(otherRobotAtDestination && (otherRobot != robot & destination instanceof SlowConveyorbelt)){
                        SlowConveyorbelt destinationBelt = (SlowConveyorbelt) destination;
                        boolean otherRobotMoved = destinationBelt.moveRobotInDirectionIfPossible(otherRobot, destinationBelt.getMovementDirection(), board, otherRobots);
                        if(otherRobotMoved){
                            turnRobotIfNecessary(robot, board);
                            robot.move(direction);
                            respawnRobotIfNecessary(robot, board);
                        }
                        else canMove = false;
                        break;
                    } else if(otherRobotAtDestination && (otherRobot != robot & !(destination instanceof SlowConveyorbelt))){
                        canMove = false;
                        break;
                    }
                }
                if(!otherRobotAtDestination){
                    turnRobotIfNecessary(robot, board);
                    robot.move(direction);
                    respawnRobotIfNecessary(robot, board);
                }
            }
            else robot.respawn();
        }
        else canMove = false;
        robotsOnSlowConveyorbelt.remove(robot);
        return canMove;
    }

    private boolean checkIfOtherRobotIsAtDestination(Robot otherRobot, Board board, Square destination){
        Square otherRobotPosition = board.getSquare(otherRobot.getXCoordinate(), otherRobot.getYCoordinate());
        if(destination == otherRobotPosition) return true;
        else return false;
    }

    private boolean robotsHaveSameDestination(Robot robot, Board board){
        Square currentRobotDestination = getDestination(robot.getXCoordinate(), robot.getYCoordinate(), board);
        boolean destinationMatch = false;
        if(!(currentRobotDestination instanceof Pit)){
            ArrayList<Robot> copyRobotsOnSlowConveyorbelt = new ArrayList<Robot>();
            for(Robot robotOnOriginalList : robotsOnSlowConveyorbelt) copyRobotsOnSlowConveyorbelt.add(robotOnOriginalList);
            for(Robot otherRobot : copyRobotsOnSlowConveyorbelt){
                if(robotsOnSlowConveyorbelt.size() > 1){
                Square otherRobotDestination = getDestination(otherRobot.getXCoordinate(), otherRobot.getYCoordinate(), board);
                if((otherRobotDestination == currentRobotDestination) && (otherRobot != robot)){
                    destinationMatch = true;
                    robotsOnSlowConveyorbelt.remove(otherRobot);
                    }
                } else break;
            }
        }
        return destinationMatch;
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
        SlowConveyorbelt origin = (SlowConveyorbelt) board.getSquare(xCoordinate, yCoordinate);
        switch(origin.movementDirection){
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