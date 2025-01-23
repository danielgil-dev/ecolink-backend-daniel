package com.example.ecolink.ecolink.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecolink.ecolink.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
    
}
