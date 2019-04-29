package messaging;

import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(name = "SampleListenerMDB")
public class SampleListener implements MessageListener {

	public void onMessage(Message message) {
		try {
			System.out.println("Message: " + message.getBody(String.class));
		} catch (JMSException e) {
			throw new RuntimeException(e);
		}
	}

}
