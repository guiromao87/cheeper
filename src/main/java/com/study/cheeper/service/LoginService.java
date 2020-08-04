package com.study.cheeper.service;

import com.study.cheeper.email.RedisEmailRepository;
import com.study.cheeper.email.sender.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class LoginService {

    @Autowired
    private EmailSender emailSender;

    @Autowired
    private RedisEmailRepository redisEmailRepository;

    private String code;

    public void sendEmailTo(String email) {
        this.code = generate6DigitsCode();
        this.emailSender.send(email, code);
        this.redisEmailRepository.save(email, code);
    }

    private String generate6DigitsCode() {
        return String.format("%06d", new Random().nextInt(999999));
    }
}
