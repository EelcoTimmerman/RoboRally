package nl.sogyo.roborally.domain.cards;

import java.util.List;

import nl.sogyo.roborally.domain.robots.Robot;
import nl.sogyo.roborally.domain.squares.Board;

public class RotateRightCard extends Card{

    public RotateRightCard() {
        super();
    }

    public RotateRightCard(int speed) {
        super(speed);
    }

    public void doCardAction(Robot robot, Board board, List<Robot> robots) {
        robot.turnRight();
    }

    public String getName(){
        return "RotateRightCard";
    }
}