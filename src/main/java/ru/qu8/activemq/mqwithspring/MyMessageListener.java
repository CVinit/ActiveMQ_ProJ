package ru.qu8.activemq.mqwithspring;

import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
@Component
public class MyMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        if (message != null && message instanceof TextMessage){
            TextMessage textMessage = (TextMessage) message;
            try {
                System.out.println("收到消息：" + textMessage.getText());
            } catch (JMSException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
