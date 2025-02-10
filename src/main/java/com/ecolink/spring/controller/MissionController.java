package com.ecolink.spring.controller;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.internal.bytebuddy.asm.Advice.Return;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.ecolink.spring.dto.ClientMissionDTO;
import com.ecolink.spring.dto.DTOConverter;
import com.ecolink.spring.dto.MissionPostDTO;
import com.ecolink.spring.entity.Admin;
import com.ecolink.spring.entity.Client;
import com.ecolink.spring.entity.ClientMission;
import com.ecolink.spring.entity.Mission;
import com.ecolink.spring.entity.MissionType;
import com.ecolink.spring.entity.UserBase;
import com.ecolink.spring.exception.ClientMissionAssingmentException;
import com.ecolink.spring.exception.ClientNotFoundException;
import com.ecolink.spring.exception.ErrorDetails;
import com.ecolink.spring.exception.MissionNotFoundException;
import com.ecolink.spring.service.ClientMissionService;
import com.ecolink.spring.service.ClientService;
import com.ecolink.spring.service.MissionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("api/mission")
@RestController
public class MissionController {

    public final MissionService missionService;
    public final ClientService clientService;
    private final ClientMissionService clientMissionService;
    private final DTOConverter dtoConverter;

    @GetMapping
    public ResponseEntity<?> getAllMissions(@AuthenticationPrincipal UserBase user) {

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

            List<ClientMissionDTO> missionsDto = missions.stream().map(mission -> new ClientMissionDTO(mission.getId(),
                    mission.getName(),
                    mission.getDescription(),
                    mission.getType().toString(),
                    mission.getPoints(),
                    completedMissionIds.contains(mission.getId()))).collect(Collectors.toList());

            // List<ClientMissionDTO> missionsDto = missions.stream().map(mission -> con)

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

    // Cambiar estado de la mission a completed
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
            ClientMission missionClient = clientMissionService.completeMissionForClient(mission, client);
            ClientMissionDTO missionClientDto = dtoConverter.convertClientMissionDTO(missionClient.getMission(), true);
            return ResponseEntity.ok(missionClientDto);
        } catch (MissionNotFoundException | ClientNotFoundException | ClientMissionAssingmentException e) {
            e.printStackTrace();
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
        } catch (Exception e) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Ocurrió un error interno en el servidor");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        }
    }

    // Crear mission
    @PostMapping("/new")
    public ResponseEntity<?> createMission(@AuthenticationPrincipal UserBase user,
            @RequestPart("mission") String missionJson) {

        System.out.println("Esto son los valores de JSON:" + missionJson);
        try {
            if (!(user instanceof Admin)) {

                throw new AccessDeniedException("User not have permission to create a mission");
            }
            ObjectMapper objectMapper = new ObjectMapper();
            MissionPostDTO mission = objectMapper.readValue(missionJson, MissionPostDTO.class);
            if (mission.getName().isEmpty() || mission.getDescription().isEmpty() || mission.getPoints() == null
                    || mission.getType() == null) {

                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ErrorDetails(HttpStatus.BAD_REQUEST.value(), "Invalid Fields"));
            }
            MissionType typeMission;
            try {
                typeMission = MissionType.valueOf(mission.getType());

            } catch (IllegalArgumentException e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ErrorDetails(HttpStatus.BAD_REQUEST.value(),
                                "Invalid mission type: " + mission.getType()));
            }
            Mission newMission = new Mission(mission.getName(), mission.getDescription(), typeMission,
                    mission.getPoints());
            missionService.save(newMission);
            return ResponseEntity.ok(newMission);
        } catch (UsernameNotFoundException e) {

            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
        } catch (AccessDeniedException e) {

            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.FORBIDDEN.value(), e.getMessage());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorDetails);

        } catch (Exception e) {

            System.out.println(e.getMessage());
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Ocurrió un error interno en el servidor");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        }

    }

    // Eliminar mission
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMission(@AuthenticationPrincipal UserBase user, @PathVariable Long id) {

        try {
            // Verificamos que el usaurio sea admin antes de cualquier otra operacion
            if (!(user instanceof Admin)) {
                throw new AccessDeniedException("User not have  permissions to delete a mission");
            }

            // Buscamos la mission por el id y verifamos que no sea nula
            Mission missionToDelete = missionService.findById(id);
            if (missionToDelete == null) {
                throw new MissionNotFoundException("No se econtro una mission con el id" + id);
            }

            // Eliminamos la mission y devolvemos un notFound 204 para indicar que la
            // eliminacion fue exitosa
            missionService.delete(missionToDelete);
            return ResponseEntity.noContent().build();
        } catch (MissionNotFoundException e) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);

        } catch (AccessDeniedException e) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.FORBIDDEN.value(), e.getMessage());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorDetails);

        } catch (Exception e) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Ocurrió un error interno en el servidor");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        }
    }

    // Editar una mission
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editMission(@AuthenticationPrincipal UserBase user, @PathVariable Long id,
            @RequestPart("mission") String missionJson) {

        try {
            if (!(user instanceof Admin)) {
                throw new AccessDeniedException("User not have  permissions to delete a mission");
            }
            Mission missionToEdit = missionService.findById(id);
            if (missionToEdit == null) {
                throw new MissionNotFoundException("No se encontro una mission con el id" + id);
            }

            ObjectMapper objectMapper = new ObjectMapper();
            MissionPostDTO missionDto = objectMapper.readValue(missionJson, MissionPostDTO.class);
            if (missionDto.getName() == null || missionDto.getDescription() == null || missionDto.getPoints() == null
                    || missionDto.getType() == null) {

                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ErrorDetails(HttpStatus.BAD_REQUEST.value(), "Invalid Fields"));
            }
            MissionType typeMission;
            try {
                typeMission = MissionType.valueOf(missionDto.getType());

            } catch (IllegalArgumentException e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ErrorDetails(HttpStatus.BAD_REQUEST.value(),
                                "Invalid mission type: " + missionDto.getType()));
            }
            missionToEdit.setName(missionDto.getName());
            missionToEdit.setDescription(missionDto.getDescription());
            missionToEdit.setPoints(missionDto.getPoints());
            missionToEdit.setType(typeMission);
            missionService.save(missionToEdit);

            return ResponseEntity.ok(missionToEdit);
        } catch (MissionNotFoundException e) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);

        } catch (Exception e) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Ocurrió un error interno en el servidor");
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        }
    }
}
