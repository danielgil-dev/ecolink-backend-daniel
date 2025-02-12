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

    public void saveVerificationCode(UserBase user, String code, long expirationMinutes) {
        String key = "verification" + user.getEmail();
        redisTemplate.opsForValue().set(key, code, expirationMinutes, TimeUnit.MINUTES);

        String storedCode = redisTemplate.opsForValue().get(key);

        if (storedCode != null) {
            System.out.println("El código fue guardado correctamente: " + storedCode);
        } else {
            System.out.println("No se pudo recuperar el código.");
        }

    }

    public String getVerificationCode(UserBase user) {
        String key = "verification" + user.getEmail();
        String storedCode = redisTemplate.opsForValue().get(key);
        return storedCode;
    }

    public void deleteVerificationCode(UserBase user) {
        redisTemplate.delete("verification" + user.getEmail());
    }
}
