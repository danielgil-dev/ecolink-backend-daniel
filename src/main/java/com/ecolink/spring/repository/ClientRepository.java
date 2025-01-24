package com.ecolink.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecolink.spring.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
    
}
