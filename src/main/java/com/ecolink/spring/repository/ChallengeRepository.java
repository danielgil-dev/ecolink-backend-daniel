package com.ecolink.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.ecolink.spring.entity.Challenge;

import java.math.BigDecimal;
import java.time.LocalDate;


@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, Long>, JpaSpecificationExecutor<Challenge> {

    Boolean existsByTitle(String title);
    Challenge findByTitle(String title);
    List<Challenge> findByBudgetBetween(BigDecimal minBudget, BigDecimal maxBudget);
    List<Challenge> findTop4ByEndDateGreaterThanEqualOrderByEndDateAsc(LocalDate todayDay);
    List<Challenge> findByendDateGreaterThanEqual(LocalDate todayDay);

}
