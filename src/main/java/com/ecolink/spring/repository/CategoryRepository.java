package com.ecolink.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecolink.spring.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByName(String name);
    boolean findByName(String name);
    
}