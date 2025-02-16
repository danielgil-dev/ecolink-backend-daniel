package com.ecolink.spring.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPendingDTO {
    private Long id;
    private String name;
    private String imageUrl;
    private String email;
}
