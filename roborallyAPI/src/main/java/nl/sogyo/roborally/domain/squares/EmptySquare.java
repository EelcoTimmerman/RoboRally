package nl.sogyo.roborally.domain.squares;

import java.util.List;

import nl.sogyo.roborally.domain.robots.Robot;

public class EmptySquare extends Square{

   public EmptySquare(){
   }

   public EmptySquare(String walls){
      super(walls);
   }

   @Override
   public String getType(){
      return "EmptySquare";
   }

   @Override
   public void doSquareAction(Robot robot, Board board, List<Robot> robots){
   }
}