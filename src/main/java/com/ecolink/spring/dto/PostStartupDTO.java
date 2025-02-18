package com.ecolink.spring.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostStartupDTO {
    private Long id;
    private String title;
    private String startupName;
    private String shortDescription;
    List<OdsWithoutIdDTO> odsList;
    private Integer likesCount;
    private Integer commentsCount;
    private LocalDate postDate;
}
