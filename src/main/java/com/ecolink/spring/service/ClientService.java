package com.ecolink.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecolink.spring.entity.Client;
import com.ecolink.spring.repository.ClientRepository;

@Service
public class ClientService {
    @Autowired
    private ClientRepository repository;

    public Client findById(Long id){
        return repository.findById(id).orElse(null);
    }
    
    public boolean existByEmail(String email){
        return repository.existsByEmail(email);
    }

    public void save(Client client){
        repository.save(client);
    }

    public Client findByEmail(String email) {
       return repository.findByEmail(email);
    }
}
