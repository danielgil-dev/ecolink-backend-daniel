package com.ecolink.spring.service;

import java.util.List;

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

    public Category findByName(String name) {
        return repository.findByName(name);
    }

    public void save(Category category) {
        repository.save(category);
    }

    public List<Category> findAll() {
        return repository.findAll();
    }

    public Category findById(Long categoryId) {
        return repository.findById(categoryId).orElse(null);
    }

    public List<Category> findAllById(List<Long> categoryIds) {
        return repository.findAllById(categoryIds);
    }
}
