package libertymq;

import javax.jms.Destination;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.Message;

import com.ibm.msg.client.jms.JmsConnectionFactory;
import com.ibm.msg.client.jms.JmsFactoryFactory;
import com.ibm.msg.client.wmq.WMQConstants;

import org.junit.jupiter.api.Test;

public class ClientTest {

	@Test
	public void dequeue() throws Exception {

		JMSContext context = null;
		Destination destination = null;
		JMSConsumer consumer = null;

		JmsFactoryFactory ff = JmsFactoryFactory.getInstance(WMQConstants.WMQ_PROVIDER);
		JmsConnectionFactory cf = ff.createConnectionFactory();

		// Set the properties
		cf.setStringProperty(WMQConstants.WMQ_HOST_NAME, "localhost");
		cf.setIntProperty(WMQConstants.WMQ_PORT, 1414);
		cf.setStringProperty(WMQConstants.WMQ_CHANNEL, "DEV.APP.SVRCONN");
		cf.setIntProperty(WMQConstants.WMQ_CONNECTION_MODE, WMQConstants.WMQ_CM_CLIENT);
		cf.setStringProperty(WMQConstants.WMQ_QUEUE_MANAGER, "QM1");
		cf.setStringProperty(WMQConstants.WMQ_APPLICATIONNAME, "Open Liberty MQ");
		cf.setBooleanProperty(WMQConstants.USER_AUTHENTICATION_MQCSP, true);
		cf.setStringProperty(WMQConstants.USERID, "app");
		cf.setStringProperty(WMQConstants.PASSWORD, "passw0rd");

		// Create JMS objects
		context = cf.createContext();
		destination = context.createQueue("queue:///DEV.QUEUE.1");

		consumer = context.createConsumer(destination);
		String body = consumer.receiveBody(String.class);
		// String body = consumer.receiveBodyNoWait(String.class);

		System.out.println("Message body: %s".formatted(body));

	}

}

// MQQueueManager QMgr = new MQQueueManager("QM1"); //<-- qManager is a String
// with the QMgr name

// int openOptions = MQC.MQOO_FAIL_IF_QUIESCING | MQC.MQOO_INPUT_SHARED |
// MQC.MQOO_BROWSE;

// MQQueue queue = QMgr.accessQueue("DEV.QUEUE.1", openOptions);

// MQMessage theMessage = new MQMessage();
// MQGetMessageOptions gmo = new MQGetMessageOptions();
// gmo.options=MQC.MQGMO_WAIT | MQC.MQGMO_BROWSE_FIRST;
// gmo.matchOptions=MQC.MQMO_NONE;
// gmo.waitInterval=5000;

// boolean thereAreMessages=true;
// while(thereAreMessages){
// try{
// //read the message
// gmo.options = CMQC.MQGMO_MSG_UNDER_CURSOR;
// queue.get(theMessage,gmo);
// //print the text
// String msgText = theMessage.readString(theMessage.getMessageLength());
// System.out.println("msg text: "+msgText);

// // <--- Solution code Here

// //move cursor to the next message
// gmo.options = MQC.MQGMO_WAIT | MQC.MQGMO_BROWSE_NEXT;

// }catch(MQException e){

// if(e.reasonCode == e.MQRC_NO_MSG_AVAILABLE) {
// System.out.println("no more message available or retrived");
// }

// thereAreMessages=false;
// } catch (IOException e) {
// System.out.println("ERROR: "+e.getMessage());
// }
// }