package com.ecolink.spring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecolink.spring.dto.DTOConverter;
import com.ecolink.spring.service.CompanyService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/company")
public class CompanyController {

    private final CompanyService companyService;
    private final DTOConverter dtoConverter;
}
