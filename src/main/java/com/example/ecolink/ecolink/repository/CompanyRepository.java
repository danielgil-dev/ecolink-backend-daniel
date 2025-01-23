package com.example.ecolink.ecolink.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecolink.ecolink.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    
}
