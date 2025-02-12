package com.ecolink.spring.service;

import org.springframework.stereotype.Service;

import com.ecolink.spring.entity.UserBase;
import com.ecolink.spring.utils.CodeGenerator;

@Service
public class EmailVerificationService {
    private VerificationCodeService verificationCodeService;
    private final EmailServiceImpl emailService;

    public EmailVerificationService(VerificationCodeService verificationCodeService,
            EmailServiceImpl emailServiceImpl) {
        this.verificationCodeService = verificationCodeService;
        this.emailService = emailServiceImpl;
    }

    public void sendVerificationEmail(UserBase user) {

        String code = CodeGenerator.generateVerificationCode(6);
        verificationCodeService.saveVerificationCode(user, code, 10);

        emailService.sendMessageValidate(user.getEmail(), code);

    }
}
