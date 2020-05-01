package nl.sogyo.roborally.domain.cards;

import nl.sogyo.roborally.domain.robots.Robot;
import nl.sogyo.roborally.domain.squares.Board;

public interface ICard{

    public void doCardAction(Robot robot, Board board);

    public int getSpeed();
}