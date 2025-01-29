package com.ecolink.spring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecolink.spring.dto.ChallengeDTO;
import com.ecolink.spring.dto.DTOConverter;
import com.ecolink.spring.entity.Challenge;
import com.ecolink.spring.service.ChallengeService;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;



@RequestMapping("api/challenges")
@RequiredArgsConstructor
@RestController
public class ChallengeController {

    private final ChallengeService challengeService;
    private final DTOConverter challengeDtoConverter;


    @GetMapping
    public ResponseEntity<?> getAllChallenges() {

        List<Challenge> challenges = challengeService.getAllChallenges();
        if(challenges.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        List<ChallengeDTO> dtoList = challenges.stream().map(challengeDtoConverter::converChallengeToDto)
        .collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }
    
}
