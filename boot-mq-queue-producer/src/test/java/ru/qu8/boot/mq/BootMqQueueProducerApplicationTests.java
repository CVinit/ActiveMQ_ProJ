package ru.qu8.boot.mq;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import ru.qu8.boot.mq.produce.QueueProducer;

import javax.annotation.Resource;

@SpringBootTest(classes = BootMqQueueProducerApplication.class)
@WebAppConfiguration
class BootMqQueueProducerApplicationTests {
    @Resource
    private QueueProducer queueProducer;
    @Test
    void contextLoads() {
    }
    @Test
    public void testSend(){
        queueProducer.produceMeg();
    }
    @Test
    public void testScheduledSend(){
        queueProducer.produceMsgScheduled();
    }

}
