package com.ecolink.spring.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private List<CategoryDTO> categories;
    private String imageUrl;
    private BigDecimal price;
    private LocalDate creationDate;
    private String startupName;
}