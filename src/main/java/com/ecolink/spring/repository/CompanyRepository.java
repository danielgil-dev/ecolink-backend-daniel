package com.ecolink.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecolink.spring.entity.Company;
import com.ecolink.spring.entity.Status;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    boolean existsByName(String name);
    Company findByName(String name);
    List<Company> findByStatus(Status pending);
}
