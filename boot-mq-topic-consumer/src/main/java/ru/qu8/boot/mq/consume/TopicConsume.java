package ru.qu8.boot.mq.consume;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;

@Component
public class TopicConsume {
    @JmsListener(destination = "${mytopic}")
    public void receiveTopic(TextMessage textMessage) throws JMSException {
        System.out.println("Got Topic"+textMessage.getText());
    }
}
