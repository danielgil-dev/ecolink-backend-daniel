package com.ecolink.spring.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MissionPostDTO {
    private String name;
    private String description;
    private String type;
    private Integer points;
}
