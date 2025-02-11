package com.ecolink.spring.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecolink.spring.dto.CategoryDTO;
import com.ecolink.spring.dto.DTOConverter;
import com.ecolink.spring.dto.OdsDTO;
import com.ecolink.spring.entity.Category;
import com.ecolink.spring.entity.Ods;
import com.ecolink.spring.exception.ErrorDetails;
import com.ecolink.spring.exception.OdsNotFoundException;
import com.ecolink.spring.service.CategoryService;
import com.ecolink.spring.service.OdsService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryController {
    private final CategoryService service;
    private final DTOConverter dtoConverter;

    @GetMapping
    public ResponseEntity<?> getAllOds() {
        try {
            List<Category> categories = service.findAll();
            if (categories.isEmpty()) {
                throw new OdsNotFoundException("No hay Categorias en la base de datos");
            }
            List<CategoryDTO> dtoList = categories.stream().map(dtoConverter::convertCategoryToDto)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(dtoList);
        } catch (OdsNotFoundException e) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        } catch (Exception e) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Ocurrió un error interno en el servidor");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMethodName(@PathVariable Long id) {
        try {
            Ods ods = service.findById(id);
            if (ods == null) {
                throw new OdsNotFoundException("No existe una ods con id=" + id);
            }
            OdsDTO dto = odsDTOConverter.convertOdsToDto(ods);

            return ResponseEntity.ok(dto);
        } catch (OdsNotFoundException e) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
        } catch (Exception e) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Ocurrió un error interno en el servidor");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        }
    }

}
