package com.kreditcart.userservice.Clients;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducerClient {
    private KafkaTemplate<String, String> kafkaTemplate;
    public KafkaProducerClient(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    public void sendMessage(String topic, String message) {
        System.out.println("SendMessage------" + topic);
        this.kafkaTemplate.send(topic, message);
    }

}
