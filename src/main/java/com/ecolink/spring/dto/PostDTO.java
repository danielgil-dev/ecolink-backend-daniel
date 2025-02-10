package com.ecolink.spring.dto;

import java.time.LocalDate;
import java.util.List;

import com.ecolink.spring.entity.Comment;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
public class PostDTO {
    private Long id;
    private String startupName;
    private String title;
    private String shortDescription;
    private String description;
    private Integer likesCount; 
    List<OdsDTO> odsList;
    private LocalDate postDate;
    private List<Comment> comments;
}
