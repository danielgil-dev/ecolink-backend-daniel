package com.ecolink.spring.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecolink.spring.dto.ClientMissionDTO;
import com.ecolink.spring.entity.Client;
import com.ecolink.spring.entity.ClientMission;
import com.ecolink.spring.entity.Mission;
import com.ecolink.spring.entity.UserBase;
import com.ecolink.spring.exception.ClientMissionAssingmentException;
import com.ecolink.spring.exception.ClientNotFoundException;
import com.ecolink.spring.exception.ErrorDetails;
import com.ecolink.spring.exception.MissionNotFoundException;
import com.ecolink.spring.service.ClientMissionService;
import com.ecolink.spring.service.ClientService;
import com.ecolink.spring.service.MissionService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("api/mission")
@RestController
public class MissionController {

    public final MissionService missionService;
    public final ClientService clientService;
    private final ClientMissionService clientMissionService;

    @GetMapping
    public ResponseEntity<?> getAllMissions(@AuthenticationPrincipal UserBase user ) {

        try {

            Client client = clientService.findById(user.getId());
            if (client == null) {
                
                throw new ClientNotFoundException("No se encontro un usuario autenticado");
            }
            List<Mission> missions = missionService.getAllMissions();
            if (missions.isEmpty()) {
                throw new MissionNotFoundException("No hay misiones en la base de datos");
            }
            List<ClientMission> clientMissions = clientMissionService.getClientMission(client);
            Set<Long> completedMissionIds = clientMissions.stream()
            .filter(ClientMission::getCompleted) 
            .map(cm -> cm.getMission().getId())
            .collect(Collectors.toSet());
            
            List<ClientMissionDTO> missionsDto = missions.stream().map(mission ->
            new ClientMissionDTO(mission.getId(),
                mission.getName(),
                mission.getDescription(),
                mission.getType().toString(),
                mission.getPoints(),
                completedMissionIds.contains(mission.getId()))
            ).collect(Collectors.toList());
          
            return ResponseEntity.ok(missionsDto);

        } catch (MissionNotFoundException | ClientNotFoundException e) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        } catch (Exception e) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Internal Server Error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> completedMission(@AuthenticationPrincipal UserBase user,
            @PathVariable(required = true) Long id) {

        try {

            Mission mission = missionService.findById(id);
            if (mission == null) {
                throw new MissionNotFoundException("No se encontro una mission con el id " + id);
            }
            Client client = clientService.findById(user.getId());
            if (client == null) {
                throw new ClientNotFoundException("No se encontro ningun cliente con el id " + user.getId());
            }
                clientMissionService.completeMissionForClient(mission, client);
                return ResponseEntity.ok(HttpStatus.OK);
        } catch (MissionNotFoundException | ClientNotFoundException  | ClientMissionAssingmentException e) {
            e.printStackTrace();
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
        } catch (Exception e) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Ocurrió un error interno en el servidor");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        }
    }
}

