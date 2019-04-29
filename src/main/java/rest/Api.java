package rest;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import messaging.SampleProducer;

@Path("/enqueue")
@Stateless
public class Api {

	@Inject
	SampleProducer producer;

	@GET
	public Response enqueue() throws Exception {
		producer.sendMessage();
		return Response.ok().entity("Message enqueued").build();
	}

}
