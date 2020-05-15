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
        this.board = new Board("ES-X*ES-X*ES-N*ES-X*||*ES-W*ES-x*ES-x*ES-x*||*ES-x*ES-x*ES-x*ES-E*||*ES-x*ES-S*ES-x*CH-x");
    }

    public Roborally(Robot robot){
        this.robots.add(robot);
    }

    public Roborally(String boardString){
        this.board = new Board(boardString);
    }

    public Roborally(String boardString, Robot robot){
        this.board = new Board(boardString);
        this.robots.add(robot);
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
        for(int cardNr=0;cardNr<5;cardNr++){
            robots.sort(Robot.COMPARE_BY_CARD);
            for(Robot robot : robots){
                robotPlaysCard(robot, cardNr);
            }
            activateBoardElements(SlowConveyorbelt.class);
            activateBoardElements(Gear180.class);
            activateBoardElements(GearRight.class);
            activateBoardElements(GearLeft.class);
            fireRobotLasers();
            activateBoardElements(Checkpoint.class);
        }
        //This keeps the order of the robots consistent for the frontend.
        robots.sort(Robot.COMPARE_BY_NAME);
        for(Robot robot : robots){
            robot.cyclePowerState();
        }
    }

    private void robotPlaysCard(Robot robot, int cardNr){
        Card playingCard = robot.getCard(cardNr);
        playingCard.doCardAction(robot, board, robots);
        robot.unready();
        robot.updateCurrentCard();
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