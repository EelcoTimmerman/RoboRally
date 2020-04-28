package nl.sogyo.roborally.api;

import java.io.IOException;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import nl.sogyo.roborally.domain.Direction;
import nl.sogyo.roborally.domain.robots.Robot;
import nl.sogyo.roborally.domain.rulebooks.RulebookRobots;
import nl.sogyo.roborally.domain.squares.Board;

@ServerEndpoint(value = "/websocket")
public class RobotrallyWebsocket{
    final String TESTBOARD4X4 = "CH-X*ES-X*ES-N*ES-X*||*ES-W*ES-x*ES-x*ES-x*||*ES-x*ES-x*ES-x*ES-E*||*ES-x*ES-S*ES-x*ES-x";
    Board board = new Board(TESTBOARD4X4);
    Robot robot = new Robot(2,3, Direction.EAST);
    
    @OnOpen
    public void onOpen(Session session)throws IOException{
        System.out.println("WebSocket opened: " + session.getId());
    }

    @OnMessage
    public void onMessage(String message, Session session)throws IOException{
        System.out.println("Message recieved: " + message);
        if(message.equals("initialize")){
            String stringoutput = new JSONResultProcessor().createJSONResponse(board, robot);
            session.getBasicRemote().sendText(stringoutput);
        }
        else{
            int cardnr = Integer.parseInt(message);

            robot.program(cardnr);    
            RulebookRobots rulebookRobots = new RulebookRobots(board, robot);
            rulebookRobots.playRound();

            String stringoutput = new JSONResultProcessor().createJSONResponse(board, robot);
            session.getBasicRemote().sendText(stringoutput);
        }
    }

    @OnClose
    public void onClose(CloseReason reason, Session session){
        System.out.println("Closing a websocket due to " + reason.getReasonPhrase());
    }

}