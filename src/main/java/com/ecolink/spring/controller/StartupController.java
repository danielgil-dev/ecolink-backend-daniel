package com.ecolink.spring.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecolink.spring.dto.DTOConverter;
import com.ecolink.spring.dto.StartupDTO;
import com.ecolink.spring.entity.Startup;
import com.ecolink.spring.service.StartupService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/startup")
public class StartupController {
    private final DTOConverter productoDTOConverter;
    private final StartupService service;

    @GetMapping()
    public ResponseEntity<?> getStartups() {
        List<Startup> startups = service.findAll();
        if (startups.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<StartupDTO> dtoList = startups.stream().map(productoDTOConverter::convertStartupToDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtoList);
    }
}
