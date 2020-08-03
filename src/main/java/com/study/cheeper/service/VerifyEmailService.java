package com.study.cheeper.service;

import com.study.cheeper.repository.RedisEmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerifyEmailService {

    @Autowired
    private RedisEmailRepository redisEmailRepository;

    public boolean verify(String email, String code) {
        return this.redisEmailRepository.findBy(email).equals(code);
    }

    public void remove(String email) {
        this.redisEmailRepository.remove(email);
    }

}
