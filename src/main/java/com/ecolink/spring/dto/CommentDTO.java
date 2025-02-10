package com.ecolink.spring.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDTO {
    private Long id;
    private String message;
    private String imageUrl;
    private String name;
}
