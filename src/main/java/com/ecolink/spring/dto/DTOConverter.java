package com.ecolink.spring.dto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.ecolink.spring.entity.Challenge;
import com.ecolink.spring.entity.Client;
import com.ecolink.spring.entity.Company;
import com.ecolink.spring.entity.Mission;
import com.ecolink.spring.entity.Ods;
import com.ecolink.spring.entity.Post;
import com.ecolink.spring.entity.Product;
import com.ecolink.spring.entity.Proposal;
import com.ecolink.spring.entity.Startup;
import com.ecolink.spring.entity.UserBase;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DTOConverter {

    private final ModelMapper modelMapper;

    public ProductDTO convertProductToDto(Product product) {
        ProductDTO productDto = modelMapper.map(product, ProductDTO.class);
        String startupName = product.getStartup().getName();
        if( startupName != null){
            productDto.setStartupName(startupName);
        }
        return productDto;
    }

    public ProductRelevantDTO convertProductRelevantToDto(Product product) {
        return modelMapper.map(product, ProductRelevantDTO.class);
    }

    public ProposalDTO convertProposalToDto(Proposal proposal) {
        return modelMapper.map(proposal, ProposalDTO.class);
    }

    public ProposalStartupDTO convertProposalStartupToDto(Proposal proposal) {
        return modelMapper.map(proposal, ProposalStartupDTO.class);
    }

    public StartupDTO convertStartupToDto(Startup startup) {

        return modelMapper.map(startup, StartupDTO.class);
    }

    public StartupHomeDTO convertStartupHomeToDto(Startup startup) {
        return modelMapper.map(startup, StartupHomeDTO.class);
    }

    public StartupProfileDTO convertStartupProfileToDto(Startup startup) {
        return modelMapper.map(startup, StartupProfileDTO.class);
    }

    public PostDTO convertPostToDto(Post post) {

        PostDTO postDto = modelMapper.map(post, PostDTO.class);
        if (post.getLikes() != null && post.getLikes().size() > 0) {
            postDto.setLikesCount(post.getLikes().size());
        } else {
            postDto.setLikesCount(0);
        }

        return postDto;
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

    public GetUserDTO convertClientBaseToDto(Client client){
        return modelMapper.map(client, GetUserDTO.class);
    }
    public GetUserDTO convertStartupBaseToDto(Startup startup){
        return modelMapper.map(startup, GetUserDTO.class);
    }
    public GetUserDTO convertCompanypBaseToDto(Company company){
        return modelMapper.map(company, GetUserDTO.class);
    }

}