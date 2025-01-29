package com.ecolink.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.ecolink.spring.entity.Product;
import com.ecolink.spring.entity.Startup;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    Product findByNameAndStartup(String name, Startup startup);
    Boolean existsByNameAndStartup(String name, Startup startup);
    List<Product> findTop5ByOrderByCreationDateDesc();
}
