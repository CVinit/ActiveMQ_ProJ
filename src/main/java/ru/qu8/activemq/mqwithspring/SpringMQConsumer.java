package ru.qu8.activemq.mqwithspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class SpringMQConsumer {
    @Autowired
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        SpringMQConsumer springMQConsumer = (SpringMQConsumer) context.getBean("springMQConsumer");
        String message = (String) springMQConsumer.jmsTemplate.receiveAndConvert();
        System.out.println("收到消息："+message);
    }
}
