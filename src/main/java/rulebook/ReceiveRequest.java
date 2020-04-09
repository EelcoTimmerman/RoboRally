package rulebook;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.simple.JSONObject;

@Path("/addCards")
public class ReceiveRequest {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addItem(@Context HttpServletRequest request, RequestData itemData) throws Exception {
        String i=itemData.getItem();
		JSONObject output = new JSONObject();
		output.put("color", "Red");         
		String stringoutput = output.toJSONString();		
		return Response.status(200).entity(stringoutput).build();
	}


}
