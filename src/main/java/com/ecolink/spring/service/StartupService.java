
package com.ecolink.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ecolink.spring.entity.Ods;
import com.ecolink.spring.entity.Product;
import com.ecolink.spring.entity.Startup;
import com.ecolink.spring.repository.StartupRepository;
import com.ecolink.spring.specification.ProductSpecification;
import com.ecolink.spring.specification.StartupSpecification;

@Service
public class StartupService {
    @Autowired
    private StartupRepository repository;

    public boolean existsByName(String name) {
        return repository.existsByName(name);
    }

    public void save(Startup startup) {
        repository.save(startup);
    }

    public Startup findById(Long id){
        return repository.findById(id).orElse(null);
    }

    public Startup findByName(String name) {
        return repository.findByName(name);
    }

    public List<Startup> findAll() {
        return repository.findAll();
    }

    public List<Startup> findTop3ByOrderByLevelDesc() {
        return repository.findTop3ByOrderByLevelDesc();
    }

    public Page<Startup> findByPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.findAll(pageable);
    }

    public List<Startup> findByFilter(String name, List<Ods> ods) {
         Specification<Startup> spec = StartupSpecification.filters(name, ods);
         return repository.findAll(spec);
    }
}
