package com.ecolink.spring.specification;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.ecolink.spring.entity.Ods;
import com.ecolink.spring.entity.Product;
import com.ecolink.spring.entity.Startup;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;

public class ProductSpecification {
    public static Specification<Product> filters(
            Startup startup,
            Ods ods,
            BigDecimal priceMin,
            BigDecimal priceMax) {

                return (root, query, criteriaBuilder) -> {
                    List<Predicate> predicates = new ArrayList<>();
                    if (startup != null) {
                        Join<Product, Startup> startupJoin = root.join("startup");
                        predicates.add(criteriaBuilder.equal(startupJoin.get("id"), ods.getId()));
                    }

                    if (ods != null) {
                        Join<Product, Ods> odsJoin = root.join("ods");
                        predicates.add(criteriaBuilder.equal(odsJoin.get("id"), ods.getId()));
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
