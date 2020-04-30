package nl.sogyo.roborally.domain;

import nl.sogyo.roborally.domain.robots.Robot;
import nl.sogyo.roborally.domain.rulebooks.RulebookRobots;
import nl.sogyo.roborally.domain.squares.Board;

public class Robotrally {
    private RulebookRobots rulebookrobots;
    private Board board;
    private Robot robot;

    public Robotrally(){
        this.board = new Board("CH-X*ES-X*ES-N*ES-X*||*ES-W*ES-x*ES-x*ES-x*||*ES-x*ES-x*ES-x*ES-E*||*ES-x*ES-S*ES-x*ES-x");
        this.robot = new Robot(2,3, Direction.EAST);
        this.rulebookrobots = new RulebookRobots(board, robot);
    }
    public Board getBoard() {
        return board;
    }
    public Robot getRobot() {
        return robot;
    }
    public RulebookRobots getRulebookrobots() {
        return rulebookrobots;
    }

    public void program(int cardnr){
        this.robot.program(cardnr);
    }

    public void playRound(){
        this.rulebookrobots.playRound();
    }

}