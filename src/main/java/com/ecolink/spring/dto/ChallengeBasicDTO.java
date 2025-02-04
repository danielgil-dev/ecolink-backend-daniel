package com.ecolink.spring.dto;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChallengeBasicDTO {
    Long id;
    String description;
    BigDecimal budget;
}
