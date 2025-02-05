package com.ecolink.spring.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.ecolink.spring.entity.Ods;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChallengeDTO {
    Long id;
    String companyName;
    String title;
    String description;
    BigDecimal budget;
    LocalDate endDate;
    List<Ods> odsList;
    private Integer numberOfParticipans;
}
