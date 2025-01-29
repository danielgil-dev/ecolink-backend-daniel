package com.ecolink.spring.dto;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDTO {
    private String title;
    private String description;
    private LocalDate postDate;
}
