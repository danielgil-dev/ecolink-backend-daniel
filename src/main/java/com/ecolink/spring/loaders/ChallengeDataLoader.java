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
@Order(11)
public class ChallengeDataLoader implements CommandLineRunner {

    @Autowired
    private ChallengeService challengeService;

    @Autowired
    private OdsService odsService;

    @Autowired
    private CompanyService companyService;

    /*
     * Poniendo la anotacion transactional nos aseguramos de que el metodo haga una
     * operacion atomica, de tal manera de que si ocurre algun error
     * al momento de la ejecucion este se detendra, asegurando no tener
     * inconsistencias en nuestra base de datos
     */
    @Transactional
    @Override
    public void run(String... args) {
        Company ecoVision = companyService.findByName("EcoVision");
        Company greenHorizon = companyService.findByName("GreenHorizon");
        Company solarPioneer = companyService.findByName("SolarPioneer");
        Company bioCraft = companyService.findByName("BioCraft");
        Company urbanFlow = companyService.findByName("UrbanFlow");

        String generalRequirement1 = "Experience in sustainable technologies or environmental solutions.";
        String generalRequirement2 = "Knowledge of innovative materials and energy-efficient systems.";
        String generalRequirement3 = "Ability to design and prototype scalable eco-friendly solutions.";

        String generalBenefit1 = "Access to funding and mentorship from industry experts.";
        String generalBenefit2 = "Opportunity to pilot and implement solutions in real-world scenarios.";
        String generalBenefit3 = "Networking and collaboration with leaders in sustainability and innovation.";

        Challenge ecoVisionChallenge1 = new Challenge(ecoVision,
                "Eco-Friendly Manufacturing",
                "How can we make factory production greener? Propose simple solutions to reduce waste and emissions.",
                "Propose simple solutions to reduce waste and emissions in factory production.",
                new BigDecimal("100000.00"),
                LocalDate.of(2025, 2, 10),
                Arrays.asList(odsService.findByName("Climate Action")));

        ecoVisionChallenge1.addRequirement(generalRequirement1);
        ecoVisionChallenge1.addRequirement(generalRequirement2);
        ecoVisionChallenge1.addRequirement(generalRequirement3);

        ecoVisionChallenge1.addBenefit(generalBenefit1);
        ecoVisionChallenge1.addBenefit(generalBenefit2);
        ecoVisionChallenge1.addBenefit(generalBenefit3);

        Challenge ecoVisionChallenge2 = new Challenge(ecoVision,
                "Eco-Friendly Building Materials",
                "Suggest innovative materials or processes that reduce the carbon footprint of construction.",
                "Innovative materials or processes to reduce the carbon footprint of construction.",
                new BigDecimal("110000.00"),
                LocalDate.of(2025, 2, 3),
                Arrays.asList(
                        odsService.findByName("Industry, Innovation, and Infrastructure"),
                        odsService.findByName("Sustainable Cities and Communities")));

        ecoVisionChallenge2.addRequirement(generalRequirement1);
        ecoVisionChallenge2.addRequirement(generalRequirement2);
        ecoVisionChallenge2.addRequirement(generalRequirement3);

        ecoVisionChallenge2.addBenefit(generalBenefit1);
        ecoVisionChallenge2.addBenefit(generalBenefit2);
        ecoVisionChallenge2.addBenefit(generalBenefit3);

        Challenge greenHorizonChallenge1 = new Challenge(greenHorizon,
                "Powering Remote Areas",
                "Create an easy-to-deploy solar power kit for small businesses or homes in off-grid areas.",
                "Easy-to-deploy solar power kit for off-grid areas.",
                new BigDecimal("150000.00"),
                LocalDate.of(2025, 2, 13),
                Arrays.asList(
                        odsService.findByName("Affordable and Clean Energy"),
                        odsService.findByName("Industry, Innovation, and Infrastructure")));

        Challenge greenHorizonChallenge2 = new Challenge(greenHorizon,
                "Smarter Energy Storage",
                "How can we make storing solar and wind energy more efficient and affordable? Share your ideas!",
                "Efficient and affordable energy storage solutions.",
                new BigDecimal("140000.00"),
                LocalDate.of(2025, 2, 15),
                Arrays.asList(
                        odsService.findByName("Affordable and Clean Energy"),
                        odsService.findByName("Climate Action")));

        greenHorizonChallenge2.addRequirement(generalRequirement1);
        greenHorizonChallenge2.addRequirement(generalRequirement2);
        greenHorizonChallenge2.addRequirement(generalRequirement3);

        greenHorizonChallenge2.addBenefit(generalBenefit1);
        greenHorizonChallenge2.addBenefit(generalBenefit2);
        greenHorizonChallenge2.addBenefit(generalBenefit3);

        Challenge solarPioneerChallenge1 = new Challenge(solarPioneer,
                "Water-Saving Tech",
                "How can we help cities or businesses use less water? Propose an affordable, easy-to-implement solution.",
                "Affordable, easy-to-implement water-saving solutions.",
                new BigDecimal("80000.00"),
                LocalDate.of(2025, 2, 18),
                Arrays.asList(odsService.findByName("Clean Water and Sanitation")));

        Challenge solarPioneerChallenge2 = new Challenge(solarPioneer,
                "A New Era of Solar Panels",
                "Design a creative way to integrate solar panels into everyday life – beyond rooftops!",
                "Creative integration of solar panels into everyday life.",
                new BigDecimal("130000.00"),
                LocalDate.of(2025, 3, 1),
                Arrays.asList(odsService.findByName("Affordable and Clean Energy")));

        Challenge bioCraftChallenge1 = new Challenge(bioCraft,
                "Farming Without Waste",
                "Design a solution to help farmers cut down food waste, whether through better storage, distribution, or alternative uses.",
                "Solutions to help farmers reduce food waste.",
                new BigDecimal("120000.00"),
                LocalDate.of(2025, 2, 16),
                Arrays.asList(
                        odsService.findByName("Zero Hunger"),
                        odsService.findByName("Life on Land")));

        Challenge bioCraftChallenge2 = new Challenge(bioCraft,
                "Bye-Bye Plastic!",
                "Can you develop an alternative to plastic that is cheap, scalable, and biodegradable?",
                "Cheap, scalable, and biodegradable alternatives to plastic.",
                new BigDecimal("115000.00"),
                LocalDate.of(2025, 2, 20),
                Arrays.asList(odsService.findByName("Responsible Consumption and Production")));

        Challenge urbanFlowChallenge1 = new Challenge(urbanFlow,
                "The Future of Recycling",
                "What’s the next big idea in recycling? Think beyond traditional bins and propose something that makes waste separation fun and easy.",
                "Innovative ideas for the future of recycling.",
                new BigDecimal("90000.00"),
                LocalDate.of(2025, 2, 24),
                Arrays.asList(odsService.findByName("Responsible Consumption and Production")));

        Challenge urbanFlowChallenge2 = new Challenge(urbanFlow,
                "Rethinking City Mobility",
                "What’s the next big idea in urban transportation? Help us make cities cleaner and more efficient.",
                "Innovative ideas for urban transportation.",
                new BigDecimal("105000.00"),
                LocalDate.of(2025, 2, 28),
                Arrays.asList(odsService.findByName("Sustainable Cities and Communities")));

        List<Challenge> allChallenges = Arrays.asList(
                ecoVisionChallenge1, ecoVisionChallenge2,
                greenHorizonChallenge1, greenHorizonChallenge2,
                solarPioneerChallenge1, solarPioneerChallenge2,
                bioCraftChallenge1, bioCraftChallenge2,
                urbanFlowChallenge1, urbanFlowChallenge2);

        allChallenges.forEach(challenge -> {
            if (!challengeService.existsByTitle(challenge.getTitle())) {
                challengeService.save(challenge);
            }
        });

        if (ecoVision != null) {
            ecoVision.addChallenge(ecoVisionChallenge1);
            ecoVision.addChallenge(ecoVisionChallenge2);
        }
        if (greenHorizon != null) {
            greenHorizon.addChallenge(greenHorizonChallenge1);
            greenHorizon.addChallenge(greenHorizonChallenge2);
        }
        if (solarPioneer != null) {
            solarPioneer.addChallenge(solarPioneerChallenge1);
            solarPioneer.addChallenge(solarPioneerChallenge2);
        }
        if (bioCraft != null) {
            bioCraft.addChallenge(bioCraftChallenge1);
            bioCraft.addChallenge(bioCraftChallenge2);
        }
        if (urbanFlow != null) {
            urbanFlow.addChallenge(urbanFlowChallenge1);
            urbanFlow.addChallenge(urbanFlowChallenge2);
        }
    }
}
