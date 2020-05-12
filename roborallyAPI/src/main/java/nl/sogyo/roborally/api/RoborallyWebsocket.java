package nl.sogyo.roborally.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import nl.sogyo.roborally.domain.Roborally;
import nl.sogyo.roborally.domain.robots.Robot;

@ServerEndpoint(value = "/websocket")
public class RoborallyWebsocket{
    private static final Roborally roborally = new Roborally();
    private static final List<Session> players = new ArrayList<>();
    private static final Map<Session, Robot> robots = new HashMap<>();
    
    @OnOpen
    public void onOpen(Session session)throws IOException{
        System.out.println("WebSocket opened: " + session.getId());
    }

    @OnMessage
    public void onMessage(String message, Session session)throws IOException{
        System.out.println("Message received: " + message);
        if(message.contains("initialize")){
            String name = message.split(" ")[1];
            Robot robot = new Robot(name, robots.size());
            robots.put(session, robot);
            roborally.addRobot(robot);
            players.add(session);
            String board = new JSONResultProcessor().createBoardResponse(roborally);
            session.getBasicRemote().sendText(board);
            String cards = new JSONResultProcessor().createInitCardsResponse(roborally, robots.get(session));
            session.getBasicRemote().sendText(cards);
        }
        else{
            int cardnr = Integer.parseInt(message);
            Robot robot = robots.get(session);
            robot.program(cardnr);
            roborally.playRoundIfAllRobotsReady();
            String cards = new JSONResultProcessor().createCardsResponse(roborally, robots.get(session));
            session.getBasicRemote().sendText(cards);
            roborally.DiscardAllCards();
        }
        updateAllPlayers();

    }

    @OnClose
    public void onClose(CloseReason reason, Session session)throws IOException{
        Robot robotToRemove = robots.get(session);
        roborally.removeRobot(robotToRemove);
        robots.remove(session);
        players.remove(session);
        System.out.println("Closing a websocket due to " + reason.getReasonPhrase());
        updateAllPlayers();
    }

    private void updateAllPlayers()throws IOException{

        String robots = new JSONResultProcessor().createRobotsResponse(roborally);
        for(Session player : players){
            player.getBasicRemote().sendText(robots);
        }
    }

}