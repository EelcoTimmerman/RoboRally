package nl.sogyo.roborally.api;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import nl.sogyo.roborally.domain.*;
import nl.sogyo.roborally.domain.robots.Robot;
import nl.sogyo.roborally.domain.squares.*;

@SuppressWarnings("unchecked")
public class JSONResultProcessor {
 
    public String createJSONResponse(Board board, Robot robot){

        JSONArray squares = createJSONBoard(board);
        int xCoordinate = robot.getXCoordinate();
        int yCoordinate = robot.getYCoordinate();
        ((JSONObject) ((JSONArray) squares.get(yCoordinate)).get(xCoordinate)).put("robot", this.createJSONRobot(robot));
        return squares.toJSONString();
    }
 
    private JSONArray createJSONBoard(Board board){
        JSONArray jsonSquares = new JSONArray();
        ArrayList<ArrayList<Square>> boardSquares = board.getBoard();

        for(ArrayList<Square> row : boardSquares){
            JSONArray jsonRow = new JSONArray();
            for(Square square : row){
                jsonRow.add(this.createJSONSquare(square));
            }
            jsonSquares.add(jsonRow);
        }
        return jsonSquares;
    }

    private JSONObject createJSONSquare(Square square){
        JSONObject result = new JSONObject();
        result.put("type", square.getType());
        result.put("northwall", square.hasWallAt(Direction.NORTH));
        result.put("eastwall", square.hasWallAt(Direction.EAST));
        result.put("southwall", square.hasWallAt(Direction.SOUTH));
        result.put("westwall", square.hasWallAt(Direction.WEST));
        result.put("robot", null);
        return result;
    }

    private JSONObject createJSONRobot(Robot robot){
        JSONObject result = new JSONObject();
        result.put("name", robot.getName());
        result.put("orientation", robot.getOrientation().toString());
        return result;
    }
}