package nl.sogyo.roborally.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
import nl.sogyo.roborally.domain.squares.BoardFactory;

@ServerEndpoint(value = "/websocket")
public class RoborallyWebsocket{
    private static final Roborally roborally = new Roborally(BoardFactory.createSmallCompleteBoard());
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
            sendBoardInformation(session);
        }
        else if(message.equals("switchpower")){
            Robot robot = robots.get(session);
            robot.turnOnOrOff();
            updatePlayerPowerStatus(session);
        }
        else{            
            int[] cardnrs = MessageParser.parseMessage(message);
            Robot robot = robots.get(session);
            robot.programFromHand(cardnrs);
            roborally.playAllRegistersIfRobotsReady();
            if(roborally.getWinner() != null){
                String gameover = new JSONResultProcessor().createGameOverResponse(roborally);
                session.getBasicRemote().sendText(gameover);
            }
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
        for(Session player : players){
            updateRobots(player);
            updatePlayerPowerStatus(player);
            updatePlayerHand(player);
        }
    }

    private void updatePlayerPowerStatus(Session session)throws IOException{
        Robot robot = robots.get(session);
        String powerstatusresponse = new JSONResultProcessor().createPowerstatusResponse(robot);
        session.getBasicRemote().sendText(powerstatusresponse);
    }

    private void updatePlayerHand(Session session)throws IOException{
        String cards = new JSONResultProcessor().createCardsResponse(roborally, robots.get(session));
        session.getBasicRemote().sendText(cards);
    }

    private void updateRobots(Session session)throws IOException{
        String robotresponse = new JSONResultProcessor().createRobotsResponse(roborally);
        session.getBasicRemote().sendText(robotresponse);
    }

    private void sendBoardInformation(Session session)throws IOException{
        String board = new JSONResultProcessor().createBoardResponse(roborally);
        session.getBasicRemote().sendText(board);
        String lasers = new JSONResultProcessor().createLasersResponse(roborally);
        session.getBasicRemote().sendText(lasers);
        updatePlayerHand(session);
    }

}