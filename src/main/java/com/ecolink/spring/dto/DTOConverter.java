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
        if (startupName != null) {
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
    public CompanyForChallengeDTO convertCompanyHomeToDto(Company company) {
        return modelMapper.map(company, CompanyForChallengeDTO.class);
    }

    public StartupProfileDTO convertStartupProfileToDto(Startup startup) {
        return modelMapper.map(startup, StartupProfileDTO.class);
    }

    public PostRelevantDTO convertPostRelevantToDTO(Post post) {
        return modelMapper.map(post, PostRelevantDTO.class);
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

    public ClientMissionDTO convertClientMissionDTO (Mission mission, boolean completed ){
        ClientMissionDTO clientMissionDto = modelMapper.map(mission, ClientMissionDTO.class);
        clientMissionDto.setCompleted(completed);
        return clientMissionDto;
    }

    public ChallengeDTO converChallengeToDto(Challenge challenge) {
        ChallengeDTO challengeDto = modelMapper.map(challenge, ChallengeDTO.class);
        Integer numberOfParticipants = challenge.getNumberOfParticipants();
        challengeDto.setNumberOfParticipans(numberOfParticipants > 0 ? numberOfParticipants : 0 );
        return challengeDto;
    }
    public ChallengeFindDTO converChallengeToChallengeFindDto(Challenge challenge) {
        ChallengeFindDTO challengeDto = modelMapper.map(challenge, ChallengeFindDTO.class);
        Company company = challenge.getCompany();
        CompanyForChallengeDTO companyDto = modelMapper.map(company, CompanyForChallengeDTO.class);
        challengeDto.setCompany(companyDto);
        Integer numberOfParticipants = challenge.getNumberOfParticipants();
        challengeDto.setNumberOfParticipans(numberOfParticipants > 0 ? numberOfParticipants : 0 );
        return challengeDto;
    }

    // Creo un DTO para solo almacenar el ID,Descripcion, y presupuesto de un reto
    public ChallengeBasicDTO converChallengeBasicToDTO(Challenge challenge) {
        ChallengeBasicDTO challengeDto = modelMapper.map(challenge, ChallengeBasicDTO.class);
        Integer numberOfParticipants = challenge.getNumberOfParticipants();
        challengeDto.setNumberOfParticipans(numberOfParticipants > 0 ? numberOfParticipants : 0 );
        return challengeDto;
    }

    public CompanyDTO convertCompanyDTO(Company company) {
        return modelMapper.map(company, CompanyDTO.class);
    }

    public OdsDTO convertOdsToDto(Ods ods) {
        return modelMapper.map(ods, OdsDTO.class);
    }

    public GetUserDTO convertUserDTO(UserBase user) {
        return modelMapper.map(user, GetUserDTO.class);
    }

    public GetUserFrontDTO convertClientBaseToDto(Client client) {
        return modelMapper.map(client, GetUserFrontDTO.class);
    }

    public GetUserFrontDTO convertStartupBaseToDto(Startup startup) {
        return modelMapper.map(startup, GetUserFrontDTO.class);
    }

    public GetUserFrontDTO convertCompanypBaseToDto(Company company) {
        return modelMapper.map(company, GetUserFrontDTO.class);
    }

}