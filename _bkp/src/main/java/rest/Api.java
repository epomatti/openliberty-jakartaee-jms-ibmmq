package rest;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import messaging.SampleProducer;

@Path("/enqueue")
@Stateless
public class Api {

	@Inject
	SampleProducer producer;

	// Should be POST, using GET for the simplicity of the example
	@GET
	public Response enqueue(@QueryParam("msg") String message) throws Exception {
		producer.sendMessage(message);
		return Response.ok().entity("Message enqueued").build();
	}

}
