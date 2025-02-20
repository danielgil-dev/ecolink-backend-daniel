package com.ecolink.spring.loaders;

import java.math.BigDecimal;
import java.time.LocalDate;
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
@Order(8)
public class ChallengeDataLoader implements CommandLineRunner {

    @Autowired
    private ChallengeService challengeService;

    @Autowired
    private OdsService odsService;

    @Autowired
    private CompanyService companyService;

    @Transactional
    @Override
    public void run(String... args) {

        Company tesla = companyService.findByName("Tesla");
        Company google = companyService.findByName("Google");
        Company apple = companyService.findByName("Apple");
        Company microsoft = companyService.findByName("Microsoft");
        Company amazon = companyService.findByName("Amazon");

        // Requisitos y beneficios "generales"
        String generalRequirement1 = "Experience in sustainable technologies or environmental solutions.";
        String generalRequirement2 = "Knowledge of innovative materials and energy-efficient systems.";
        String generalRequirement3 = "Ability to design and prototype scalable eco-friendly solutions.";

        String generalBenefit1 = "Access to funding and mentorship from industry experts.";
        String generalBenefit2 = "Opportunity to pilot and implement solutions in real-world scenarios.";
        String generalBenefit3 = "Networking and collaboration with leaders in sustainability and innovation.";

        Challenge teslaChallenge1 = new Challenge(
            tesla,
            "Electric Vehicle Innovation",
            "How can we improve the efficiency and range of electric vehicles? Propose innovative solutions.",
            "Propose innovative solutions to improve the efficiency and range of electric vehicles.",
            new BigDecimal("200000.00"),
            LocalDate.of(2025, 3, 15),
            Arrays.asList(odsService.findByName("Affordable and Clean Energy"))
        );

        teslaChallenge1.addRequirement(generalRequirement1);
        teslaChallenge1.addRequirement(generalRequirement2);
        teslaChallenge1.addRequirement(generalRequirement3);
        teslaChallenge1.addRequirement("Experience with electric vehicle technology or battery systems.");

        teslaChallenge1.addBenefit(generalBenefit1);
        teslaChallenge1.addBenefit(generalBenefit2);
        teslaChallenge1.addBenefit(generalBenefit3);
        teslaChallenge1.addBenefit("Opportunity to collaborate with Tesla's R&D team.");

        Challenge teslaChallenge2 = new Challenge(
            tesla,
            "Sustainable Energy Storage",
            "Develop advanced energy storage solutions for renewable energy systems.",
            "Advanced energy storage solutions for renewable energy systems.",
            new BigDecimal("250000.00"),
            LocalDate.of(2025, 4, 10),
            Arrays.asList(
                odsService.findByName("Affordable and Clean Energy"),
                odsService.findByName("Climate Action")
            )
        );

        teslaChallenge2.addRequirement(generalRequirement1);
        teslaChallenge2.addRequirement(generalRequirement2);
        teslaChallenge2.addRequirement(generalRequirement3);
        teslaChallenge2.addRequirement("Knowledge of battery technologies and energy storage systems.");

        teslaChallenge2.addBenefit(generalBenefit1);
        teslaChallenge2.addBenefit(generalBenefit2);
        teslaChallenge2.addBenefit(generalBenefit3);
        teslaChallenge2.addBenefit("Access to Tesla's energy storage facilities for testing.");

        Challenge googleChallenge1 = new Challenge(
            google,
            "AI for Climate Change",
            "How can AI be used to combat climate change? Propose innovative applications.",
            "Innovative applications of AI to combat climate change.",
            new BigDecimal("300000.00"),
            LocalDate.of(2025, 5, 20),
            Arrays.asList(odsService.findByName("Climate Action"))
        );

        googleChallenge1.addRequirement(generalRequirement1);
        googleChallenge1.addRequirement(generalRequirement2);
        googleChallenge1.addRequirement(generalRequirement3);
        googleChallenge1.addRequirement("Experience with AI and machine learning technologies.");

        googleChallenge1.addBenefit(generalBenefit1);
        googleChallenge1.addBenefit(generalBenefit2);
        googleChallenge1.addBenefit(generalBenefit3);
        googleChallenge1.addBenefit("Collaboration with Google's AI research team.");

        Challenge googleChallenge2 = new Challenge(
            google,
            "Sustainable Data Centers",
            "Develop solutions to make data centers more energy-efficient and sustainable.",
            "Energy-efficient and sustainable solutions for data centers.",
            new BigDecimal("350000.00"),
            LocalDate.of(2025, 6, 15),
            Arrays.asList(
                odsService.findByName("Affordable and Clean Energy"),
                odsService.findByName("Industry, Innovation, and Infrastructure")
            )
        );

        googleChallenge2.addRequirement(generalRequirement1);
        googleChallenge2.addRequirement(generalRequirement2);
        googleChallenge2.addRequirement(generalRequirement3);
        googleChallenge2.addRequirement("Knowledge of data center operations and energy management.");

        googleChallenge2.addBenefit(generalBenefit1);
        googleChallenge2.addBenefit(generalBenefit2);
        googleChallenge2.addBenefit(generalBenefit3);
        googleChallenge2.addBenefit("Access to Google's data center facilities for testing.");

        Challenge appleChallenge1 = new Challenge(
            apple,
            "Eco-Friendly Product Design",
            "Design eco-friendly products that reduce environmental impact.",
            "Eco-friendly product designs that reduce environmental impact.",
            new BigDecimal("150000.00"),
            LocalDate.of(2025, 7, 10),
            Arrays.asList(
                odsService.findByName("Responsible Consumption and Production"),
                odsService.findByName("Climate Action")
            )
        );

        appleChallenge1.addRequirement(generalRequirement1);
        appleChallenge1.addRequirement(generalRequirement2);
        appleChallenge1.addRequirement(generalRequirement3);
        appleChallenge1.addRequirement("Experience in product design and sustainable materials.");

        appleChallenge1.addBenefit(generalBenefit1);
        appleChallenge1.addBenefit(generalBenefit2);
        appleChallenge1.addBenefit(generalBenefit3);
        appleChallenge1.addBenefit("Collaboration with Apple's design team.");

        Challenge appleChallenge2 = new Challenge(
            apple,
            "Renewable Energy Integration",
            "Integrate renewable energy solutions into Apple's supply chain.",
            "Renewable energy solutions for Apple's supply chain.",
            new BigDecimal("200000.00"),
            LocalDate.of(2025, 8, 5),
            Arrays.asList(
                odsService.findByName("Affordable and Clean Energy"),
                odsService.findByName("Industry, Innovation, and Infrastructure")
            )
        );

        appleChallenge2.addRequirement(generalRequirement1);
        appleChallenge2.addRequirement(generalRequirement2);
        appleChallenge2.addRequirement(generalRequirement3);
        appleChallenge2.addRequirement("Knowledge of renewable energy technologies and supply chain management.");

        appleChallenge2.addBenefit(generalBenefit1);
        appleChallenge2.addBenefit(generalBenefit2);
        appleChallenge2.addBenefit(generalBenefit3);
        appleChallenge2.addBenefit("Access to Apple's supply chain network for implementation.");

        Challenge microsoftChallenge1 = new Challenge(
            microsoft,
            "Carbon Negative Solutions",
            "Develop solutions to help Microsoft achieve its carbon negative goals.",
            "Solutions to help Microsoft achieve its carbon negative goals.",
            new BigDecimal("250000.00"),
            LocalDate.of(2025, 9, 15),
            Arrays.asList(
                odsService.findByName("Climate Action"),
                odsService.findByName("Industry, Innovation, and Infrastructure")
            )
        );

        microsoftChallenge1.addRequirement(generalRequirement1);
        microsoftChallenge1.addRequirement(generalRequirement2);
        microsoftChallenge1.addRequirement(generalRequirement3);
        microsoftChallenge1.addRequirement("Experience with carbon capture and reduction technologies.");

        microsoftChallenge1.addBenefit(generalBenefit1);
        microsoftChallenge1.addBenefit(generalBenefit2);
        microsoftChallenge1.addBenefit(generalBenefit3);
        microsoftChallenge1.addBenefit("Collaboration with Microsoft's sustainability team.");

        Challenge microsoftChallenge2 = new Challenge(
            microsoft,
            "Sustainable Software Development",
            "Create sustainable software development practices to reduce environmental impact.",
            "Sustainable software development practices to reduce environmental impact.",
            new BigDecimal("200000.00"),
            LocalDate.of(2025, 10, 10),
            Arrays.asList(
                odsService.findByName("Industry, Innovation, and Infrastructure"),
                odsService.findByName("Climate Action")
            )
        );

        microsoftChallenge2.addRequirement(generalRequirement1);
        microsoftChallenge2.addRequirement(generalRequirement2);
        microsoftChallenge2.addRequirement(generalRequirement3);
        microsoftChallenge2.addRequirement("Knowledge of software development and sustainability practices.");

        microsoftChallenge2.addBenefit(generalBenefit1);
        microsoftChallenge2.addBenefit(generalBenefit2);
        microsoftChallenge2.addBenefit(generalBenefit3);
        microsoftChallenge2.addBenefit("Access to Microsoft's development tools and resources.");

        Challenge amazonChallenge1 = new Challenge(
            amazon,
            "Sustainable Packaging",
            "Develop sustainable packaging solutions to reduce waste.",
            "Sustainable packaging solutions to reduce waste.",
            new BigDecimal("150000.00"),
            LocalDate.of(2025, 11, 5),
            Arrays.asList(
                odsService.findByName("Responsible Consumption and Production"),
                odsService.findByName("Climate Action")
            )
        );

        amazonChallenge1.addRequirement(generalRequirement1);
        amazonChallenge1.addRequirement(generalRequirement2);
        amazonChallenge1.addRequirement(generalRequirement3);
        amazonChallenge1.addRequirement("Experience in packaging design and sustainable materials.");

        amazonChallenge1.addBenefit(generalBenefit1);
        amazonChallenge1.addBenefit(generalBenefit2);
        amazonChallenge1.addBenefit(generalBenefit3);
        amazonChallenge1.addBenefit("Collaboration with Amazon's packaging team.");

        Challenge amazonChallenge2 = new Challenge(
            amazon,
            "Green Logistics",
            "Create green logistics solutions to reduce carbon emissions in Amazon's supply chain.",
            "Green logistics solutions to reduce carbon emissions in Amazon's supply chain.",
            new BigDecimal("200000.00"),
            LocalDate.of(2025, 12, 1),
            Arrays.asList(
                odsService.findByName("Climate Action"),
                odsService.findByName("Industry, Innovation, and Infrastructure")
            )
        );

        amazonChallenge2.addRequirement(generalRequirement1);
        amazonChallenge2.addRequirement(generalRequirement2);
        amazonChallenge2.addRequirement(generalRequirement3);
        amazonChallenge2.addRequirement("Knowledge of logistics and supply chain management.");

        amazonChallenge2.addBenefit(generalBenefit1);
        amazonChallenge2.addBenefit(generalBenefit2);
        amazonChallenge2.addBenefit(generalBenefit3);
        amazonChallenge2.addBenefit("Access to Amazon's logistics network for implementation.");

        List<Challenge> allChallenges = Arrays.asList(
            teslaChallenge1, teslaChallenge2,
            googleChallenge1, googleChallenge2,
            appleChallenge1, appleChallenge2,
            microsoftChallenge1, microsoftChallenge2,
            amazonChallenge1, amazonChallenge2
        );

        allChallenges.forEach(challenge -> {
            if (!challengeService.existsByTitle(challenge.getTitle())) {
                challengeService.save(challenge);
            }
        });

        if (tesla != null) {
            tesla.addChallenge(teslaChallenge1);
            tesla.addChallenge(teslaChallenge2);
        }
        if (google != null) {
            google.addChallenge(googleChallenge1);
            google.addChallenge(googleChallenge2);
        }
        if (apple != null) {
            apple.addChallenge(appleChallenge1);
            apple.addChallenge(appleChallenge2);
        }
        if (microsoft != null) {
            microsoft.addChallenge(microsoftChallenge1);
            microsoft.addChallenge(microsoftChallenge2);
        }
        if (amazon != null) {
            amazon.addChallenge(amazonChallenge1);
            amazon.addChallenge(amazonChallenge2);
        }
    }
}
