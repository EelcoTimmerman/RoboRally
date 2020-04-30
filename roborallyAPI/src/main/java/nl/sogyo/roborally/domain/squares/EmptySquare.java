package nl.sogyo.roborally.domain.squares;

import nl.sogyo.roborally.domain.robots.Robot;

public class EmptySquare extends Square{

   public EmptySquare(){

   }

   @Override
   public String getType(){
      return "EmptySquare";
   }

   @Override
   public void doSquareAction(Robot robot, Board board){
   }
}