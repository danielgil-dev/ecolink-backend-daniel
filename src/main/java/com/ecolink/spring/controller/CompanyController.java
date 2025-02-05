package com.ecolink.spring.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecolink.spring.dto.CompanyDTO;
import com.ecolink.spring.dto.DTOConverter;
import com.ecolink.spring.entity.Company;
import com.ecolink.spring.exception.CompanyNotFoundException;
import com.ecolink.spring.exception.ErrorDetails;
import com.ecolink.spring.service.CompanyService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/api/company")
@RequiredArgsConstructor
@RestController
public class CompanyController {

    private final CompanyService companyService;
    private final DTOConverter dtoConverter;

    @GetMapping
    public ResponseEntity<?> getAllCompanies() {

        try {
            List<Company> companies = companyService.getAllCompanies();
            if (companies == null || companies.isEmpty()) {
                throw new CompanyNotFoundException("No se encontraron empresas en la base de datos");
            }

            List<CompanyDTO> dtoList = companies.stream().map(dtoConverter::convertCompanyDTO)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(dtoList);

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

    @GetMapping("/{id}")
    public ResponseEntity<?> getCompany(@PathVariable Long id) {

        try {
            Company company = companyService.getCompanyById(id);
            if (company == null) {
                throw new CompanyNotFoundException("No se encontro ninguna empresa por el id: " + id);
            }

            CompanyDTO dtoCompany = dtoConverter.convertCompanyDTO(company);
            return ResponseEntity.ok(dtoCompany);
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
