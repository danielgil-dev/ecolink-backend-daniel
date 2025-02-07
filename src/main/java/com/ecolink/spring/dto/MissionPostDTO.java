package com.ecolink.spring.dto;

import com.ecolink.spring.entity.MissionType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MissionPostDTO {
   
    private String name;
    private String description;
    private MissionType type;
    private Integer points;
}
