package com.ecolink.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecolink.spring.entity.Client;
import com.ecolink.spring.entity.ClientMission;
import com.ecolink.spring.entity.Mission;

import java.util.List;


public interface ClientMissionRepository extends  JpaRepository<ClientMission, Long>{
        
    List<ClientMission> findByClient(Client client); 
    public ClientMission findByClientAndMission(Client client ,Mission mission);
	
}
