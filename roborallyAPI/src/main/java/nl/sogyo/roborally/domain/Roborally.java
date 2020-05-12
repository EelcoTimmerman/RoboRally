package nl.sogyo.roborally.domain;

import java.util.ArrayList;
import java.util.List;


import nl.sogyo.roborally.domain.cards.Card;
import nl.sogyo.roborally.domain.robots.Robot;
import nl.sogyo.roborally.domain.squares.*;

public class Roborally{

    List<Robot> robots = new ArrayList<Robot>();
    Board board;
    
    public Roborally(){
        this.board = Board.createTESTBOARD4X4();
    }

    public Roborally(Robot robot){
        this.robots.add(robot);
    }

    public Roborally(Board board){
        this.board = board;
    }

    public Roborally(Board board, Robot robot){
        this.board = board;
        this.robots.add(robot);
    }

    public List<Robot> getRobots(){
        return this.robots;
    }

    public Board getBoard(){
        return this.board;
    }

    public void playRoundIfAllRobotsReady(){
        boolean robotsReady = true;
        for(Robot robot : robots){
            robotsReady &= (robot.isReady() || robot.isInactive());
        }
        if(robotsReady) playRound();
    }

    private void playRound(){
        robots.sort(Robot.COMPARE_BY_CARD);
        for(Robot robot : robots){
            Card card = robot.getCard();
            card.doCardAction(robot, board, robots);
            robot.unready();
            if(robot.isInactive()){
                robot.activate();
            }
            if(robot.isPoweringDown()){
                robot.shutDown();
            }
        }
        //This keeps the order of the robots consistent for the frontend.
        robots.sort(Robot.COMPARE_BY_NAME);

        activateBoardElements(SlowConveyorbelt.class);
        activateBoardElements(Gear180.class);
        activateBoardElements(GearRight.class);
        activateBoardElements(GearLeft.class);
        fireRobotLasers();
        activateBoardElements(Checkpoint.class);
    }

    public void program(int cardnr){
        this.robots.get(0).program(cardnr);
    }

    private <T extends Square> void activateBoardElements(Class<T> elementTypeToActivate){
        for(Robot robot : robots){
            Square position = board.getSquare(robot.getXCoordinate(), robot.getYCoordinate());
            if(elementTypeToActivate.isInstance(position)){
                position.doSquareAction(robot, board);
            }
        }
    }

    private void fireRobotLasers(){
        for(Robot robot : robots){
            robot.fireLaser(robots, board);
        }
    }

    public void addRobot(Robot robot){
        this.robots.add(robot);
        robots.sort(Robot.COMPARE_BY_NAME);
    }

    public void removeRobot(Robot robot){
        this.robots.remove(robot);
    }
}