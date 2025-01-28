package com.ecolink.spring.controller;

import org.springframework.web.bind.annotation.RestController;

import com.ecolink.spring.entity.Product;
import com.ecolink.spring.service.ProductService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;

    @GetMapping("/product")
    public List<Product> getProducts() {
        return service.findAll();
    }
    

}
