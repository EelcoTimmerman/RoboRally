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
        System.out.println("Inside addRobotsToSlowConveyorbeltList()... ");
        for(Robot robot : robots){
            Square currentPosition = board.getSquare(robot.getXCoordinate(), robot.getYCoordinate());
            if(currentPosition instanceof SlowConveyorbelt & !(robotsOnSlowConveyorbelt.contains(robot))){
                System.out.println("Added " + robot.getName() + " to robotsOnSlowConveyorbelt.");
                robotsOnSlowConveyorbelt.add(robot);
            }
        }
    }

    public static void clearListRobotsOnSlowConveyorbelt(){
        robotsOnSlowConveyorbelt.clear();
    }

    private boolean moveRobotInDirectionIfPossible(Robot robot, Direction direction, Board board, List<Robot> otherRobots){
        System.out.println("Entering moveRobotInDirectionIfPossible for: " + robot.getName() + "...");
        System.out.println(robot.getName() + " at the beginning of its function call is at position [" + robot.getXCoordinate() + "," + robot.getYCoordinate() + "].");
        boolean canMove = true;
        SlowConveyorbelt currentPosition = (SlowConveyorbelt) board.getSquare(robot.getXCoordinate(), robot.getYCoordinate());
        boolean isBlockedByWall = currentPosition.hasWallAt(direction);
        if(!isBlockedByWall & robotsOnSlowConveyorbelt.contains(robot) & !robotsHaveSameDestination(robot, board)){
            Square destination = getDestination(robot.getXCoordinate(), robot.getYCoordinate(), board);
            if(destination != null){
                System.out.println("The destination of " + robot.getName() + " is on the board.");
                System.out.println(robot.getName() + " checks if no one is in the way at destination.");
                boolean otherRobotAtDestination = false;
                for(Robot otherRobot : otherRobots){
                    System.out.println("Checking via for-loop if " + otherRobot.getName() + " is in the way of " + robot.getName() + ".");
                    otherRobotAtDestination = checkIfOtherRobotIsAtDestination(otherRobot, board, destination);
                    if(otherRobotAtDestination && (otherRobot != robot & destination instanceof SlowConveyorbelt)){
                        System.out.println(otherRobot.getName() + " is in the way of " + robot.getName() + " at slow conveyorbelt position [" + otherRobot.getXCoordinate() + "," + otherRobot.getYCoordinate() + "].");
                        SlowConveyorbelt destinationBelt = (SlowConveyorbelt) destination;
                        boolean otherRobotMoved = destinationBelt.moveRobotInDirectionIfPossible(otherRobot, destinationBelt.getMovementDirection(), board, otherRobots);
                        if(otherRobotMoved){
                            System.out.println(otherRobot.getName() + " has moved out of the way and is now at position [" + otherRobot.getXCoordinate() + "," + otherRobot.getYCoordinate() + "].");
                            turnRobotIfNecessary(robot, board);
                            robot.move(direction);
                            respawnRobotIfNecessary(robot, board);
                        }
                        else{
                            canMove = false;
                            System.out.println(otherRobot.getName() + " has not moved out of the way of " + robot.getName() + " and is still at position [" + otherRobot.getXCoordinate() + "," + otherRobot.getYCoordinate() + "].");
                            System.out.println("Therefore " + robot.getName() + " cannot move and is still at [" + robot.getXCoordinate() + "," + robot.getYCoordinate() + "].");
                        }
                        System.out.println("Exiting for loop for " + otherRobot.getName() + " in original function-call for " + robot.getName());
                        break;
                    } else if(otherRobotAtDestination && (otherRobot != robot & !(destination instanceof SlowConveyorbelt))){
                        canMove = false;
                        System.out.println(otherRobot.getName() + " is not on a slow conveyorbelt and can therefore not move out of the way of " + robot.getName() + ".");
                        System.out.println("So " + robot.getName() + " cannot move and is still at its starting position at [" + robot.getXCoordinate() + "," + robot.getYCoordinate() + "].");
                        break;
                    }
                }
                if(!otherRobotAtDestination){
                    System.out.println("No other robot is in the way and " + robot.getName() + " can now move.");
                    turnRobotIfNecessary(robot, board);
                    robot.move(direction);
                    respawnRobotIfNecessary(robot, board);
                }
            }
            else{
                System.out.println("The destination of " + robot.getName() + " is off the board, so " + robot.getName() + " respawns.");
                robot.respawn();
            }
        }
        else canMove = false;
        robotsOnSlowConveyorbelt.remove(robot);
        System.out.println(robot.getName() + " at the end of its function call is at position [" + robot.getXCoordinate() + "," + robot.getYCoordinate() + "].");
        System.out.println("Exiting moveRobotInDirectionIfPossible for: " + robot.getName() + "...");
        return canMove;
    }

    private boolean checkIfOtherRobotIsAtDestination(Robot otherRobot, Board board, Square destination){
        System.out.println("Checking if " + otherRobot.getName() + " is at destination.");
        Square otherRobotPosition = board.getSquare(otherRobot.getXCoordinate(), otherRobot.getYCoordinate());
        if(destination == otherRobotPosition){
            System.out.println(otherRobot.getName() + " is at destination.");
            return true;
        }
        else{
            System.out.println(otherRobot.getName() + " is not at destination");
            return false;
        }
    }

    private boolean robotsHaveSameDestination(Robot robot, Board board){
        System.out.println("Checking if another robot has the same destination as " + robot.getName() + "...");
        Square currentRobotDestination = getDestination(robot.getXCoordinate(), robot.getYCoordinate(), board);
        boolean destinationMatch = false;
        if(!(currentRobotDestination instanceof Pit)){
            ArrayList<Robot> copyRobotsOnSlowConveyorbelt = new ArrayList<Robot>();
            for(Robot robotOnOriginalList : robotsOnSlowConveyorbelt){
                copyRobotsOnSlowConveyorbelt.add(robotOnOriginalList);
            }
            for(Robot otherRobot : copyRobotsOnSlowConveyorbelt){
                if(robotsOnSlowConveyorbelt.size() > 1){
                System.out.println("Checking if " + otherRobot.getName() + " has the same destination as " + robot.getName() + "...");
                Square otherRobotDestination = getDestination(otherRobot.getXCoordinate(), otherRobot.getYCoordinate(), board);
                if((otherRobotDestination == currentRobotDestination) && (otherRobot != robot)){
                    destinationMatch = true;
                    robotsOnSlowConveyorbelt.remove(otherRobot);
                    System.out.println(otherRobot.getName() + " has the same destination as " + robot.getName());
                    System.out.println(otherRobot.getName() + " is removed from robotsOnSlowConveyorbelt.");
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