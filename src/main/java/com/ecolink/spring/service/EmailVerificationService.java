package com.ecolink.spring.service;

import org.springframework.stereotype.Service;

import com.ecolink.spring.entity.UserBase;
import com.ecolink.spring.utils.CodeGenerator;

@Service
public class EmailVerificationService {
    private VerificationCodeService verificationCodeService;
    private NewPasswordCodeService newPasswordCodeService;
    private final EmailServiceImpl emailService;

    public EmailVerificationService(VerificationCodeService verificationCodeService,
            EmailServiceImpl emailServiceImpl, NewPasswordCodeService newPasswordCodeService) {
        this.verificationCodeService = verificationCodeService;
        this.emailService = emailServiceImpl;
        this.newPasswordCodeService = newPasswordCodeService;
    }

    public void sendVerificationEmail(UserBase user) {

        String code = CodeGenerator.generateVerificationCode(6);
        verificationCodeService.saveVerificationCode(user, code, 10);

        emailService.sendMessageValidate(user.getEmail(), code);

    }

    public void resendVerificationEmail(UserBase user) {
        String code = CodeGenerator.generateVerificationCode(6);
        verificationCodeService.deleteVerificationCode(user);
        verificationCodeService.saveVerificationCode(user, code, 10);
        emailService.resendMessageValidate(user.getEmail(), code);
    }

    public void sendResetPasswordCode(UserBase user) {
        String code = CodeGenerator.generateVerificationCode(6);
        newPasswordCodeService.saveNewPasswordCode(user, code, 10);
        emailService.sendMessageResetPassword(user.getEmail(), code);
    }
}
