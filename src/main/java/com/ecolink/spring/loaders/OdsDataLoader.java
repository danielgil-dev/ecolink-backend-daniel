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
                new Ods("End of Poverty", "S-WEB-Goal-01.png"),
                new Ods("Zero Hunger", "S-WEB-Goal-02.png"),
                new Ods("Good Health and Well-being", "S-WEB-Goal-03.png"),
                new Ods("Quality Education", "S-WEB-Goal-04.png"),
                new Ods("Gender Equality", "S-WEB-Goal-05.png"),
                new Ods("Clean Water and Sanitation", "S-WEB-Goal-06.png"),
                new Ods("Affordable and Clean Energy", "S-WEB-Goal-07.png"),
                new Ods("Decent Work and Economic Growth", "S-WEB-Goal-08.png"),
                new Ods("Industry, Innovation, and Infrastructure", "S-WEB-Goal-09.png"),
                new Ods("Reduced Inequalities", "S-WEB-Goal-10.png"),
                new Ods("Sustainable Cities and Communities", "S-WEB-Goal-11.png"),
                new Ods("Responsible Consumption and Production", "S-WEB-Goal-12.png"),
                new Ods("Climate Action", "S-WEB-Goal-13.png"),
                new Ods("Life Below Water", "S-WEB-Goal-14.png"),
                new Ods("Life on Land", "S-WEB-Goal-15.png"),
                new Ods("Peace, Justice, and Strong Institutions", "S-WEB-Goal-16.png"),
                new Ods("Partnerships for the Goals", "S-WEB-Goal-17.png"));
        odsList.forEach(ods -> {
            if (!service.existByName(ods.getName())) {
                service.save(ods);
            }
        });
    }
}
