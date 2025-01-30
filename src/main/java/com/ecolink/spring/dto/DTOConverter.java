package com.ecolink.spring.dto;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.ecolink.spring.entity.Challenge;
import com.ecolink.spring.entity.Mission;
import com.ecolink.spring.entity.Ods;
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

    public ProductRelevantDTO convertProductRelevantToDto(Product product) {
        return modelMapper.map(product, ProductRelevantDTO.class);
    }

    public StartupHomeDTO convertStartupToDto(Startup startup) {
        return modelMapper.map(startup, StartupHomeDTO.class);
    }

    public PostDTO convertPostToDto(Post post) {
        return modelMapper.map(post, PostDTO.class);
    }

    public MissionDTO convertMissionToDto(Mission mission) {
        return modelMapper.map(mission, MissionDTO.class);
    }

    public ChallengeDTO converChallengeToDto(Challenge challenge) {
        return modelMapper.map(challenge, ChallengeDTO.class);
    }

    public OdsDTO convertOdsToDto(Ods ods) {
        return modelMapper.map(ods, OdsDTO.class);
    }

}