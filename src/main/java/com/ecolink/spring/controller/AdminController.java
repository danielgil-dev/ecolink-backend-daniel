package com.ecolink.spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecolink.spring.dto.CompanyDTO;
import com.ecolink.spring.dto.DTOConverter;
import com.ecolink.spring.dto.StartupPublicProfileDTO;
import com.ecolink.spring.dto.UserPendingDTO;
import com.ecolink.spring.entity.Company;
import com.ecolink.spring.entity.Startup;
import com.ecolink.spring.entity.Status;
import com.ecolink.spring.entity.UserBase;
import com.ecolink.spring.entity.UserType;
import com.ecolink.spring.exception.CompanyNotFoundException;
import com.ecolink.spring.exception.ErrorDetails;
import com.ecolink.spring.exception.StartupNotFoundException;
import com.ecolink.spring.service.CompanyService;
import com.ecolink.spring.service.EmailServiceImpl;
import com.ecolink.spring.service.StartupService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@RequestMapping("/api/admin")
@RequiredArgsConstructor
@RestController
public class AdminController {

    private final StartupService startupService;
    private final CompanyService companyService;
    private final DTOConverter dtoConverter;
    private final EmailServiceImpl emailService;

    @PostMapping("/validate-startup/{id}")
    public ResponseEntity<?> validateAnStartup(@AuthenticationPrincipal UserBase user, @PathVariable Long id,
            @RequestParam(required = true) Status state) {

        try {

            if (user == null) {
                throw new Exception("No user found");
            }

            if (!user.getUserType().equals(UserType.ADMIN)) {
                throw new Exception("You are not an admin");

            }

            Startup startup = startupService.findById(id);
            if (startup == null) {
                throw new StartupNotFoundException("No startup found with the id " + id);
            }
            startupService.changeStartupState(startup, state);

            startupService.save(startup);

            switch (state) {
                case ACCEPTED:
                    emailService.sendAccountAccepted(startup.getEmail());
                    break;
                case REJECTED:
                    emailService.sendAccountRejected(startup.getEmail());
                    break;
                case PENDING:
                    emailService.sendAccountPending(startup.getEmail());
                    break;
            }

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
    public ResponseEntity<?> validateCompany(@AuthenticationPrincipal UserBase user, @PathVariable Long id,
            @RequestParam(required = true) Status state) {

        try {

            if (user == null) {
                throw new Exception("No user found");
            }

            if (!user.getUserType().equals(UserType.ADMIN)) {
                throw new Exception("You are not an admin");
            }

            if (state == null) {
                throw new Exception("State is required");
            }

            Company company = companyService.findById(id);
            if (company == null) {
                throw new CompanyNotFoundException("No company found with the id" + id);
            }

            companyService.changeCompanyState(company, state);

            companyService.save(company);

            switch (state) {
                case ACCEPTED:
                    emailService.sendAccountAccepted(company.getEmail());
                    break;
                case REJECTED:
                    emailService.sendAccountRejected(company.getEmail());
                    break;
                case PENDING:
                    emailService.sendAccountPending(company.getEmail());
                    break;
            }

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

    @GetMapping("/user/pending")
    public ResponseEntity<?> getPendingUsers(@AuthenticationPrincipal UserBase user) {
        try {

            if (user == null) {
                throw new Exception("No user found");
            }

            if (!user.getUserType().equals(UserType.ADMIN)) {
                throw new Exception("You are not an admin");
            }

            List<Startup> startupsPending = startupService.findByState(Status.PENDING);
            List<Company> companiesPending = companyService.findByState(Status.PENDING);

            List<UserPendingDTO> userPendingDtos = new ArrayList<>();

            if (startupsPending != null && !startupsPending.isEmpty()) {
                for (Startup startup : startupsPending) {
                    userPendingDtos.add(dtoConverter.convertStartupToUserPendingDto(startup));
                }
            }

            if (companiesPending != null && !companiesPending.isEmpty()) {
                System.out.println("Companies pending: " + companiesPending.size());
                for (Company company : companiesPending) {
                    System.out.println(company.getEmail());
                    userPendingDtos.add(dtoConverter.convertCompanyToUserPendingDto(company));
                }
            }

            return ResponseEntity.ok(userPendingDtos);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Ocurrió un error interno en el servidor");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);

        }
    }

}
