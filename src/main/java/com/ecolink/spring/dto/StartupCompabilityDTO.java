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
public class StartupCompabilityDTO {

    private Long id;
    private String name;
    private String imageUrl;
    private String type;
    private String compability;
    private String location;
    private List<OdsDTO> odsList;
}
