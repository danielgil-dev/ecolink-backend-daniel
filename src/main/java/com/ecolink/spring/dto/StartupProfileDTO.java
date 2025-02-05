package com.ecolink.spring.dto;

import java.util.List;

import com.ecolink.spring.entity.Ods;
import com.ecolink.spring.entity.Product;
import com.ecolink.spring.entity.Proposal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StartupProfileDTO {
    String status;
    String name;
    String description;
    List<Ods> odsList;
    List<Proposal> proposals;
    List<Product> products;
}
