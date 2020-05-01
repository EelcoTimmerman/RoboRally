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
        Robot robot = new Robot(2,2);
        robots.put(session, robot);
        roborally.addRobot(robot);

        players.add(session);
        System.out.println("WebSocket opened: " + session.getId());
        System.out.println("Playercount: " + players.size());
    }

    @OnMessage
    public void onMessage(String message, Session session)throws IOException{
        System.out.println("Message recieved: " + message);
        if(message.equals("initialize")){
            String stringoutput = new JSONResultProcessor().createJSONResponse(roborally);
            session.getBasicRemote().sendText(stringoutput);
        }
        else{
            int cardnr = Integer.parseInt(message);
            Robot robot = robots.get(session);
            robot.program(cardnr);
            roborally.playRoundIfAllRobotsReady();

            String stringoutput = new JSONResultProcessor().createJSONResponse(roborally);
            for(Session player : players){
                player.getBasicRemote().sendText(stringoutput);
            }
        }
    }

    @OnClose
    public void onClose(CloseReason reason, Session session){
        Robot robotToRemove = robots.get(session);
        roborally.removeRobot(robotToRemove);
        robots.remove(session);
        players.remove(session);
        System.out.println("Closing a websocket due to " + reason.getReasonPhrase());
    }

}