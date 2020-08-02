package com.study.cheeper.repository;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RedisEmailRepository  {

    private HashOperations hashOperations;

    private RedisTemplate redisTemplate;

    public RedisEmailRepository(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
        this.hashOperations = this.redisTemplate.opsForHash();
    }

    public void save(String email, String code){
        hashOperations.put("CHEEPER", email, code);
    }

    public String findBy(String email) {
        return (String) hashOperations.get("CHEEPER", email);
    }

}
