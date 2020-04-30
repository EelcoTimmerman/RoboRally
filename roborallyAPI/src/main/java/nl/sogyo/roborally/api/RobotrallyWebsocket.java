package nl.sogyo.roborally.api;

import java.io.IOException;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import nl.sogyo.roborally.domain.Robotrally;



@ServerEndpoint(value = "/websocket")
public class RobotrallyWebsocket{
    Robotrally robotrally = new Robotrally();
    
    @OnOpen
    public void onOpen(Session session)throws IOException{
        System.out.println("WebSocket opened: " + session.getId());
    }

    @OnMessage
    public void onMessage(String message, Session session)throws IOException{
        System.out.println("Message recieved: " + message);
        if(message.equals("initialize")){
            String stringoutput = new JSONResultProcessor().createJSONResponse(robotrally);
            session.getBasicRemote().sendText(stringoutput);
        }
        else{
            int cardnr = Integer.parseInt(message);

            robotrally.program(cardnr);
            robotrally.playRound();

            String stringoutput = new JSONResultProcessor().createJSONResponse(robotrally);
            session.getBasicRemote().sendText(stringoutput);
        }
    }

    @OnClose
    public void onClose(CloseReason reason, Session session){
        System.out.println("Closing a websocket due to " + reason.getReasonPhrase());
    }

}