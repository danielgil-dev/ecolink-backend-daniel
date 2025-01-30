package com.ecolink.spring.dto;

import java.time.LocalDate;
import java.util.List;

import com.ecolink.spring.entity.Like;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDTO {
    private Long id;
    private String title;
    private String description;
    private String imageUrl;
    List<Like> likes;
    private LocalDate postDate;
}
