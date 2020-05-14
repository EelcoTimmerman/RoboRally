package nl.sogyo.roborally.domain.cards;

import java.util.List;

import nl.sogyo.roborally.domain.robots.Robot;
import nl.sogyo.roborally.domain.squares.Board;

public class UTurnCard extends Card{
    
    public UTurnCard() {
        super();
    }

    public UTurnCard(int speed) {
        super(speed);
    }

    public void doCardAction(Robot robot, Board board, List<Robot> robots) {
        robot.turnRight();
        robot.turnRight();
    }

    @Override
    public int getSpeed(){
        return this.speed;
    }

    @Override
    public String getName(){
        return "UTurnCard";
    }
}