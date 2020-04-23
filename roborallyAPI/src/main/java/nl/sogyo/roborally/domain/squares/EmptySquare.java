package nl.sogyo.roborally.domain.squares;

public class EmptySquare extends Square{

   public EmptySquare(){

   }

   @Override
   public String getType() {
      return "EmptySquare";
   }
}