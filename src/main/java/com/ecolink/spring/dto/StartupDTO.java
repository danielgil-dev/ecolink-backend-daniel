package com.ecolink.spring.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StartupDTO {
    Long id;
    String name;
    String imageUrl;
    Long level;
    String description;
    List<OdsDTO> odsList;
    List<ProposalStartupDTO> proposals;
}
