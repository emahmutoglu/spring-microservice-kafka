package com.emahmutoglu.notificationservice.service.mail;

import com.emahmutoglu.notificationservice.data.client.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface MailService {

    void sendNotification(UserDto userDto);
}
