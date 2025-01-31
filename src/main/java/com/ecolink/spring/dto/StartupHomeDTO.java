package com.ecolink.spring.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StartupHomeDTO {
    Long id;
    String name;
    String imageUrl;
    Long level;
    String description;
    List<OdsDTO> odsList;
}