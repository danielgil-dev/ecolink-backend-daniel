package com.ecolink.spring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecolink.spring.entity.UserBase;
import com.ecolink.spring.exception.ErrorDetails;
import com.ecolink.spring.service.ProposalService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/proposal")
@RestController
public class ProposalController {
    private final ProposalService service;

    @GetMapping("/challenge/{id}")
    private ResponseEntity<?> getProposalsByChallenge(@AuthenticationPrincipal UserBase user, @PathVariable Long id){
        try {
            if (user == null) {
                ErrorDetails errorDetails = new ErrorDetails(HttpStatus.UNAUTHORIZED.value(),
                        "The user must be logged in");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorDetails);
            }
            return ResponseEntity.ok("");
        } catch (Exception e) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        }
    }
}
