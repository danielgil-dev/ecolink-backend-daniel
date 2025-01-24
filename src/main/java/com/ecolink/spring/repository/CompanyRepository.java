package com.ecolink.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecolink.spring.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    
}
