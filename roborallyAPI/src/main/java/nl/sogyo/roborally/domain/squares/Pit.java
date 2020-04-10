package nl.sogyo.roborally.domain.squares;

public class Pit extends Square {

    public Square getDestination() {
        throw new RuntimeException("Robots should never stand on a pit.");
    }

}