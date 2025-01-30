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

@Component
@Order(7)
public class ChallengeDataLoader implements CommandLineRunner {

        @Autowired
        private ChallengeService service;

        @Autowired
        private OdsService odsService;

        @Autowired
        private CompanyService companyService;

        public void run(String... args) {

                Company ecoVision = companyService.findByName("EcoVision");
                Company greenHorizon = companyService.findByName("GreenHorizon");
                Company solarPioneer = companyService.findByName("SolarPioneer");
                Company bioCraft = companyService.findByName("BioCraft");
                Company urbanFlow = companyService.findByName("UrbanFlow");
                List<Challenge> challenges = Arrays.asList(
                                new Challenge(ecoVision,
                                                "Eco-Friendly Manufacturing",
                                                "How can we make factory production greener? Propose simple solutions to reduce waste and emissions.",
                                                new BigDecimal("100000.00"),
                                                LocalDateTime.of(2025, 2, 4, 0, 0),
                                                Arrays.asList(
                                                                odsService.findByName("Climate Action"))),

                                new Challenge(greenHorizon,
                                                "Powering Remote Areas",
                                                "Create an easy-to-deploy solar power kit for small businesses or homes in off-grid areas.",
                                                new BigDecimal("150000.00"),
                                                LocalDateTime.of(2025, 2, 6, 0, 0),
                                                Arrays.asList(
                                                                odsService.findByName("Affordable and Clean Energy"),
                                                                odsService.findByName(
                                                                                "Industry, Innovation, and Infrastructure"))),

                                new Challenge(solarPioneer,
                                                "Water-Saving Tech",
                                                "How can we help cities or businesses use less water? Propose an affordable, easy-to-implement solution.",
                                                new BigDecimal("80000.00"),
                                                LocalDateTime.of(2025, 2, 2, 0, 0),
                                                Arrays.asList(
                                                                odsService.findByName("Clean Water and Sanitation"))),

                                new Challenge(bioCraft,
                                                "Farming Without Waste",
                                                "Design a solution to help farmers cut down food waste, whether through better storage, distribution, or alternative uses.",
                                                new BigDecimal("120000.00"),
                                                LocalDateTime.of(2025, 2, 2, 0, 0),
                                                Arrays.asList(
                                                                odsService.findByName("Zero Hunger"),
                                                                odsService.findByName("Life on Land"))),

                                new Challenge(urbanFlow,
                                                "The Future of Recycling",
                                                "What’s the next big idea in recycling? Think beyond traditional bins and propose something that makes waste separation fun and easy.",
                                                new BigDecimal("90000.00"),
                                                LocalDateTime.of(2025, 2, 3, 0, 0),
                                                Arrays.asList(
                                                                odsService.findByName(
                                                                                "Responsible Consumption and Production"))),

                                new Challenge(ecoVision,
                                                "Eco-Friendly Building Materials",
                                                "Suggest innovative materials or processes that reduce the carbon footprint of construction.",
                                                new BigDecimal("110000.00"),
                                                LocalDateTime.of(2025, 2, 3, 0, 0),
                                                Arrays.asList(
                                                                odsService.findByName(
                                                                                "Industry, Innovation, and Infrastructure"),
                                                                odsService.findByName(
                                                                                "Sustainable Cities and Communities"))),

                                new Challenge(greenHorizon,
                                                "Smarter Energy Storage",
                                                "How can we make storing solar and wind energy more efficient and affordable? Share your ideas!",
                                                new BigDecimal("140000.00"),
                                                LocalDateTime.of(2025, 2, 3, 0, 0),
                                                Arrays.asList(
                                                                odsService.findByName("Affordable and Clean Energy"),
                                                                odsService.findByName("Climate Action"))),

                                new Challenge(solarPioneer,
                                                "A New Era of Solar Panels",
                                                "Design a creative way to integrate solar panels into everyday life – beyond rooftops!",
                                                new BigDecimal("130000.00"),
                                                LocalDateTime.of(2025, 1, 31, 0, 0),
                                                Arrays.asList(
                                                                odsService.findByName("Affordable and Clean Energy"))),

                                new Challenge(bioCraft,
                                                "Bye-Bye Plastic!",
                                                "Can you develop an alternative to plastic that is cheap, scalable, and biodegradable?",
                                                new BigDecimal("115000.00"),
                                                LocalDateTime.of(2025, 2, 6, 0, 0),
                                                Arrays.asList(
                                                                odsService.findByName(
                                                                                "Responsible Consumption and Production"))),

                                new Challenge(urbanFlow,
                                                "Rethinking City Mobility",
                                                "What’s the next big idea in urban transportation? Help us make cities cleaner and more efficient.",
                                                new BigDecimal("105000.00"),
                                                LocalDateTime.of(2025, 2, 4, 0, 0),
                                                Arrays.asList(
                                                                odsService.findByName(
                                                                                "Sustainable Cities and Communities"))));

                challenges.forEach(challenge -> {
                        if (!service.existsByTitle(challenge.getTitle())) {
                                service.save(challenge);
                        }
                });
        }
}
