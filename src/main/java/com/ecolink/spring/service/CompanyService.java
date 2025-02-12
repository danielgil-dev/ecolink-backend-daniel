package com.ecolink.spring.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecolink.spring.entity.Company;
import com.ecolink.spring.entity.Status;
import com.ecolink.spring.repository.CompanyRepository;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository repository;

    public boolean existsByName(String name) {
        return repository.existsByName(name);
    }

    public void save(Company company) {
        repository.save(company);
    }

    public Company findByName(String name) {
        return repository.findByName(name);
    }

    public List<Company> getAllCompanies() {
        return repository.findAll();
    }

    public Company getCompanyById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Company findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void changeCompanyState(Company company, Status state) {

        if (company.getStatus() == Status.PENDING && state == Status.ACCEPTED) {
            company.setStatus(state);
        } else if (company.getStatus() == Status.PENDING && state == Status.REJECTED) {
            company.setStatus(state);
        } else {
            company.setStatus(Status.PENDING);
        }

    }
}
