package nl.sogyo.roborally.domain;

import java.util.ArrayList;
import java.util.List;


import nl.sogyo.roborally.domain.cards.Card;
import nl.sogyo.roborally.domain.cards.Deck;
import nl.sogyo.roborally.domain.elements.Laser;
import nl.sogyo.roborally.domain.robots.Robot;
import nl.sogyo.roborally.domain.squares.*;

public class Roborally{

    List<Robot> robots = new ArrayList<Robot>();
    Board board;
    private Robot winner = null;
    Deck deck = new Deck();
    
    public Roborally(){
        this.board = BoardFactory.createTESTBOARD4X4();
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

    public boolean allRobotsReady(){
        boolean robotsReady = true;
        for(Robot robot : robots){
            robotsReady &= (robot.isReady() || robot.isInactive());
        }
        return robotsReady;
    }

    public void playAllRegistersIfRobotsReady(){
        if(allRobotsReady()){
            for(int registernr=0;registernr<5;registernr++){
                playRegister(registernr);
                if(this.winner != null) break;//TO DO: add a check that one robot cannot move the other from
                //the winner square and be the winner himself.
            }
            prepareNextRound();
        }
    }

    private void playRegister(int registernr){
        robots.sort(Robot.COMPARE_BY_CARD(registernr));
        for(Robot robot : robots){
            robotPlaysCard(robot, registernr);
            if(robot.isWinner()){
                this.winner = robot;
            }                
        }
        if(this.winner == null){
            activateBoardElements(SlowConveyorbelt.class);
            activateBoardElements(Gear180.class);
            activateBoardElements(GearRight.class);
            activateBoardElements(GearLeft.class);
            fireBoardLasers();
            fireRobotLasers();
            activateBoardElements(Checkpoint.class);
        }
    }

    private void prepareNextRound(){
        robots.sort(Robot.COMPARE_BY_NAME);
        this.deck = new Deck();  
        for(Robot robot : robots){
            robot.cyclePowerState();
            robot.clearHand();
            robot.drawCards(deck);
            robot.unready();
        }
    }

    public Robot getWinner(){
        return this.winner;
    }

    void activateAllBoardElements(){
        activateBoardElements(SlowConveyorbelt.class);
        activateBoardElements(Gear180.class);
        activateBoardElements(GearRight.class);
        activateBoardElements(GearLeft.class);
        fireBoardLasers();
        fireRobotLasers();
        activateBoardElements(Checkpoint.class);
    }

    private void robotPlaysCard(Robot robot, int cardNr){
        if(!robot.isInactive()){
            Card playingCard = robot.getCard(cardNr);
            playingCard.doCardAction(robot, board, robots);
            robot.updateCurrentCard();
        }
    }

    public void program(int cardnr){
        this.robots.get(0).program(cardnr);
    }

    private <T extends Square> void activateBoardElements(Class<T> elementTypeToActivate){
        SlowConveyorbelt.addRobotsToSlowConveyorbeltList(board, robots);
        for(Robot robot : robots){
            Square position = board.getSquare(robot.getXCoordinate(), robot.getYCoordinate());
            if(elementTypeToActivate.isInstance(position)) position.doSquareAction(robot, board, robots);
        }
        SlowConveyorbelt.clearListRobotsOnSlowConveyorbelt();
    }

    private void fireBoardLasers(){
        for(Laser laser : board.getLasers()){
            laser.fire(robots, board);
        }
    }

    private void fireRobotLasers(){
        for(Robot robot : robots){
            robot.fireLaser(robots, board);
        }
    }

    public void addRobot(Robot robot){
        this.robots.add(robot);
        robot.drawCards(deck);
        robots.sort(Robot.COMPARE_BY_NAME);
    }

    public void removeRobot(Robot robot){
        robot.clearHand(deck);
        this.robots.remove(robot);
    }

    public Deck getDeck(){
        return this.deck;
    }
}