package com.ecolink.spring.dto;

import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CompanyDTO {

    private Long id;
    private String name;
    private Long level;
    private String description;
    private List<ChallengeBasicDTO> challenges;
    
}
