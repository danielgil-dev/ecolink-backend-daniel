package com.ecolink.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecolink.spring.dto.CompanyCompabilityDTO;
import com.ecolink.spring.dto.DTOConverter;
import com.ecolink.spring.dto.PaginationResponse;
import com.ecolink.spring.dto.StartupCompabilityDTO;
import com.ecolink.spring.entity.Company;
import com.ecolink.spring.entity.SortType;
import com.ecolink.spring.entity.Startup;
import com.ecolink.spring.entity.UserBase;
import com.ecolink.spring.service.CompanyService;
import com.ecolink.spring.service.CompatibilityService;
import com.ecolink.spring.service.StartupService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/compatibility")
public class CompatibilityController {

    private final CompatibilityService compatibilityService;
    private final DTOConverter dtoConverter;
    private final CompanyService companyService;
    private final StartupService startupService;

    public SortType parseStringToSortType(String orderType) {
        
        if (orderType.toUpperCase().trim().equals("ASC" )) {
            return SortType.ASC;
        } else {
            return SortType.DESC;
        }
    }

    @GetMapping
    public ResponseEntity<?> getUserCompabilities(@AuthenticationPrincipal UserBase user,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "DESC") String order,  
            @RequestParam(defaultValue = "") String country) {

        try {
            SortType orderType = parseStringToSortType(order);
            if (user instanceof Company) {
                Company company = companyService.getCompanyById(user.getId());
                
                Page<Startup> startupMatches = compatibilityService.findCompatibleStartupsForCompany(company, page,
                        size, orderType, country);

                List<StartupCompabilityDTO> startupMatchesDTO = startupMatches.getContent().stream()
                        .map(dtoConverter::convertStartupCompabilityDTO)
                        .collect(Collectors.toList());

                // Crear respuesta paginada
                PaginationResponse<StartupCompabilityDTO> response = new PaginationResponse<>(
                        startupMatchesDTO,
                        startupMatches.getNumber(),
                        startupMatches.getSize(),
                        startupMatches.getTotalElements(),
                        startupMatches.getTotalPages(),
                        startupMatches.isLast());

                return ResponseEntity.ok(response);

            } else if (user instanceof Startup) {
                Startup startup = startupService.findById(user.getId());
                Page<Company> companyMatches = compatibilityService.findCompatiblesCompanyForStartup(startup, page,
                        size, orderType, country);

                List<CompanyCompabilityDTO> companyMatchesDTO = companyMatches.getContent().stream()
                        .map(dtoConverter::convertCompanyCompabilityDTO)
                        .collect(Collectors.toList());

                // Crear respuesta paginada
                PaginationResponse<CompanyCompabilityDTO> response = new PaginationResponse<>(
                        companyMatchesDTO,
                        companyMatches.getNumber(),
                        companyMatches.getSize(),
                        companyMatches.getTotalElements(),
                        companyMatches.getTotalPages(),
                        companyMatches.isLast());

                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There is not matches for that user request");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
