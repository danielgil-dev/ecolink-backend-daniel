package com.ecolink.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecolink.spring.entity.Company;
import com.ecolink.spring.repository.CompanyRepository;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository repository;
    
    public boolean existsByName(String name){
        return repository.existsByName(name);
    }
    public void save (Company company){
        repository.save(company);
    }

<<<<<<< HEAD
    public Company findByName (String name){
        return repository.findByName(name);

=======
    public Company findByName(String name){
        return repository.findByName(name);
>>>>>>> 9f160bc (Fixed id mapping and finish likes)
    }

   
}
