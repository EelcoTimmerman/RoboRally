package nl.sogyo.roborally.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import nl.sogyo.roborally.domain.Direction;
import nl.sogyo.roborally.domain.robots.Robot;
import nl.sogyo.roborally.domain.rulebooks.RulebookRobots;
import nl.sogyo.roborally.domain.squares.Board;

@Path("/program")
public class RobotrallyProgram{

	/**
	 * @param request
	 * @return
	 */
	@PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{cardnr}")
	public Response program(
            @PathParam("cardnr") int cardnr,
			@Context HttpServletRequest request){
		
        System.out.println("programming card: " + cardnr);
        HttpSession session = request.getSession();
		Board board = (Board) session.getAttribute("Board");
        Robot robot = (Robot) session.getAttribute("Robot");
        robot.program(cardnr);

        RulebookRobots rulebookRobots = new RulebookRobots(board, robot);
        rulebookRobots.playRound();
        
		session.setAttribute("Board", board);
		session.setAttribute("Robot", robot);
		
		String stringoutput = new JSONResultProcessor().createJSONResponse(board, robot);	
		return Response.status(200).entity(stringoutput).build();
	}
	
}