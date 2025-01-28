package com.ecolink.spring.dto;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.ecolink.spring.entity.Product;
import com.ecolink.spring.entity.Startup;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductoDTOConverter {

    private final ModelMapper modelMapper;

    public ProductDTO convertProductToDto(Product product) {
        return modelMapper.map(product, ProductDTO.class);
    }
    public StartupDTO convertStartupToDto(Startup startup) {
        return modelMapper.map(startup, StartupDTO.class);
    }
}
