package nl.sogyo.roborally.domain.cards;

import java.util.List;

import nl.sogyo.roborally.domain.robots.Robot;
import nl.sogyo.roborally.domain.squares.Board;

public class RotateLeftCard extends Card{

    public RotateLeftCard() {
        super();
    }

    public RotateLeftCard(int speed) {
        super(speed);
    }

    public void doCardAction(Robot robot, Board board, List<Robot> robots) {
        robot.turnLeft();    
    }

    public String getName(){
        return "RotateLeftCard";
    }
}