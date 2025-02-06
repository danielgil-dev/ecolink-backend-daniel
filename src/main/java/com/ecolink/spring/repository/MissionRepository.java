package com.ecolink.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecolink.spring.entity.Mission;
import com.ecolink.spring.entity.MissionType;



public interface MissionRepository extends JpaRepository<Mission,Long> {

    boolean existsByNameAndType(String name, MissionType type);
    Mission findByName (String name);

}
