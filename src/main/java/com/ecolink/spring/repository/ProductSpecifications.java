package com.ecolink.spring.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.ecolink.spring.entity.Ods;
import com.ecolink.spring.entity.Product;
import com.ecolink.spring.entity.Startup;

import jakarta.persistence.criteria.Predicate;

public class ProductSpecifications {
    public static Specification<Product> filters(
            Startup startup,
            Ods ods,
            BigDecimal precioMin,
            BigDecimal precioMax,
            Boolean disponible) {

                return (root, query, criteriaBuilder) -> {
                    List<Predicate> predicates = new ArrayList<>();

                };
    }
}
