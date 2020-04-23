package nl.sogyo.roborally.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
// import javax.servlet.http.HttpSession;
// import javax.ws.rs.Consumes;
// import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import nl.sogyo.roborally.domain.Direction;
import nl.sogyo.roborally.domain.robots.Robot;
import nl.sogyo.roborally.domain.squares.Board;

@Path("initialize")
public class RobotrallyInitialize{

	/**
	 * @param request
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response initialize(
			@Context HttpServletRequest request){
		
		System.out.println("Request received for initial gamestate");
		final String TESTBOARD4X4 = "CH-X*ES-X*ES-N*ES-X*||*ES-W*ES-x*ES-x*ES-x*||*ES-x*ES-x*ES-x*ES-E*||*ES-x*ES-S*ES-x*ES-x";
		Board board = new Board(TESTBOARD4X4);
		Robot robot = new Robot(2,3, Direction.EAST);

		HttpSession session = request.getSession();
		session.setAttribute("Board", board);
		session.setAttribute("Robot", robot);
		
		String stringoutput = new JSONResultProcessor().createJSONResponse(board, robot);	
		return Response.status(200).entity(stringoutput).build();
	}
	
}
