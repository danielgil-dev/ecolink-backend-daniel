package com.ecolink.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecolink.spring.entity.Admin;
import com.ecolink.spring.repository.AdminRepository;

@Service
public class AdminService {
    @Autowired
    private AdminRepository repository;

    public Admin findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    public void save(Admin admin) {
        repository.save(admin);
    }
}
