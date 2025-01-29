package com.ecolink.spring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecolink.spring.dto.ChallengeDTO;
import com.ecolink.spring.dto.DTOConverter;
import com.ecolink.spring.entity.Challenge;
import com.ecolink.spring.entity.Ods;
import com.ecolink.spring.service.ChallengeService;
import com.ecolink.spring.service.OdsService;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("api/challenges")
@RequiredArgsConstructor
@RestController
public class ChallengeController {

    private final ChallengeService challengeService;
    private final DTOConverter challengeDtoConverter;
     private final OdsService odsService;

    @GetMapping
    public ResponseEntity<?> getAllChallenges() {

        List<Challenge> challenges = challengeService.getAllChallenges();
        if (challenges.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        List<ChallengeDTO> dtoList = challenges.stream().map(challengeDtoConverter::converChallengeToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/filter")
    public ResponseEntity<?> getFilteredChallenges(
            @RequestParam(required = false) List<Long> odsIds,
             @RequestParam(required = false) BigDecimal min,
            @RequestParam (required = false) BigDecimal max) {

        List<Ods> odsList = (odsIds != null && !odsIds.isEmpty()) ? odsService.findAllById(odsIds) : null;
        

        List<Challenge> challenges = challengeService.getChallengesByFilter(odsList, min, max);
       
        if (challenges.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
       

        List<ChallengeDTO> dtoList = challenges.stream().map(challengeDtoConverter::converChallengeToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);

    }

}
