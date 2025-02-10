package com.ecolink.spring.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostItemPageDTO {
  private Long id;
    private String startupName;
    private String title;
    private String shortDescription;
    private Integer likesCount; 
    List<OdsDTO> odsList;
    private LocalDate postDate;
    private Integer numberComents;
}
