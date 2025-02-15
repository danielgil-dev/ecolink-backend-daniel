package com.ecolink.spring.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecolink.spring.dto.VerificationRequestDTO;
import com.ecolink.spring.entity.Company;
import com.ecolink.spring.entity.Startup;
import com.ecolink.spring.entity.Status;
import com.ecolink.spring.entity.UserBase;
import com.ecolink.spring.exception.ErrorDetails;
import com.ecolink.spring.response.SuccessDetails;
import com.ecolink.spring.service.EmailServiceImpl;
import com.ecolink.spring.service.EmailVerificationService;
import com.ecolink.spring.service.UserBaseService;
import com.ecolink.spring.service.VerificationCodeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/verify")
public class VerificationController {
    private final VerificationCodeService verificationCodeService;
    private final UserBaseService userBaseService;
    private final EmailServiceImpl emailService;
    private final EmailVerificationService emailVerificationService;

    @PostMapping
    public ResponseEntity<?> verifyCode(@RequestBody VerificationRequestDTO request) {

        if (request.getEmail() == null || request.getEmail().isEmpty() || request.getCode() == null
                || request.getCode().isEmpty()) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST.value(),
                    "Email and code must be provided");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);

        }

        String email = request.getEmail();
        String code = request.getCode();

        UserBase user = userBaseService.findByEmail(email).orElse(null);
        if (user == null) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.UNAUTHORIZED.value(),
                    "The user must be logged in");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorDetails);
        }

        if (user.isVerified()) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST.value(),
                    "User has already been verified");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
        }

        String storedCode = verificationCodeService.getVerificationCode(user).trim();
        String inputCode = code.trim();

        if (storedCode != null && storedCode.equals(inputCode)) {
            verificationCodeService.deleteVerificationCode(user);
            SuccessDetails successDetails = new SuccessDetails(HttpStatus.OK, "User has been successfully verified");
            user.setVerified(true);
            userBaseService.save(user);
            emailService.accountVerified(user.getEmail());

            if (user instanceof Startup startup) {
                if (startup.getStatus().equals(Status.PENDING)) {
                    emailService.sendAccountPending(email);
                }
            } else if(user instanceof Company company){
                if (company.getStatus().equals(Status.PENDING)) {
                    emailService.sendAccountPending(email);
                }
            }
        
            return ResponseEntity.ok(successDetails);
        }

        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST.value(), "Invalid or expired code");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
    }

    @PostMapping("/resend")
    public ResponseEntity<String> resendCode(@RequestParam String email) {
        System.out.println(email);
        if (email == null || email.isEmpty()) {
            return ResponseEntity.badRequest().body("Email must be provided");
        }

        UserBase user = userBaseService.findByEmail(email).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        if (user.isVerified()) {
            return ResponseEntity.ok("User has already been verified");
        }

        emailVerificationService.resendVerificationEmail(user);

        return ResponseEntity.status(HttpStatus.FOUND)
                .header(HttpHeaders.LOCATION, "http://localhost:8080/auth/verification")
                .build();
    }

}
