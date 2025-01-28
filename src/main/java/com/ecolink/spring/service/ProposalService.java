package com.ecolink.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecolink.spring.repository.ProposalRepository;

@Service
public class ProposalService {
    @Autowired
    private ProposalRepository repository;
}
