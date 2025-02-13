package com.ecolink.spring.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChallengePostDTO {
    String title;
    String description;
    String shortDescription;
    BigDecimal budget;
    Integer endDate;
    List<Long> odsList;
    List<String> requirements;
    List<String> benefits;
}
