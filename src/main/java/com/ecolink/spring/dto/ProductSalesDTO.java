package com.ecolink.spring.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductSalesDTO {
    private String name;
    private Long amount;
    private BigDecimal total;
}
