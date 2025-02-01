package com.ecolink.spring.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChallengeDTO {
    Long id;
    String companyName;
    String description;
    BigDecimal budget;
    LocalDateTime endDate;
}
