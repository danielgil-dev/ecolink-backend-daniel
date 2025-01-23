package com.example.ecolink.ecolink.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecolink.ecolink.repository.ProposalRepository;

@Service
public class ProposalService {
    @Autowired
    private ProposalRepository proposalRepository;
}
