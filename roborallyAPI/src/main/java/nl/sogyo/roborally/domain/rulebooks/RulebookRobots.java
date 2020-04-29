package nl.sogyo.roborally.domain.rulebooks;

import nl.sogyo.roborally.domain.cards.ICard;
import nl.sogyo.roborally.domain.robots.Robot;
import nl.sogyo.roborally.domain.squares.*;

public class RulebookRobots{

    Robot robot;
    Board board;

    public RulebookRobots(Robot robot){
        this.robot = robot;
    }

    public RulebookRobots(String boardString){
        this.board = new Board(boardString);
    }

    public RulebookRobots(String boardString, Robot robot){
        this.board = new Board(boardString);
        this.robot = robot;
    }

    public RulebookRobots(Board board, Robot robot){
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
    }

}