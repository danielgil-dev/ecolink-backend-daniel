package com.ecolink.spring.loaders;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.ecolink.spring.entity.Challenge;
import com.ecolink.spring.entity.Company;
import com.ecolink.spring.service.ChallengeService;
import com.ecolink.spring.service.CompanyService;
import com.ecolink.spring.service.OdsService;

import jakarta.transaction.Transactional;

@Component
@Order(7)
public class ChallengeDataLoader implements CommandLineRunner {

    @Autowired
    private ChallengeService service;

    @Autowired
    private OdsService odsService;

    @Autowired
    private CompanyService companyService;

    @Transactional
    public void run(String... args) {


        Company ecoVision = companyService.findByName("EcoVision");
        Company greenHorizon = companyService.findByName("GreenHorizon");
        Company solarPioneer = companyService.findByName("SolarPioneer");
        Company bioCraft = companyService.findByName("BioCraft");
        Company urbanFlow = companyService.findByName("UrbanFlow");


        List<Challenge> challenges = Arrays.asList(
            new Challenge(ecoVision, "Eco-Friendly Manufacturing", "How can we make factory production greener?", new BigDecimal("100000.00"), LocalDateTime.of(2025, 2, 4, 0, 0), Arrays.asList(odsService.findByName("Climate Action"))),
            new Challenge(greenHorizon, "Powering Remote Areas", "Create an easy-to-deploy solar power kit.", new BigDecimal("150000.00"), LocalDateTime.of(2025, 2, 6, 0, 0), Arrays.asList(odsService.findByName("Affordable and Clean Energy"))),
            new Challenge(solarPioneer, "Water-Saving Tech", "How can we help cities or businesses use less water?", new BigDecimal("80000.00"), LocalDateTime.of(2025, 2, 2, 0, 0), Arrays.asList(odsService.findByName("Clean Water and Sanitation"))),
            new Challenge(bioCraft, "Farming Without Waste", "Design a solution to help farmers cut down food waste.", new BigDecimal("120000.00"), LocalDateTime.of(2025, 2, 2, 0, 0), Arrays.asList(odsService.findByName("Zero Hunger"))),
            new Challenge(urbanFlow, "The Future of Recycling", "What’s the next big idea in recycling?", new BigDecimal("90000.00"), LocalDateTime.of(2025, 2, 3, 0, 0), Arrays.asList(odsService.findByName("Responsible Consumption and Production")))
        );

        challenges.forEach(challenge -> {
            if (!service.existsByTitle(challenge.getTitle())) {
                service.save(challenge);
            }
        });

        challenges.forEach(challenge -> {
            Company company = challenge.getCompany();
            if (!company.getChallenges().contains(challenge)) {
                company.addChallenge(challenge);
                companyService.save(company); 
            }
        });
    }
}
