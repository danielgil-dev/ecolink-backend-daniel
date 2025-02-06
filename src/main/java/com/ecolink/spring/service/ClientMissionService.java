package com.ecolink.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecolink.spring.entity.Client;
import com.ecolink.spring.entity.ClientMission;
import com.ecolink.spring.entity.Mission;
import com.ecolink.spring.repository.ClientMissionRepository;

@Service
public class ClientMissionService {

    @Autowired
    private  ClientMissionRepository clientMissionRepository;


    public List<ClientMission> getClientMission(Client client){

        
        return clientMissionRepository.findByClient(client);
    }

    public void assingMissionToClient(Mission mission, Client client){
        if(mission != null && client != null){
            ClientMission clientMission = new ClientMission(client, mission);
            clientMissionRepository.save(clientMission);
        }
    }

    public void updateMission(ClientMission clientMission) {

		if (clientMission.getCompleted() == false) {
			clientMission.setCompleted(true);
		} else {
			clientMission.setCompleted(false);
		}

	}

    public void save (ClientMission clientMission){
        clientMissionRepository.save(clientMission);
    }


    
}
