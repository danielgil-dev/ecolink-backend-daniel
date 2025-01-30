package com.ecolink.spring.loaders;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.ecolink.spring.entity.Ods;
import com.ecolink.spring.service.OdsService;

@Component
@Order(1)
public class OdsDataLoader implements CommandLineRunner {
    @Autowired
    private OdsService service;

    @Override
    public void run(String... args) throws Exception {
        List<Ods> odsList = Arrays.asList(
                new Ods("End of Poverty"),
                new Ods("Zero Hunger"),
                new Ods("Good Health and Well-being"),
                new Ods("Quality Education"),
                new Ods("Gender Equality"),
                new Ods("Clean Water and Sanitation"),
                new Ods("Affordable and Clean Energy"),
                new Ods("Decent Work and Economic Growth"),
                new Ods("Industry, Innovation, and Infrastructure"),
                new Ods("Reduced Inequalities"),
                new Ods("Sustainable Cities and Communities"),
                new Ods("Responsible Consumption and Production"),
                new Ods("Climate Action"),
                new Ods("Life Below Water"),
                new Ods("Life on Land"),
                new Ods("Peace, Justice, and Strong Institutions"),
                new Ods("Partnerships for the Goals"));

        odsList.forEach(ods -> {
            if (!service.existByName(ods.getName())) {
                service.save(ods);
            }
        });
    }
}
