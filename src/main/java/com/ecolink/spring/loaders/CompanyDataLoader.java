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
public class CompanyDataLoader implements CommandLineRunner {

    @Autowired
    private CompanyService service;

    public void run(String... args) {
        List<Company> companyList = Arrays.asList(
                new Company("EcoVision", "eco@vision.com", 1L,
                        "Company dedicated to developing sustainable technologies for waste management and carbon footprint reduction."),
                
                new Company("GreenHorizon", "contact@greenhorizon.com", 2L,
                        "Startup specializing in renewable energy solutions, including high-efficiency solar panels and wind turbines."),
                
                new Company("SolarPioneer", "info@solarpioneer.com", 3L,
                        "Leading company in the design and manufacturing of innovative solar panels using next-generation materials."),
                
                new Company("BioCraft", "support@biocraft.com", 2L,
                        "Biotechnology company focused on creating bioplastics and biodegradable materials to reduce pollution."),
                
                new Company("UrbanFlow", "urban@flow.com", 1L,
                        "Initiative developing smart city solutions, including sustainable mobility and energy-efficient urban infrastructure.")
        );
        

        companyList.forEach(company -> {
            if (!service.existsByName(company.getName())) {
                service.save(company);
            }
        });
    }
}