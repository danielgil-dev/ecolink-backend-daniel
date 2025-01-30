package com.ecolink.spring.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChallengeDTO {
    private String companyName;
    private String description;
    private BigDecimal budget;
    private LocalDateTime endDate;
}
