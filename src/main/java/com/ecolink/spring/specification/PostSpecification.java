package com.ecolink.spring.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.ecolink.spring.entity.Ods;
import com.ecolink.spring.entity.Post;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;

public class PostSpecification {

    public static Specification<Post> filters(String startupName,
            String title,
            List<Ods> odsList) {

        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // 1) Filtro por 'startupName'
            // Verificamos si el usuario a pasado una startup
            if (startupName != null && !startupName.trim().isEmpty()) {
                // Convertimos a minúsculas para una comparación case-insensitive
                String nameLower = startupName.toLowerCase();
                // Accedemos al campo 'name' de la relación 'startup'
                Predicate startupNamePredicate = criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("startup").get("name")),
                        "%" + nameLower + "%");
                // Agregamos el predicado
                predicates.add(startupNamePredicate);
            }

            // 2) Filtro por 'title'
            // Verificamos si el usuario a pasado un titulo
            if (title != null && !title.trim().isEmpty()) {
                String titleLower = title.toLowerCase();
                Predicate titlePredicate = criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("title")),
                        "%" + titleLower + "%");
                Predicate descriptionPredicate = criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("description")),
                        "%" + titleLower + "%");
                // Combinar con OR (coincide con title o description)
                predicates.add(criteriaBuilder.or(titlePredicate, descriptionPredicate));
            }

            // 3) Filtro por 'odsList'
            //Verificamos que nos hayan pasado una lista con los id de las ods (Esta parte del codigo se puede mejorar)
            if (odsList != null && !odsList.isEmpty()) {
                // Hacemos JOIN con la lista de ODS en Post
                Join<Post, Ods> odsJoin = root.join("odsList");
                //Esto se puede traducir en lenguaje SQL como WHERE ods.id IN (x,y,z)
                predicates.add(odsJoin.in(odsList));
            }

            // 4) Unir todos los predicados con un AND
            // 
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

}
