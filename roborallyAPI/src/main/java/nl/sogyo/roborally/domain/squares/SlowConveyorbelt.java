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
        System.out.println("Entering moveRobotInDirectionIfPossible for: " + robot.getName() + "...");
        System.out.println(robot.getName() + " at the beginning of its function call is at position [" + robot.getXCoordinate() + "," + robot.getYCoordinate() + "].");
        boolean hasMoved = true;
        SlowConveyorbelt currentPosition = (SlowConveyorbelt) board.getSquare(robot.getXCoordinate(), robot.getYCoordinate());
        boolean isBlockedByWall = currentPosition.hasWallAt(direction);
        if(!isBlockedByWall){
            String robotTurnDirection = currentPosition.turnRobotIfNecessary(robot, board);
            robot.move(direction);
            System.out.println(robot.getName() + " tries to move to position [" + robot.getXCoordinate() + "," + robot.getYCoordinate() + "].");
            if(!respawnRobotIfNecessary(robot, board)){
                Square destination = board.getSquare(robot.getXCoordinate(), robot.getYCoordinate());
                for(Robot otherRobot : otherRobots){
                    System.out.println("Entering the for-loop to check if anyone is in the way of " + robot.getName() + ".");
                    System.out.println("Checking if " + otherRobot.getName() + " is in the way...");
                    if(otherRobot.isAt(robot.getXCoordinate(), robot.getYCoordinate()) && (otherRobot != robot & destination instanceof SlowConveyorbelt)){
                        System.out.println(otherRobot.getName() + " is in the way of " + robot.getName() + " at position [" + robot.getXCoordinate() + "," + robot.getYCoordinate() + "].");
                        SlowConveyorbelt destinationBelt = (SlowConveyorbelt) destination;
                        hasMoved &= destinationBelt.moveRobotInDirectionIfPossible(otherRobot, destinationBelt.getMovementDirection(), board, otherRobots);
                        if(hasMoved){
                            System.out.println(otherRobot.getName() + " has moved out of the way and is now at position [" + otherRobot.getXCoordinate() + "," + otherRobot.getYCoordinate() + "].");
                            respawnRobotIfNecessary(otherRobot, board);
                        }
                        else{
                            System.out.println(otherRobot.getName() + " has not moved out of the way of " + robot.getName() + " and is still at position [" + otherRobot.getXCoordinate() + "," + otherRobot.getYCoordinate() + "].");
                            robot.turnBack(robotTurnDirection);
                            robot.move(direction.getReverse());
                            System.out.println("Therefore " + robot.getName() + " now needs to move back to its orginal position and is at [" + robot.getXCoordinate() + "," + robot.getYCoordinate() + "].");
                        }
                        System.out.println("Exiting for loop for " + otherRobot.getName() + " in original function-call for " + robot.getName());
                        break;
                    } else if(otherRobot.isAt(robot.getXCoordinate(), robot.getYCoordinate()) && (otherRobot != robot & !(destination instanceof SlowConveyorbelt))){
                        robot.turnBack(robotTurnDirection);
                        robot.move(direction.getReverse());
                        hasMoved = false;
                        System.out.println(otherRobot.getName() + " is not on a slow conveyorbelt and can therefore not move out of the way of " + robot.getName() + ".");
                        System.out.println("So " + robot.getName() + " needs to move back to its starting position and is now at [" + robot.getXCoordinate() + "," + robot.getYCoordinate() + "].");
                    }
                }
            }
        }
        else{
            hasMoved = false;
        }
        System.out.println(robot.getName() + " at the end of its function call is at position [" + robot.getXCoordinate() + "," + robot.getYCoordinate() + "].");
        System.out.println("Exiting moveRobotInDirectionIfPossible for: " + robot.getName() + "...");
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