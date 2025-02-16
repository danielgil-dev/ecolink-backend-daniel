package com.ecolink.spring.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StartupProductPrivateProfileDTO {
    private String name;
    private String description;
    private String imageUrl;
    private BigDecimal price;
}
