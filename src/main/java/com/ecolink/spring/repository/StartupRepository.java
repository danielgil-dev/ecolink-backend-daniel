package com.ecolink.spring.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ecolink.spring.entity.Startup;
import com.ecolink.spring.entity.Status;

public interface StartupRepository extends JpaRepository<Startup, Long>, JpaSpecificationExecutor<Startup> {
    boolean existsByName(String name);
    Startup findByName(String name);
    List<Startup> findTop3ByOrderByLevelDesc();
    Page<Startup> findAll(Pageable pageable);
    List<Startup> findByStatus(Status pending);
    
}
