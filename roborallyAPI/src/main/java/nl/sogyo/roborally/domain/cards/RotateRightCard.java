package nl.sogyo.roborally.domain.cards;

import nl.sogyo.roborally.domain.robots.Robot;
import nl.sogyo.roborally.domain.squares.Board;

public class RotateRightCard implements ICard{
    private String name = "RotateRightCard";
    private int speed;

    public RotateRightCard(){}

    public RotateRightCard(int speed){
        this.speed = speed;        
    }

    public void doCardAction(Robot robot, Board board){
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