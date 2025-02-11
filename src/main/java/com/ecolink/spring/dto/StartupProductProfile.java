package com.ecolink.spring.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StartupProductProfile {

    private String name;
    private String description;
    private String imageUrl;
    private BigDecimal price;
    private LocalDate creationDate;
    private String startupName;
}
