package nl.sogyo.roborally.api;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import nl.sogyo.roborally.domain.*;
import nl.sogyo.roborally.domain.cards.ICard;
import nl.sogyo.roborally.domain.robots.Robot;
import nl.sogyo.roborally.domain.squares.*;

@SuppressWarnings("unchecked")
public class JSONResultProcessor {
 
    public String createBoardResponse(Roborally roborally){
        Board board = roborally.getBoard();
        JSONArray squares = createJSONBoard(board);
        JSONObject response = new JSONObject();
        response.put("messagetype", "boardstate");
        response.put("body", squares);
        return response.toJSONString();
    }

    public String createCardsResponse(Roborally roborally, Robot robot){
        List<ICard> drawNewHand = roborally.getHandOfCards(robot);
        JSONArray cards = createJSONCards(drawNewHand);
        JSONObject response = new JSONObject();
        response.put("messagetype", "drawncards");
        response.put("body", cards);
        return response.toJSONString();
    }

    public String createRobotsResponse(Roborally roborally){
        List<Robot> robotList= roborally.getRobots();
        JSONArray robots = new JSONArray();
        for(Robot robot : robotList){
            robots.add(this.createJSONRobot(robot));
        }

        JSONObject response = new JSONObject();
        response.put("messagetype", "robots");
        response.put("body", robots);
        return response.toJSONString();
    }
 
    private JSONArray createJSONCards(List<ICard> cards){
        JSONArray jsonCards = new JSONArray();
        for(ICard card : cards){
            jsonCards.add(card);
        }
        return jsonCards;
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
        result.put("colour", robot.getColour());
        result.put("xCoordinate", robot.getXCoordinate());
        result.put("yCoordinate", robot.getYCoordinate());
        result.put("ready", robot.isReady());
        return result;
    }
}