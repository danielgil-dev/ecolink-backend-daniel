package com.ecolink.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecolink.spring.entity.Challenge;

@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, Long> {

    Boolean existsByTitle(String title);
}
