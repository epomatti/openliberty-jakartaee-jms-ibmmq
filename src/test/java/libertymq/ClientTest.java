package libertymq;

import javax.jms.Destination;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.TextMessage;

import com.ibm.msg.client.jms.JmsConnectionFactory;
import com.ibm.msg.client.jms.JmsFactoryFactory;
import com.ibm.msg.client.wmq.WMQConstants;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class ClientTest {

	@Test
	@Disabled
	public void jmsPutGet() throws Exception {

		JmsFactoryFactory ff = JmsFactoryFactory.getInstance(WMQConstants.WMQ_PROVIDER);
		JmsConnectionFactory cf = ff.createConnectionFactory();

		cf.setStringProperty(WMQConstants.WMQ_HOST_NAME, "localhost");
		cf.setIntProperty(WMQConstants.WMQ_PORT, 1414);
		cf.setStringProperty(WMQConstants.WMQ_CHANNEL, "DEV.APP.SVRCONN");
		cf.setIntProperty(WMQConstants.WMQ_CONNECTION_MODE, WMQConstants.WMQ_CM_CLIENT);
		cf.setStringProperty(WMQConstants.WMQ_QUEUE_MANAGER, "QM1");
		cf.setStringProperty(WMQConstants.WMQ_APPLICATIONNAME, "Open Liberty MQ");
		cf.setBooleanProperty(WMQConstants.USER_AUTHENTICATION_MQCSP, true);
		cf.setStringProperty(WMQConstants.USERID, "app");
		cf.setStringProperty(WMQConstants.PASSWORD, "passw0rd");

		try (JMSContext context = cf.createContext()) {
			Destination destination = context.createQueue("queue:///DEV.QUEUE.1");

			TextMessage message = context.createTextMessage("XXX MESSAGE");

			JMSProducer producer = context.createProducer();
			producer.send(destination, message);

			JMSConsumer consumer = context.createConsumer(destination);
			String body = consumer.receiveBody(String.class, 5000);

			System.out.println("Message body: %s".formatted(body));
		}
	}

}
