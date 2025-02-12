package com.ecolink.spring.service;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.ecolink.spring.entity.UserBase;

@Service
public class VerificationCodeService {
    private final StringRedisTemplate redisTemplate;

    public VerificationCodeService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void saveVerificationCode(String email, String code, long expirationMinutes) {
        String key = "verification: " + email;
        redisTemplate.opsForValue().set(key, code, expirationMinutes, TimeUnit.MINUTES);
    }

    public String getVerificationCode(UserBase user) {
        return redisTemplate.opsForValue().get("verification:" + user.getEmail());
    }

    public void deleteVerificationCode(UserBase user) {
        redisTemplate.delete("verification" + user.getEmail());
    }
}
