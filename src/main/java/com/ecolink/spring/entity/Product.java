package com.ecolink.spring.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    private Startup startup;

    public Product(Startup startup, String name, String description, BigDecimal price, LocalDate creationDate) {
        this.startup = startup;
        this.name = name;
        this.description = description;
        this.price = price;
        this.creationDate = creationDate;
    }

    public Product(Startup startup, String name, String description, BigDecimal price) {
        this.startup = startup;
        this.name = name;
        this.description = description;
        this.price = price;
        this.creationDate = LocalDate.now();
    }

    private String name;
    private String description;
    private BigDecimal price;
    private LocalDate creationDate;
}
