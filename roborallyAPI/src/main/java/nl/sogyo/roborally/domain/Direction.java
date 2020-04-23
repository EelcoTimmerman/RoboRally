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

    public Direction getNext(){
        Direction result = null;
        switch(this){
            case NORTH: result = EAST;
                        break;                        
            case EAST: result = SOUTH;
                        break;
            case SOUTH: result = WEST;
                        break;                        
            case WEST: result = NORTH;
                        break;
        }
        return result;
    }
}