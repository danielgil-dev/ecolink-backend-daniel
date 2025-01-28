package com.ecolink.spring.controller;

import org.springframework.web.bind.annotation.RestController;

import com.ecolink.spring.dto.ProductDTO;
import com.ecolink.spring.dto.DTOConverter;
import com.ecolink.spring.entity.Product;
import com.ecolink.spring.service.ProductService;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {
    private final DTOConverter dtoConverter;
    private final ProductService service;

    @GetMapping()
    public ResponseEntity<?> getProducts() {
        List<Product> products = service.findAll();
        if (products.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            List<ProductDTO> dtoList = products.stream().map(dtoConverter::convertProductToDto)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(dtoList);
        }
    }

    @GetMapping("/relevant")
    public ResponseEntity<?> getRelevantProducts() {
        List<Product> products = service.findTop5ByOrderByCreationDateDesc();
        if (products.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<ProductDTO> dtoList = products.stream().map(dtoConverter::convertProductToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }
}