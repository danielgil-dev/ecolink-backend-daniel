package com.ecolink.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecolink.spring.entity.Mission;
import com.ecolink.spring.entity.MissionType;
import com.ecolink.spring.repository.MissionRepository;

@Service
public class MissionService {

	@Autowired
	private MissionRepository repository;

	public boolean existsByNameAndType(String name, MissionType type) {
		return repository.existsByNameAndType(name, type);
	}


	public void save(Mission misson) {
		repository.save(misson);
	}

}
