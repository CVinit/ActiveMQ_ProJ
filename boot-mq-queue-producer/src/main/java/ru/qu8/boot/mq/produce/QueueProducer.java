package ru.qu8.boot.mq.produce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import java.util.UUID;

@Component
public class QueueProducer {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    private Queue queue;

     public void produceMeg(){
         jmsMessagingTemplate.convertAndSend(queue,"springboot msg:"+ UUID.randomUUID().toString().substring(0,5));
     }
     @Scheduled(fixedDelay = 3000)
     public void produceMsgScheduled(){
         jmsMessagingTemplate.convertAndSend(queue,"scheduled msg:"+UUID.randomUUID().toString().substring(0,5).toUpperCase());
         System.out.println("scheduled msg: sent");
     }}
