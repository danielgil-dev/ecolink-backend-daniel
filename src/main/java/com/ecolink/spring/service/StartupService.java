
package com.ecolink.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ecolink.spring.entity.Startup;
import com.ecolink.spring.repository.StartupRepository;

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

    public Startup findByName(String name) {
        return repository.findByName(name);
    }

    public List<Startup> findAll() {
        return repository.findAll();
    }

    public List<Startup> findTop5ByOrderByLevelDesc() {
        return repository.findTop5ByOrderByLevelDesc();
    }

    public Page<Startup> findByPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.findAll(pageable);
    }
}
