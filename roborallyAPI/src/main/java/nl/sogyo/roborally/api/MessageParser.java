package nl.sogyo.roborally.api;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

public class MessageParser{
    public static int[] parseMessage(String message){
        JSONParser parser = new JSONParser();
        ArrayList<Integer> result = new ArrayList<>();
        try{
            JSONArray array = (JSONArray) parser.parse(message);
            for(Object o : array){
                Integer step = (int) (long) (Long) o;
                result.add(step);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return result.stream().mapToInt(i->i).toArray();
    }
}