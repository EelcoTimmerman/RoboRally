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

    public void playRoundIfAllRobotsReady(){
        playRegisterIFAllRobotsReady(0);
    }

    public void playRegisterIFAllRobotsReady(int registernr){
        if(allRobotsReady()) playRegister(registernr);
    }

    private void playRegister(int registernr){
        robots.sort(Robot.COMPARE_BY_CARD(registernr));
        for(Robot robot : robots){
            robotPlaysCard(robot, registernr);                
        }

        activateBoardElements(SlowConveyorbelt.class);
        activateBoardElements(Gear180.class);
        activateBoardElements(GearRight.class);
        activateBoardElements(GearLeft.class);
        fireBoardLasers();
        fireRobotLasers();
        activateBoardElements(Checkpoint.class);

        //This keeps the order of the robots consistent for the frontend.
        robots.sort(Robot.COMPARE_BY_NAME);
    }

    public void prepareNextRound(){
        for(Robot robot : robots){
            robot.cyclePowerState();
            robot.clearHand(deck);
            robot.drawCards(deck);
            robot.unready();
        }
    }

    private void robotPlaysCard(Robot robot, int cardNr){
        Card playingCard = robot.getCard(cardNr);
        playingCard.doCardAction(robot, board, robots);
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