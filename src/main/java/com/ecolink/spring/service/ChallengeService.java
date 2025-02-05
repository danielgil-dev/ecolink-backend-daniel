package com.ecolink.spring.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ecolink.spring.entity.Challenge;
import com.ecolink.spring.entity.Ods;
import com.ecolink.spring.repository.ChallengeRepository;
import com.ecolink.spring.specification.ChallengeSpecification;

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

    public List<Challenge> getChallengesByFilter(List<Ods> filterOds, BigDecimal pricemin,
            BigDecimal pricemax) {
        Specification<Challenge> spec = ChallengeSpecification.filters(filterOds, pricemin, pricemax);
        return repository.findAll(spec);
    }

    public Long getNumberChallenges(){
        return repository.count();
    }

    
}
