package com.ecolink.spring.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyProfileDTO {

    private String name;
    private String email;
    private String status;
    private String description;
    private List<ChallengeCompanyProfileDTO> listChallengesCompany;
    private List<PostProfileUserDTO> listLikePost;
    List<OrderDTO> orders;
    Long xp;
    Long nextLevelXp;
}
