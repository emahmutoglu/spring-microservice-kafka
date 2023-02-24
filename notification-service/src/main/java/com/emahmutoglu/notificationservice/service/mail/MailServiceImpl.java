package com.emahmutoglu.notificationservice.service.mail;

import com.emahmutoglu.notificationservice.data.client.UserDto;
import com.emahmutoglu.notificationservice.data.entity.Mail;
import com.emahmutoglu.notificationservice.data.repository.MailRepository;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MailServiceImpl implements MailService {

    private final static String MAIL_SUBJECT = "Registration";
    private final static String MAIL_TEXT = "Welcome, your registration has been successfully completed.";
    private final MailRepository mailRepository;
    private final JavaMailSender mailSender;

    @Override
    public void sendNotification(UserDto userDto) {
        Mail mail = new Mail();
        mail.setEmail(userDto.getEmail());
        mail.setSubject(MAIL_SUBJECT);
        mail.setText(MAIL_TEXT);

        Mail savedMail = mailRepository.save(mail);
        sendSimpleMailMessage(savedMail);
    }

    private void sendSimpleMailMessage(Mail mail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("xxx@gmail.com");
        message.setTo(mail.getEmail());
        message.setText(mail.getText());
        message.setSubject(mail.getSubject());
        mailSender.send(message);
    }
}
