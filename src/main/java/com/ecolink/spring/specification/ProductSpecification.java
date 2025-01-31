package com.ecolink.spring.specification;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.ecolink.spring.entity.Product;
import com.ecolink.spring.entity.Startup;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;

public class ProductSpecification {
    public static Specification<Product> filters(
            Long id_startup,
            String name,
            BigDecimal priceMin,
            BigDecimal priceMax) {

        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (id_startup != null) {
                Join<Product, Startup> startupJoin = root.join("startup");
                predicates.add(criteriaBuilder.equal(startupJoin.get("id"), id_startup));
            }

            if (name != null) {
                predicates.add(
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
                predicates.add(
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("description")),
                                "%" + name.toLowerCase() + "%"));
            }

            if (priceMin != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), priceMin));
            }
            if (priceMax != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"), priceMax));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
