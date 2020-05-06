package nl.sogyo.roborally.domain;

import java.util.ArrayList;
import java.util.List;

import nl.sogyo.roborally.domain.cards.ICard;
import nl.sogyo.roborally.domain.cards.Deck;
import nl.sogyo.roborally.domain.robots.Robot;
import nl.sogyo.roborally.domain.squares.*;

public class Roborally{

    List<Robot> robots = new ArrayList<Robot>();
    Board board;
    Deck deck;
    
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
            robotsReady &= robot.isReady();
        }
        if(robotsReady) playRound();
    }

    private void playRound(){
        robots.sort(Robot.COMPARE_BY_CARD);
        for(Robot robot : robots){
            ICard card = robot.getCard();
            card.doCardAction(robot, board);
            robot.unready();
        }
        //This keeps the order of the robots consistent for the frontend.
        robots.sort(Robot.COMPARE_BY_NAME);

        activateBoardElements(SlowConveyorbelt.class);
        activateBoardElements(Gear180.class);
        activateBoardElements(GearRight.class);
        activateBoardElements(GearLeft.class);
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

    public void addRobot(Robot robot){
        this.robots.add(robot);
        robots.sort(Robot.COMPARE_BY_NAME);
    }

    public void removeRobot(Robot robot){
        this.robots.remove(robot);
    }

 
}