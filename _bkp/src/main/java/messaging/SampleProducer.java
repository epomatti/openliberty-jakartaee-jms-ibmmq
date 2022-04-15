package messaging;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;

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
