package ru.qu8.activemq.queue;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQMessageProducer;
import org.apache.activemq.AsyncCallback;

import javax.jms.*;
import java.util.UUID;

public class JmsProducer {
    public static final String ACTIVEMQ_URL = "nio://150.230.45.169:61618";
//    public static final String ACTIVEMQ_URL = "failover:(tcp://150.230.38.161:61616,tcp://150.230.45.169:61616,tcp://150.230.35.86:61616)";
    public static final String QUEUE_NAME = "queue-asnyc";
    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        activeMQConnectionFactory.setUseAsyncSend(true);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Queue queue = session.createQueue(QUEUE_NAME);

        ActiveMQMessageProducer activeMQMessageProducer = (ActiveMQMessageProducer) session.createProducer(queue);
        activeMQMessageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);
        TextMessage textMessage = null;
        for (int i = 0;i < 3;i++){
            textMessage = session.createTextMessage("cluster msg------" + i);
            textMessage.setJMSMessageID(UUID.randomUUID().toString()+"Async");
            String msgID = textMessage.getJMSMessageID();
            activeMQMessageProducer.send(textMessage, new AsyncCallback() {
                @Override
                public void onSuccess() {
                    System.out.println(msgID + "has been successfully sent!");
                }

                @Override
                public void onException(JMSException e) {
                    System.out.println(msgID + "has not been successfully sent!");

                }
            });
        }

        activeMQMessageProducer.close();
        session.close();
        connection.close();

        System.out.println("消息发布完成");
    }
}
