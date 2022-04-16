package libertymq.rest;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import libertymq.messaging.SampleProducer;

@Path("/enqueue")
@Stateless
public class Api {

  @Inject
  SampleProducer producer;
  
  @POST
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  @Produces(MediaType.TEXT_PLAIN)
  public Response enqueue(@FormParam("msg") String message) throws Exception {
    producer.sendMessage(message);
    return Response.ok().build();
  }

}