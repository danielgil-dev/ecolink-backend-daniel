package com.ecolink.spring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ecolink.spring.dto.DTOConverter;
import com.ecolink.spring.dto.GetUserDTO;
import com.ecolink.spring.entity.Client;
import com.ecolink.spring.entity.Company;
import com.ecolink.spring.entity.Ods;
import com.ecolink.spring.entity.Startup;
import com.ecolink.spring.entity.UserBase;
import com.ecolink.spring.entity.UserType;
import com.ecolink.spring.service.OdsService;
import com.ecolink.spring.service.UserBaseService;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserBaseController {
    private final UserBaseService service;
    private final OdsService odsService;
    private final DTOConverter dtoConverter;

    @PostMapping("/register")
    public ResponseEntity<GetUserDTO> newUser(@RequestBody UserBase user) {
        try {
            GetUserDTO dto = null;
            List<Ods> preferences = odsService.findAllById(user.getPreferences().stream()
                    .map(Ods::getId)
                    .collect(Collectors.toList()));

            user.setPreferences(preferences);
            user.setLikes(new ArrayList<>());
            user.setRegisterDate(java.time.LocalDate.now());
            user.setLevel(0L);

            if (user instanceof Startup) {
                user.setUserType(UserType.STARTUP);
                Startup startup = (Startup) user;
                startup.setProposals(new ArrayList<>());
                startup.setProducts(new ArrayList<>());
                List<Ods> odsList = odsService.findAllById(startup.getOdsList().stream()
                        .map(Ods::getId)
                        .collect(Collectors.toList()));
                startup.setOdsList(odsList);
                dto = dtoConverter.convertStartupBaseToDto(startup);
            } else if (user instanceof Company) {
                Company company = (Company) user;
                user.setUserType(UserType.COMPANY);
                dto = dtoConverter.convertCompanypBaseToDto(company);
            } else {
                Client client = (Client) user;
                user.setUserType(UserType.CLIENT);
                dto = dtoConverter.convertClientBaseToDto(client);
            }

            service.newUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(dto);
        } catch (

        DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
