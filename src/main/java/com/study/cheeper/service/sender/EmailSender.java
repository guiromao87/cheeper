package com.study.cheeper.service.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailSender {

    @Autowired
    private JavaMailSender mailSender;

    public void send(String userEmail, String verificationCode) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject("Cheeper - Verificação de email");
            message.setText(verificationCode);
            message.setTo(userEmail);
            message.setFrom("estudosgit19@gmail.com");

            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
