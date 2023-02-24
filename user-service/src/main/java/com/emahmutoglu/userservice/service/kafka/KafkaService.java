package com.emahmutoglu.userservice.service.kafka;

import com.emahmutoglu.userservice.data.dbmodel.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class KafkaService {
    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic, User user) {
        try {
            String jsonObj = objectMapper.writeValueAsString(user);
            ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic, jsonObj);
            kafkaTemplate.send(producerRecord);

            log.info("[Message send] Topic: {}, Payload: {}", topic, jsonObj);

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("Somethings went wrong", ex);
        }
    }
}
