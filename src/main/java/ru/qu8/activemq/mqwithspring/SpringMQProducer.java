package ru.qu8.activemq.mqwithspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class SpringMQProducer {
    @Autowired
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        SpringMQProducer springMQProducer = (SpringMQProducer) context.getBean("springMQProducer");
        springMQProducer.jmsTemplate.send((session)-> session.createTextMessage("spring整合MQ messageListener"));
        System.out.println("发送成功");
    }
}
