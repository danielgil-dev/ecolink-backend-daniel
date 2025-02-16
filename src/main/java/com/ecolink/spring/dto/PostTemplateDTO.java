package com.ecolink.spring.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostTemplateDTO {

    private String title;
    private String shortDescription;
    private String description;
    //private String imageUrl;
}
