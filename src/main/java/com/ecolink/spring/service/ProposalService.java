package com.ecolink.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecolink.spring.entity.Proposal;
import com.ecolink.spring.repository.ProposalRepository;

@Service
public class ProposalService {
    @Autowired
    private ProposalRepository repository;


    public Boolean existsById(Long id){

        return repository.existsById(id);
    }

    public void save (Proposal proposal){
         repository.save(proposal);
    }
}
