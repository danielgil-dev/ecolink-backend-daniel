package com.ecolink.spring.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.ecolink.spring.entity.OrderStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDTO {
    private Long id;

    private OrderStatus status;

    private LocalDate purchaseDate;

    private BigDecimal total;

    List<OrderLineDTO> orderLines;
}
