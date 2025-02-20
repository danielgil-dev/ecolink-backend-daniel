package com.ecolink.spring.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductRelevantDTO {
    private Long id;
    private String name;
    private BigDecimal price;
    private String imageUrl;
}
