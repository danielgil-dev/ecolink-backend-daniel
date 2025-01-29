package com.ecolink.spring.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecolink.spring.dto.DTOConverter;
import com.ecolink.spring.dto.PaginationResponse;
import com.ecolink.spring.dto.StartupDTO;
import com.ecolink.spring.entity.Startup;
import com.ecolink.spring.service.StartupService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/startup")
public class StartupController {
    private final DTOConverter dtoConverter;
    private final StartupService service;

    @GetMapping()
    public ResponseEntity<?> getStartups() {
        List<Startup> startups = service.findAll();
        if (startups.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<StartupDTO> dtoList = startups.stream().map(dtoConverter::convertStartupToDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/pagination")
    public ResponseEntity<?> getStartups(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<Startup> startups = service.findByPagination(page, size);
        if (startups.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<StartupDTO> dtoList = startups.stream().map(dtoConverter::convertStartupToDto)
                .collect(Collectors.toList());

        var response = new PaginationResponse<>(
                dtoList,
                startups.getNumber(),
                startups.getSize(),
                startups.getTotalElements(),
                startups.getTotalPages(),
                startups.isLast());

        return ResponseEntity.ok(response);

    }

    @GetMapping("/relevant")
    public ResponseEntity<?> getRelevantStartups() {
        List<Startup> startups = service.findTop5ByOrderByLevelDesc();
        if (startups.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<StartupDTO> dtoList = startups.stream().map(dtoConverter::convertStartupToDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtoList);
    }

}
