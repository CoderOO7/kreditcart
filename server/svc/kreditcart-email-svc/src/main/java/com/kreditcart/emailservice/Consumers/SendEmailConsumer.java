package com.kreditcart.emailservice.Consumers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kreditcart.emailservice.Dtos.SendEmailMessageDto;
import com.kreditcart.emailservice.Utils.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

@Service
public class SendEmailConsumer {
    @Autowired
    private ObjectMapper objectMapper;
    @Value("${smtp.email.id}")
    private String smtpEmailId;
    @Value("${smtp.email.secret}")
    private String smtpEmailSecret;

    // groupId ensures that only instance of that groupId will be able to perform task.
    // I don't want to send emails multiple times to same user

    @KafkaListener(topics = "sendEmail", groupId = "emailService")
    public void handleSendEmail(String message) {
        System.out.println("executing handleSendEmail----");
        try {
            SendEmailMessageDto sendEmailMessageDto = objectMapper.readValue(message, SendEmailMessageDto.class);

            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
            props.put("mail.smtp.port", "587"); //TLS Port
            props.put("mail.smtp.auth", "true"); //enable authentication
            props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

            //create Authenticator object to pass in Session.getInstance argument
            Authenticator auth = new Authenticator() {
                //override the getPasswordAuthentication method
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(smtpEmailId, smtpEmailSecret);
                }
            };
            Session session = Session.getInstance(props, auth);

            EmailUtil.sendEmail(session, sendEmailMessageDto.getTo(), sendEmailMessageDto.getSubject(), sendEmailMessageDto.getBody());

        }catch(JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }
}
