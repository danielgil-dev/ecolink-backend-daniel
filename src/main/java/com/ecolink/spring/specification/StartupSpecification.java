package com.ecolink.spring.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.ecolink.spring.entity.Ods;
import com.ecolink.spring.entity.Startup;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;

public class StartupSpecification {
    public static Specification<Startup> filters(
            String name, List<Ods> odsList) {

        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (name != null) {
                Predicate namePredicate = criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%"
                );
                Predicate descriptionPredicate = criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("description")), "%" + name.toLowerCase() + "%"
                );

                predicates.add(criteriaBuilder.or(namePredicate, descriptionPredicate));

            }
            if (odsList != null && !odsList.isEmpty()) {
                Join<Startup, Ods> odsJoin = root.join("odsList");
                predicates.add(odsJoin.in(odsList));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

    }
}
