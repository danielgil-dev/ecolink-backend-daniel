package com.ecolink.spring.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProfileDTO {
    List<Long> odsIdList = new ArrayList<>();
    String description = "";
}
