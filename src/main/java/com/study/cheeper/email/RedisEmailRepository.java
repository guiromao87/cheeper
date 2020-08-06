package com.study.cheeper.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RedisEmailRepository  {

    @Autowired
    private RedisTemplate redisTemplate;

    public RedisEmailRepository(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    public void save(String email, String code){ redisTemplate.opsForValue().set(email,code); }

    public String findBy(String email) {
        return (String) redisTemplate.opsForValue().get(email);
    }

    public void remove(String email) { redisTemplate.opsForValue().getOperations().delete(email); }
}
