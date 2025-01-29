package com.ecolink.spring.controller;

import org.springframework.web.bind.annotation.RestController;

import com.ecolink.spring.dto.ProductDTO;
import com.ecolink.spring.dto.DTOConverter;
import com.ecolink.spring.dto.PaginationResponse;
import com.ecolink.spring.entity.Product;
import com.ecolink.spring.service.ProductService;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/p")
    public ResponseEntity<?> getStartups(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<Product> products = service.findByPagination(page, size);
        if (products.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<ProductDTO> dtoList = products.stream().map(dtoConverter::convertProductToDto)
                .collect(Collectors.toList());

        var response = new PaginationResponse<>(
                dtoList,
                products.getNumber(),
                products.getSize(),
                products.getTotalElements(),
                products.getTotalPages(),
                products.isLast());

        return ResponseEntity.ok(response);
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