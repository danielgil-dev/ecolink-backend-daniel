package com.ecolink.spring.dto;

import com.ecolink.spring.entity.MissionType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ClientMissionDTO {
    
    private Long clienteId;
    private Long missionId;
    private String name;
    private String Missiondescription;
    private MissionType Missiontype;
    private Integer Missionpoints;
    private Boolean completed;

}
