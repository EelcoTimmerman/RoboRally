package nl.sogyo.roborally.domain;

public enum Direction {
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
}