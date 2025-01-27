package com.ecolink.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecolink.spring.entity.Ods;

@Repository
public interface OdsRepository extends JpaRepository<Ods, Long> {
    public boolean existsByName(String name);
    public Ods findByName(String name);
}
