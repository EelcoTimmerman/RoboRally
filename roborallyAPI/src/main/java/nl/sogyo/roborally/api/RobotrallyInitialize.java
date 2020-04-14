/**
 * 
 */
package nl.sogyo.roborally.api;

import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpSession;
// import javax.ws.rs.Consumes;
// import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.simple.JSONObject;

/**
 * @author rvvugt
 *
 */
@Path("players")
public class RobotrallyInitialize {

	/**
	 * @param request
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response initialize(
			@Context HttpServletRequest request) {

		JSONObject output = new JSONObject();
		output.put("testcolor", "testRed");         
		String stringoutput = output.toJSONString();		
		return Response.status(200).entity(stringoutput).build();
	}
	
}
