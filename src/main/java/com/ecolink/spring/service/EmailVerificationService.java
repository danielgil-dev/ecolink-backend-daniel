package com.ecolink.spring.service;

import org.springframework.stereotype.Service;

import com.ecolink.spring.entity.UserBase;

@Service
public class EmailVerificationService {
    private VerificationCodeService verificationCodeService;

    public EmailVerificationService(VerificationCodeService verificationCodeService){
        this.verificationCodeService = verificationCodeService;
    }

    public void sendVerificationEmail(UserBase user){
       String code = "1234";
       verificationCodeService.saveVerificationCode(user.getEmail(), code, 10);

       System.out.println("Codigo del usuario: "+user.getEmail() + ": "+ code);
    }
}
