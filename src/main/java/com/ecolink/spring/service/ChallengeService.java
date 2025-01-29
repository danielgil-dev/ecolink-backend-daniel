package com.ecolink.spring.service;

import java.math.BigDecimal;
import java.util.List;

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

    public Challenge findByTitle (String title){
        return repository.findByTitle(title);
    }

    public void save(Challenge challenge) {
        repository.save(challenge);
    }

    public List<Challenge> getAllChallenges(){
        return repository.findAll();
    }

    public List<Challenge> getChallengeByBudgetRange(BigDecimal minBudget, BigDecimal maxBudget){
        List<Challenge> challenges = repository.findByBudgetBetween(minBudget, maxBudget);
       
        challenges.forEach(challenge ->{
            System.out.println("Holaaaa");
            System.out.println(challenge.toString());
        });
        return challenges;
        //return repository.findByBudgetBetween(minBudget, maxBudget);
    }
}
