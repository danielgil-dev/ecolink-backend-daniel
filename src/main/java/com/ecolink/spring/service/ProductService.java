package com.ecolink.spring.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ecolink.spring.entity.Ods;
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

    public List<Product> getProductsByFilter(Long id_startup, String name, BigDecimal priceMin,
            BigDecimal priceMax) {
        Specification<Product> spec = ProductSpecification.filters(id_startup, name, priceMin, priceMax);
        return repository.findAll(spec);
    }

    public Product findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Page<Product> findByPaginationAndFilter(Long startup, String name, BigDecimal priceMin, BigDecimal priceMax,
            int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Specification<Product> spec = ProductSpecification.filters(startup, name, priceMin, priceMax);
        return repository.findAll(spec, pageable);
    }
}
