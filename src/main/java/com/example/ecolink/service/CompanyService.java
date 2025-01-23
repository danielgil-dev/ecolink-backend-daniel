package com.example.ecolink.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecolink.ecolink.repository.CompanyRepository;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository repository;
}
