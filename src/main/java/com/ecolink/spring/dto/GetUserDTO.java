package com.ecolink.spring.dto;

import java.util.List;


import com.ecolink.spring.entity.Ods;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetUserDTO {
    Long id;
    String name;
    String imageUrl;
    Long level;
    List<Ods> preferences;
}
