package com.example.ecolink.ecolink.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecolink.ecolink.entity.Startup;

public interface StartupRepository extends JpaRepository<Startup, Long> {
    
}
