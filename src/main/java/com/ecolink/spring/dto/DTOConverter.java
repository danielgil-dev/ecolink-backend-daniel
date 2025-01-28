package com.ecolink.spring.dto;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.ecolink.spring.entity.Post;
import com.ecolink.spring.entity.Product;
import com.ecolink.spring.entity.Startup;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DTOConverter {

    private final ModelMapper modelMapper;

    public ProductDTO convertProductToDto(Product product) {
        return modelMapper.map(product, ProductDTO.class);
    }
    public StartupDTO convertStartupToDto(Startup startup) {
        return modelMapper.map(startup, StartupDTO.class);
    }
    public PostDTO convertPostToDTO(Post post){
        return modelMapper.map(post, PostDTO.class);
    }
}
