package com.ecolink.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecolink.spring.entity.Client;
import com.ecolink.spring.entity.ClientMission;
import com.ecolink.spring.entity.Mission;
import com.ecolink.spring.exception.ClientMissionAssingmentException;
import com.ecolink.spring.repository.ClientMissionRepository;

@Service
public class ClientMissionService {

    @Autowired
    private ClientMissionRepository clientMissionRepository;

    public List<ClientMission> getClientMission(Client client) {

        return clientMissionRepository.findByClient(client);
    }

    public void completeMissionForClient(Mission mission, Client client) {
        ClientMission clientMission = findByMissionAndClient (mission, client);
        if (clientMission == null) {
            clientMission = new ClientMission(client, mission);
            clientMissionRepository.save(clientMission);
        }else{
            throw new ClientMissionAssingmentException("La mission ya ha sido asignada al usuario");
        }
    }

    public ClientMission findByMissionAndClient (Mission mission, Client client){
        return clientMissionRepository.findByClientAndMission(client, mission);

    }

    public void save(ClientMission clientMission) {
        clientMissionRepository.save(clientMission);
    }

}
