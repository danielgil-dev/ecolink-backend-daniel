package com.ecolink.spring.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ecolink.spring.entity.Category;
import com.ecolink.spring.entity.Product;
import com.ecolink.spring.entity.Startup;
import com.ecolink.spring.repository.ProductRepository;
import com.ecolink.spring.specification.ProductSpecification;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public void save(Product product) {
        repository.save(product);
    }

    public Product findByNameAndStartup(String name, Startup startup) {
        return repository.findByNameAndStartup(name, startup);
    }

    public Boolean existsByNameAndStartup(String name, Startup startup) {
        return repository.existsByNameAndStartup(name, startup);
    }

    public List<Product> findAll() {
        return repository.findAll();
    }

    public List<Product> findTop4ByOrderByCreationDateDesc() {
        return repository.findTop4ByOrderByCreationDateDesc();
    }

    public Page<Product> findByPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.findAll(pageable);
    }


    public Product findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Page<Product> findByPaginationAndFilter(Long startup, String name, List<Category> categories,
            BigDecimal pricemin, BigDecimal pricemax,
            int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Specification<Product> spec = ProductSpecification.filters(startup, name, categories, pricemin, pricemax);
        return repository.findAll(spec, pageable);
    }

    public void delete(Product product) {
        repository.delete(product);
    }
}
