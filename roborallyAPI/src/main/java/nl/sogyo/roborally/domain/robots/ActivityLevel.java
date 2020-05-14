package nl.sogyo.roborally.domain.robots;

public enum ActivityLevel {
    ACTIVE("Active"),
    POWERINGDOWN("Poweringdown"),
    INACTIVE("Inactive");

    private String name;
    private ActivityLevel(String name){
        this.name = name;
    }

    public String toString(){
        return this.name;
    }
    
}