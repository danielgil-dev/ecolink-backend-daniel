package com.ecolink.spring.dto;

import com.ecolink.spring.entity.Product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderLineDTO {
    private Long id;
    
    private Product product;

    private Integer amount;

}
