package nl.sogyo.roborally.domain.cards;

import java.util.List;

import nl.sogyo.roborally.domain.robots.Robot;
import nl.sogyo.roborally.domain.squares.Board;

public abstract class Card{

    public abstract void doCardAction(Robot robot, Board board, List<Robot> robots);

    public abstract int getSpeed();
}