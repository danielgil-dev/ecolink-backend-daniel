package com.ecolink.spring.dto;

import java.util.List;

import com.ecolink.spring.entity.Ods;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetUserFrontDTO {
    Long id;
    String name;
    String imageUrl;
    Long level;
}
