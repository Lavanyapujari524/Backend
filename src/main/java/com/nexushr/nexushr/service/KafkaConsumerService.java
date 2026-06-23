package com.nexushr.nexushr.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

//@Service
public class KafkaConsumerService {

    @KafkaListener(
            topics = "employee-events",
            groupId = "nexushr-group"
    )
    public void consume(String message) {

        System.out.println(
                "Message Received From Kafka: "
                        + message
        );
    }
}