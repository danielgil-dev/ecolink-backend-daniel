package com.ecolink.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecolink.spring.repository.ClientRepository;

@Service
public class ClientService {
    @Autowired
    private ClientRepository repository;

}
