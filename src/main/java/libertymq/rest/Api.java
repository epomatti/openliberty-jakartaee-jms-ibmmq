package libertymq.rest;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/enqueue")
@Stateless
public class Api {

  // @Inject
  // SampleProducer producer;

  @Inject
  @JMSConnectionFactory("jms/wmqCF")
  JMSContext context;

  // Should be POST, using GET for the simplicity of the example
  @GET
  public Response enqueue(@QueryParam("msg") String message) throws Exception {
    // producer.sendMessage(message);
    context.createProducer();
    return Response.ok().entity("Message enqueued").build();
  }

}