package com.ecolink.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecolink.spring.entity.Mission;

public interface MisionRepository extends JpaRepository<Mission,Long> {

}
