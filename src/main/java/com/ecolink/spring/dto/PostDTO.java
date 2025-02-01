package com.ecolink.spring.dto;

import java.time.LocalDate;
import java.util.List;

import com.ecolink.spring.entity.Like;
import com.ecolink.spring.entity.Ods;
import com.ecolink.spring.entity.Startup;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDTO {
    private Long id;
    private String startupName;
    private String title;
    private String description;
    private Integer likesCount; 
    List<OdsDTO> odsList;
    private LocalDate postDate;
}
