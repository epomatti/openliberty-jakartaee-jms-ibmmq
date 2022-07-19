package libertymq.rest;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

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