package com.ecolink.spring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecolink.spring.entity.UserBase;
import com.ecolink.spring.exception.ErrorDetails;
import com.ecolink.spring.response.SuccessDetails;
import com.ecolink.spring.service.VerificationCodeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/verify")
public class VerificationController {
    private final VerificationCodeService verificationCodeService;

    @PostMapping
    public ResponseEntity<?> verifyCode(@AuthenticationPrincipal UserBase user,
            @RequestParam String code) {

        if (user == null) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.UNAUTHORIZED.value(),
                    "The user must be logged in");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorDetails);
        }

        String storedCode = verificationCodeService.getVerificationCode(user);

        if (storedCode != null && storedCode.equals(code)) {
            verificationCodeService.deleteVerificationCode(user);
            SuccessDetails successDetails = new SuccessDetails(HttpStatus.OK, "User has been successfully verified");
            return ResponseEntity.ok(successDetails);
        }
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST, "User has been successfully verified");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
    }
}
