package com.ecolink.spring.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecolink.spring.dto.DTOConverter;
import com.ecolink.spring.dto.PaginationResponse;
import com.ecolink.spring.dto.StartupHomeDTO;
import com.ecolink.spring.dto.StartupPrivateProfileDTO;
import com.ecolink.spring.dto.StartupPublicProfileDTO;
import com.ecolink.spring.entity.Ods;
import com.ecolink.spring.entity.Proposal;
import com.ecolink.spring.entity.Startup;
import com.ecolink.spring.exception.ErrorDetails;
import com.ecolink.spring.exception.StartupNotFoundException;
import com.ecolink.spring.service.OdsService;
import com.ecolink.spring.service.StartupService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/startup") 
public class StartupController {
    private final DTOConverter dtoConverter;
    private final StartupService service;
    private final OdsService odsService;

    @GetMapping()
    public ResponseEntity<?> getStartups(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "8") int size,
            @RequestParam(required = false) List<Long> odsIdList) {

        try {
            List<Ods> odsList = new ArrayList<>();

            if (odsIdList != null && !odsIdList.isEmpty()) {
                odsIdList.forEach(odsId -> {
                    Ods ods = odsService.findById(odsId);
                    if (ods != null) {
                        odsList.add(ods);
                    }
                });
            }

            Page<Startup> startups = service.findByFilterAndPagination(name, odsList, page, size);

            if (startups.isEmpty()) {
                throw new StartupNotFoundException("No se encontraron startups en la página especificada");
            }
            ;

            List<StartupHomeDTO> dtoList = startups.getContent().stream().map(dtoConverter::convertStartupHomeToDto)
                    .collect(Collectors.toList());

            var response = new PaginationResponse<>(
                    dtoList,
                    startups.getNumber(),
                    startups.getSize(),
                    startups.getTotalElements(),
                    startups.getTotalPages(),
                    startups.isLast());

            return ResponseEntity.ok(response);
        } catch (StartupNotFoundException e) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Ocurrió un error interno en el servidor");

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);

        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStartup(@PathVariable Long id) {
        try {
            Startup startup = service.findById(id);
            if (startup == null) {
                throw new StartupNotFoundException("No existe la startup con id=" + id);
            }
            List<Proposal> startupProposal = startup.getProposals();
            if (startupProposal != null) {
                startupProposal.forEach(proposal -> {

                    System.out.println("Propuestas de startup: " + proposal.getDescription());
                });
            }else{
                System.out.println("No tiene ");
            }
            StartupPublicProfileDTO dto = dtoConverter.convertStartupProfileToDto(startup);
            
            return ResponseEntity.ok(dto);
        } catch (StartupNotFoundException e) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
        } catch (Exception e) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Ocurrió un error interno en el servidor");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        }
    }

    
    @GetMapping("/profile/{id}")
    public ResponseEntity<?> getStartupProfile(@PathVariable Long id) {
        try {
            Startup startup = service.findById(id);
            if (startup == null) {
                throw new StartupNotFoundException("No existe la startup con id=" + id);
            }
            List<Proposal> startupProposal = startup.getProposals();
            if (startupProposal != null) {
                startupProposal.forEach(proposal -> {

                    System.out.println("Propuestas de startup: " + proposal.getDescription());
                });
            }else{
                System.out.println("No tiene ");
            }
            StartupPrivateProfileDTO dto = dtoConverter.convertStartupToStartupPrivateProfile(startup);
            
            return ResponseEntity.ok(dto);
        } catch (StartupNotFoundException e) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
        } catch (Exception e) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Ocurrió un error interno en el servidor");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        }
    }


    @GetMapping("/home")
    public ResponseEntity<?> getRelevantStartups() {

        try {
            List<Startup> startups = service.findTop3ByOrderByLevelDesc();
            if (startups.isEmpty()) {
                throw new StartupNotFoundException("No se encontraron startups relevantes");
            }

            List<StartupHomeDTO> dtoList = startups.stream().map(dtoConverter::convertStartupHomeToDto)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(dtoList);
        } catch (StartupNotFoundException e) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
        } catch (Exception e) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Ocurrió un error interno en el servidor");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        }
    }
}