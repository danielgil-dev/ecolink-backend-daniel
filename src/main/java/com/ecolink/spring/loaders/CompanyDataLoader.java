package com.ecolink.spring.loaders;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.ecolink.spring.entity.Company;
import com.ecolink.spring.service.CompanyService;

@Component
@Order(5)
public class CompanyDataLoader implements CommandLineRunner  {

     @Autowired
     private CompanyService service;


    public void run (String... args){
        List<Company> companyList = Arrays.asList(
            new Company("EcoSolutions", "eco@solutions.com" ,1L),
            new Company("GreenTech", "contact@greentech.com", 2L),
            new Company("SolarInnovators", "info@solarinno.com" , 3L),
            new Company("BioFuture", "support@biofuture.com", 2L ),
            new Company("UrbanRenew", "urban@renew.com", 1L)
        );

        
        companyList.forEach(company -> {
            if (!service.existsByName(company.getName())) {
                service.save(company);
            }
        });
    }
}