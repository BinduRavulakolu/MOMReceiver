package receiver;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Receiver {

	public static void main(String[] args) {
		ConnectionFactory connectionFactory = null;
		Queue queue = null;
		try {
			InitialContext initialContext = new InitialContext();
			queue = (Queue) initialContext.lookup("jms/P2PQueue");
			connectionFactory = (ConnectionFactory) initialContext.lookup("jms/__defaultConnectionFactory");
			/*
			 * connection= connectionFactory.createConnection(); Session session=
			 * connection.createSession( false, Session.AUTO_ACKNOWLEDGE);
			 * connection.start(); MessageConsumer messageConsumer
			 * =session.createConsumer(queue); TextMessage textmessage=(TextMessage)
			 * messageConsumer.receive();
			 * 
			 * 
			 * String body=textmessage.getText();
			 * 
			 * System.out.println(body);
			 */

		} catch (NamingException e) {
			e.printStackTrace();
		} /*
			 * catch(JMSException e){ e.printStackTrace(); }finally {
			 * if(connection!=null)try { connection.close(); }catch (JMSException e) {
			 * e.printStackTrace(); } }
			 */

		try (JMSContext context = connectionFactory.createContext()) {

			JMSConsumer consumer = context.createConsumer(queue);
//			String body = consumer.receiveBody(String.class);
//			System.out.println(body);
			TextMessage message;
			while ((message = (TextMessage) consumer.receive()) != null) {
				System.out.println(message.getText());
			}

		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
