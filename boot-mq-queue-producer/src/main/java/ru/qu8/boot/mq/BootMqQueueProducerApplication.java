package ru.qu8.boot.mq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BootMqQueueProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootMqQueueProducerApplication.class, args);
    }

}
