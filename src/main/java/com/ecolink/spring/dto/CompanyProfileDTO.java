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
    private String location;
    private List<OdsDTO> userOdsList;
    private List<ChallengeCompanyProfileDTO> listChallengesCompany;
    private List<PostProfileUserDTO> listLikePost;
    private List<OrderDTO> orders;
    private Long level;
    private Long xp;
    private Long nextLevelXp;
}
