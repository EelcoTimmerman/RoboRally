package nl.sogyo.roborally.domain.cards;

import nl.sogyo.roborally.domain.robots.Robot;
import nl.sogyo.roborally.domain.squares.Board;

public class RotateLeftCard implements ICard{
    private String name = "RotateLeftCard";
    private int speed;

    public RotateLeftCard(){}

    public RotateLeftCard(int speed){
        this.speed = speed;
    }

    public void doCardAction(Robot robot, Board board){    
        robot.turnLeft();    
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