package libertymq.messaging;

import jakarta.ejb.MessageDriven;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;

@MessageDriven(name = "SampleListenerMDB")
public class SampleListener implements MessageListener {

  public void onMessage(Message message) {
    try {
      System.out.println("MDB received: " + message.getBody(String.class));
    } catch (JMSException e) {
      throw new RuntimeException(e);
    }
  }

}
