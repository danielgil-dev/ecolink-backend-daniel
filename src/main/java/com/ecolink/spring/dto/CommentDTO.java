package com.ecolink.spring.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDTO {
    private Long id;
    private Long id_user;
    private String comment;
    private String imageUrl;
    private String name;
}
