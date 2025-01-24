package com.ecolink.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecolink.spring.repository.ChallengeRepository;

@Service
public class ChallengeService {
    @Autowired
    private ChallengeRepository challengeRepository;
}
