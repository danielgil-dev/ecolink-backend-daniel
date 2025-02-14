package com.ecolink.spring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecolink.spring.dto.CompanyDTO;
import com.ecolink.spring.dto.DTOConverter;
import com.ecolink.spring.dto.StartupPublicProfileDTO;
import com.ecolink.spring.entity.Company;
import com.ecolink.spring.entity.Startup;
import com.ecolink.spring.entity.Status;
import com.ecolink.spring.exception.CompanyNotFoundException;
import com.ecolink.spring.exception.ErrorDetails;
import com.ecolink.spring.exception.StartupNotFoundException;
import com.ecolink.spring.service.CompanyService;
import com.ecolink.spring.service.StartupService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/api/admin")
@RequiredArgsConstructor
@RestController
public class AdminController {

    private final StartupService startupService;
    private final CompanyService companyService;
    private final DTOConverter dtoConverter;

    @PostMapping("/validate-startup/{id}")
    public ResponseEntity<?> validateAnStartup(@PathVariable Long id, @RequestParam(required = true) Status state) {

        try {

            Startup startup = startupService.findById(id);
            if (startup == null) {
                throw new StartupNotFoundException("No startup found with the id " + id);
            }
            startupService.changeStartupState(startup, state);

            startupService.save(startup);

            StartupPublicProfileDTO startupDto = dtoConverter.convertStartupProfileToDto(startup);

            return ResponseEntity.ok(startupDto);
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

    @PostMapping("/validate-company/{id}")
    public ResponseEntity<?> validateCompany(@PathVariable Long id, @RequestParam(required = true) Status state) {

        try {

            Company company = companyService.findById(id);
            if (company == null) {
                throw new CompanyNotFoundException("No company found with the id" + id);
            }
            companyService.changeCompanyState(company, state);

            companyService.save(company);

            CompanyDTO companyDto = dtoConverter.convertCompanyDTO(company);

            return ResponseEntity.ok(companyDto);
        } catch (CompanyNotFoundException e) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Ocurrió un error interno en el servidor");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);

        }
    }
}
