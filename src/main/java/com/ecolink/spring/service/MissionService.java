package com.ecolink.spring.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecolink.spring.entity.Mission;
import com.ecolink.spring.entity.MissionType;
import com.ecolink.spring.repository.MissionRepository;

@Service
public class MissionService {

	@Autowired
	private  MissionRepository repository;


	public boolean existsByNameAndType(String name, MissionType type) {
		return repository.existsByNameAndType(name, type);
	}

	public List<Mission> getAllMissions(){
		return repository.findAll();
	}

	public Mission findById(Long id){
		return repository.findById(id).orElse(null);
	}

	public void save(Mission misson) {
		repository.save(misson);
	}

	public void updateMission(Mission mission){

		if(mission.getCompleted() == false){
			mission.setCompleted(true);
		}else{
			mission.setCompleted(false);
		}

	}

}
