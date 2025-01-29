package com.ecolink.spring.specification;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.ecolink.spring.entity.Challenge;
import com.ecolink.spring.entity.Ods;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;

public class ChallengeSpecification {

    public static Specification<Challenge> filters(
            List<Ods> odsList,
            BigDecimal priceMin,
            BigDecimal priceMax ){
       
                
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>(); 
            if(odsList != null && !odsList.isEmpty()){
                Join<Challenge, Ods> odsJoin = root.join("odsList");
                predicates.add(odsJoin.get("id").in(odsList.stream().map(Ods::getId).toList()));
            }

            if (priceMin != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("budget"), priceMin));
            }
            if (priceMax != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("budget"), priceMax));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
          
        }
}
