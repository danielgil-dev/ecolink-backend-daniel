package com.ecolink.spring.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateLineDTO {
    private Long id_orderLine;
    private Integer amount;
}
