package com.ecolink.spring.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MissionDTO {

    private Long id;
    private String name;
    private String description;
    private String type;
    private Integer points;
    private Boolean completed;

}
