package com.emahmutoglu.notificationservice.consumer;

import com.emahmutoglu.notificationservice.data.client.UserDto;
import com.emahmutoglu.notificationservice.service.mail.MailService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserConsumer {
    private final ObjectMapper objectMapper;
    private final MailService mailService;

    @Bean
    public NewTopic senEmailTopic() {
        return TopicBuilder.name("send-email-notification")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @KafkaListener(topics = "send-email-notification", groupId = "mail-service")
    @SendTo
    public void send(String userDto) throws JsonProcessingException {
        mailService.sendNotification(objectMapper.readValue(userDto, UserDto.class));
    }
}
