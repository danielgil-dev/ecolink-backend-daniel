package com.ecolink.spring.controller;

import org.springframework.web.bind.annotation.RestController;

import com.ecolink.spring.dto.ProductDTO;
import com.ecolink.spring.dto.ProductRelevantDTO;
import com.ecolink.spring.dto.DTOConverter;
import com.ecolink.spring.dto.PaginationResponse;
import com.ecolink.spring.entity.Product;
import com.ecolink.spring.exception.ErrorDetails;
import com.ecolink.spring.exception.ProductNotFoundException;
import com.ecolink.spring.service.ProductService;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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
        try {
            List<Product> products = new ArrayList<>();
            if (products.isEmpty()) {
                throw new ProductNotFoundException("No se encontraron productos en la base de datos");
            }

            List<ProductDTO> dtoList = products.stream().map(dtoConverter::convertProductToDto)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(dtoList);
        } catch (ProductNotFoundException e) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
        } catch (Exception e) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Ocurrió un error interno en el servidor");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        }
    }

    @GetMapping("/filter")
    public ResponseEntity<?> getProductsWithFilters(
            @RequestParam(required = false) Long startup,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) BigDecimal priceMin,
            @RequestParam(required = false) BigDecimal priceMax) {

        try {
            List<Product> products = service.getProductsByFilter(startup, name, priceMin, priceMax);
            if (products.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDetails(HttpStatus.NOT_FOUND.value(), "No se encontraron productos con los filtros especificados"));
            }

            List<ProductDTO> dtoList = products.stream().map(dtoConverter::convertProductToDto)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(dtoList);
        } catch (ProductNotFoundException e) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
        } catch (Exception e) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Ocurrió un error interno en el servidor");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        }
    }

    @GetMapping("/pagination")
    public ResponseEntity<?> getStartups(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        try {
            Page<Product> products = service.findByPagination(page, size);
            
            if (products.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDetails(HttpStatus.NOT_FOUND.value(), "No se encontraron productos en la página especificada"));
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
        } catch (ProductNotFoundException e) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
        } catch (Exception e) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Ocurrió un error interno en el servidor");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        }
    }

    @GetMapping("/relevant")
    public ResponseEntity<?> getRelevantProducts() {
        List<Product> products = service.findTop4ByOrderByCreationDateDesc();
        if (products.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDetails(HttpStatus.NOT_FOUND.value(), "No se encontraron productos relevantes"));
        }
        List<ProductRelevantDTO> dtoList = products.stream().map(dtoConverter::convertProductRelevantToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }
}