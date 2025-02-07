package com.ecolink.spring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecolink.spring.dto.DTOConverter;
import com.ecolink.spring.dto.StartupProfileDTO;
import com.ecolink.spring.entity.Startup;
import com.ecolink.spring.entity.Status;
import com.ecolink.spring.exception.ErrorDetails;
import com.ecolink.spring.exception.StartupNotFoundException;
import com.ecolink.spring.service.StartupService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;


@RequestMapping("/api/admin")
@RequiredArgsConstructor
@RestController
public class AdminController {

    private final StartupService startupService;
    private final DTOConverter dtoConverter;

    @PostMapping("/validate-startup/{id}")
    public ResponseEntity<?> validateAnStartup(@PathVariable Long id, @RequestParam(required = true) Status state){

        try {
            
            Startup startup = startupService.findById(id);
            if (startup == null) {
                throw new StartupNotFoundException("No se encontro una startup con el id " + id);
            }
            startupService.changeStartupState(startup, state);
            StartupProfileDTO startupDto = dtoConverter.convertStartupProfileToDto(startup);
    
            return ResponseEntity.ok(startupDto);
        } catch (StartupNotFoundException  e) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);

        }catch(Exception e){
            System.out.println(e.getMessage());
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Ocurrió un error interno en el servidor");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);

        }


    }
}
