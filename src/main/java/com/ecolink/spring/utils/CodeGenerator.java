package com.ecolink.spring.utils;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class CodeGenerator {
    public static String generateVerificationCode(int length) {
        Random random = new Random();
        StringBuilder code = new StringBuilder();

        // Generar código numérico
        for (int i = 0; i < length; i++) {
            code.append(random.nextInt(10));
        }
        return code.toString();
    }
}
