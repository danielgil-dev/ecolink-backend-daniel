package com.ecolink.spring.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetUserFrontDTO {
    Long id;
    String name;
    String imageUrl;
    String location;
    Long level;
}
