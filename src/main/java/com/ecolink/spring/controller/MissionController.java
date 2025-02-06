package com.ecolink.spring.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecolink.spring.dto.DTOConverter;
import com.ecolink.spring.dto.MissionDTO;
import com.ecolink.spring.entity.Mission;
import com.ecolink.spring.entity.UserBase;
import com.ecolink.spring.exception.ErrorDetails;
import com.ecolink.spring.exception.MissionNotFoundException;
import com.ecolink.spring.service.MissionService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("api/mission")
@RestController
public class MissionController {

    public final MissionService missionService;
    private final DTOConverter dtoConverter;

    @GetMapping
    public ResponseEntity<?> getAllMissions() {

        try {
            List<Mission> missions = missionService.getAllMissions();
            if (missions.isEmpty()) {
                throw new MissionNotFoundException("No hay misiones en la base de datos");
            }

            List<MissionDTO> dtoMission = missions.stream().map(dtoConverter::convertMissionToDto)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(dtoMission);
        } catch (MissionNotFoundException e) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        } catch (Exception e) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Ocurrió un error interno en el servidor");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        }
    }
    @PutMapping("{id}")
<<<<<<< HEAD
    public ResponseEntity<?> completedMisiion(@AuthenticationPrincipal UserBase user, @RequestParam(required = false) Long id){
=======
    public ResponseEntity<?> completedMisiion(@RequestParam(required = false) Long id){
>>>>>>> 038d52771a52b520e0b8ba681968c7ca029844e6

        try{

            Mission mission = missionService.findById(id);
            if (mission == null) {
                throw new MissionNotFoundException("No se encontro una mission con el id " + id);
            }
            missionService.updateMission(mission);
            return ResponseEntity.ok(mission);

        }catch(MissionNotFoundException e){
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        }catch(Exception e){
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Ocurrió un error interno en el servidor");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        }
    }
}
