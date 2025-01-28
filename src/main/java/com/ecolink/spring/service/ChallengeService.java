package com.ecolink.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecolink.spring.entity.Challenge;
import com.ecolink.spring.repository.ChallengeRepository;

@Service
public class ChallengeService {
    @Autowired
    private ChallengeRepository repository;

    public Boolean existsByTitle(String title) {
        return repository.existsByTitle(title);
    }

    public void save(Challenge challenge) {
        repository.save(challenge);
    }
}
