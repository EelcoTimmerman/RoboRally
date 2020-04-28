package nl.sogyo.roborally.domain;


public enum Direction{
    NORTH("North"),
    EAST("East"),
    SOUTH("South"),
    WEST("West");

    private String name;

    private Direction(String name){
        this.name = name;
    }

    public String toString(){
        return this.name;
    }

    public Direction getRight(){
        int ordinal = this.ordinal();
        Direction[] directions = Direction.values();
        int reverseOrdinal = (ordinal + 1) % 4;
        return directions[reverseOrdinal];
    }

    public Direction getLeft(){
        int ordinal = this.ordinal();
        Direction[] directions = Direction.values();
        int reverseOrdinal = (ordinal + 3) % 4;
        return directions[reverseOrdinal];
    }

    public Direction getReverse(){
        int ordinal = this.ordinal();
        Direction[] directions = Direction.values();
        int reverseOrdinal = (ordinal + 2) % 4;
        return directions[reverseOrdinal];
    }
}