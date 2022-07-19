package libertymq.messaging;

import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.jms.JMSConnectionFactory;
import jakarta.jms.JMSContext;
import jakarta.jms.Queue;

@Stateless
public class SampleProducer {

  @Inject
  @JMSConnectionFactory("jms/wmqCF")
  JMSContext context;

  @Resource(lookup = "jms/queue1")
  Queue queue;

  public void sendMessage(String message) throws Exception {
    context.createProducer().send(queue, message);
    System.out.println("Message enqueued.");
  }

}
