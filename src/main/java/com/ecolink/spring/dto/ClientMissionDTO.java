package com.ecolink.spring.dto;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ClientMissionDTO {
    
    private Long id;
    private String name;
    private String description;
    private String type;
    private Integer points;
    private Boolean completed;
    

}
