package com.ecolink.spring.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecolink.spring.dto.CompanyDTO;
import com.ecolink.spring.dto.DTOConverter;
import com.ecolink.spring.entity.Company;
import com.ecolink.spring.entity.Ods;
import com.ecolink.spring.entity.UserBase;
import com.ecolink.spring.exception.CompanyNotFoundException;
import com.ecolink.spring.exception.ErrorDetails;
import com.ecolink.spring.exception.OdsNotFoundException;
import com.ecolink.spring.response.SuccessDetails;
import com.ecolink.spring.service.CompanyService;
import com.ecolink.spring.service.OdsService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/api/company")
@RequiredArgsConstructor
@RestController
public class CompanyController {

    private final CompanyService companyService;
    private final DTOConverter dtoConverter;
    private final OdsService odsService;

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

     @PutMapping("/ods/update")
    public ResponseEntity<?> updateOds(@AuthenticationPrincipal UserBase user, @RequestParam List<Long> odsList) {
        
        if (!(user instanceof Company)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User is not a company");
        }

        try {
            
            if (odsList == null || odsList.isEmpty()) {
                throw new OdsNotFoundException("There are no ODS ids to update");
                
            }
            
            Set<Long> odsSet = odsList.stream().collect(Collectors.toSet());
         
            List<Ods> newOdsUserPreferencesList = new ArrayList<>();
            odsSet.forEach(odsId -> {
                Ods ods = odsService.findById(odsId);
                if (ods != null ) {
                    newOdsUserPreferencesList.add(ods);

                }
            });

            if (newOdsUserPreferencesList.isEmpty() || newOdsUserPreferencesList.size() == 0) {
                throw new OdsNotFoundException("There is no ODS found with the given ids");
            }

            Company companyToUpdate = companyService.findById(user.getId());
            if (companyToUpdate == null) {
                throw new CompanyNotFoundException("There is no company with the given id");      
            }
            companyToUpdate.setOdsList(newOdsUserPreferencesList);
            companyService.save(companyToUpdate);
            SuccessDetails successDetails = new SuccessDetails(HttpStatus.OK.value(), "ODS updated successfully");
            return ResponseEntity.ok(successDetails);

        } catch(CompanyNotFoundException e){
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
        }catch(OdsNotFoundException e){
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
        }catch (Exception e) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Ocurrió un error interno en el servidor");
                    e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        }

    }
}

