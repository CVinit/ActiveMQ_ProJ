package ru.qu8.activemq.schedule;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQMessageProducer;
import org.apache.activemq.AsyncCallback;
import org.apache.activemq.ScheduledMessage;

import javax.jms.*;
import java.util.UUID;

public class ScheduleProducer {
    public static final String ACTIVEMQ_URL = "tcp://150.230.45.169:61618";
    public static final String QUEUE_NAME = "queue-delay";
    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Queue queue = session.createQueue(QUEUE_NAME);

        MessageProducer messageProducer = session.createProducer(queue);
        messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);

        long delay = 3*1000;
        long period = 4*1000;
        int repeate = 5;

        TextMessage textMessage = null;
        for (int i = 0;i < 3;i++){
            textMessage = session.createTextMessage("delay and scheuled msg------" + i);

            textMessage.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY,delay);
            textMessage.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_PERIOD,period);
            textMessage.setIntProperty(ScheduledMessage.AMQ_SCHEDULED_REPEAT,repeate);

            messageProducer.send(textMessage);
            System.out.println("msg sent:"+textMessage.getText());

        }

        messageProducer.close();
        session.close();
        connection.close();

        System.out.println("消息发布完成");
    }
}
