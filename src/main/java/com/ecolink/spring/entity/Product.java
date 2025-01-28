package com.ecolink.spring.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Startup startup;

    public Product(Startup startup, String name, String description, BigDecimal price, LocalDate creationDate) {
        this.startup = startup;
        this.name = name;
        this.description = description;
        this.price = price;
        this.creationDate = creationDate;
    }

    private String name;
    private String description;
    private BigDecimal price;
    private LocalDate creationDate;
}
