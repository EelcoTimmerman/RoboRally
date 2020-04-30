package nl.sogyo.roborally.domain.rulebooks;

import nl.sogyo.roborally.domain.Direction;
import nl.sogyo.roborally.domain.cards.ICard;
import nl.sogyo.roborally.domain.robots.Robot;
import nl.sogyo.roborally.domain.squares.*;

public class Robotrally{

    Robot robot;
    Board board;
    
    public Robotrally(){
        this.board = new Board("CH-X*ES-X*ES-N*ES-X*||*ES-W*ES-x*ES-x*ES-x*||*ES-x*ES-x*ES-x*ES-E*||*ES-x*ES-S*ES-x*ES-x");
        this.robot = new Robot(2,3, Direction.EAST);
    }

    public Robotrally(Robot robot){
        this.robot = robot;
    }

    public Robotrally(String boardString){
        this.board = new Board(boardString);
    }

    public Robotrally(String boardString, Robot robot){
        this.board = new Board(boardString);
        this.robot = robot;
    }

    public Robotrally(Board board, Robot robot){
        this.board = board;
        this.robot = robot;
    }

    public Robot getRobot(){
        return this.robot;
    }

    public Board getBoard(){
        return this.board;
    }

    public void playRound(){
        ICard card = robot.getCard();
        card.doCardAction(robot, board);
        
        Square robotPosition = board.getSquare(robot.getXCoordinate(), robot.getYCoordinate());
        robotPosition.doSquareAction(robot, board);
    }

    public void program(int cardnr){
        this.robot.program(cardnr);
    }

}