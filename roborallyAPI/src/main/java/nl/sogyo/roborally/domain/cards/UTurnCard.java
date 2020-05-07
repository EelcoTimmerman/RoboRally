package nl.sogyo.roborally.domain.cards;

import nl.sogyo.roborally.domain.robots.Robot;
import nl.sogyo.roborally.domain.squares.Board;

public class UTurnCard implements ICard{
    private String name = "UTurnCard";
    private int speed;

    public UTurnCard(){}

    public UTurnCard(int speed){
        this.speed = speed;
    }
    
    public void doCardAction(Robot robot, Board board){      
        robot.turnRight();
        robot.turnRight();
    }

    @Override
    public int getSpeed(){
        return this.speed;
    }

    @Override
    public String getName(){
        return this.name;
    }
}