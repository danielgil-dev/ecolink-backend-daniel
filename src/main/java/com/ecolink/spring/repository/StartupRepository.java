package com.ecolink.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecolink.spring.entity.Startup;

public interface StartupRepository extends JpaRepository<Startup, Long> {
    boolean existsByName(String name);
    Startup findByName(String name);
    List<Startup> findTop5ByOrderByRegisterDateDesc();
}
