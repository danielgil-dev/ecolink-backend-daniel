package com.ecolink.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecolink.spring.entity.Category;
import com.ecolink.spring.repository.CategoryRepository;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repository;

    public boolean existsByName(String name) {
        return repository.existsByName(name);
    }

    public boolean findByName(String name) {
        return repository.findByName(name);
    }

    public void save(Category category) {
        repository.save(category);
    }
}
