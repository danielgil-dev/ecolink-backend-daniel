package com.ecolink.spring.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private MissionType type;
    private Integer points;
    
    

    public Mission(String name, String description, MissionType type, Integer points, Boolean completed){
        this.name = name;
        this.description = description;
        this.type = type;
        this.points = points;
    }


    
}
