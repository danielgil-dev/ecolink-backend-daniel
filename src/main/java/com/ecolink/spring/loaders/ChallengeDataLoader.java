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

        Company ecoVision = companyService.findByName("EcoVision");
        Company greenHorizon = companyService.findByName("GreenHorizon");
        Company solarPioneer = companyService.findByName("SolarPioneer");
        Company bioCraft = companyService.findByName("BioCraft");
        Company urbanFlow = companyService.findByName("UrbanFlow");

        // Requisitos y beneficios "generales"
        String generalRequirement1 = "Experience in sustainable technologies or environmental solutions.";
        String generalRequirement2 = "Knowledge of innovative materials and energy-efficient systems.";
        String generalRequirement3 = "Ability to design and prototype scalable eco-friendly solutions.";

        String generalBenefit1 = "Access to funding and mentorship from industry experts.";
        String generalBenefit2 = "Opportunity to pilot and implement solutions in real-world scenarios.";
        String generalBenefit3 = "Networking and collaboration with leaders in sustainability and innovation.";

        // =============== EcoVision Challenges ===============
        Challenge ecoVisionChallenge1 = new Challenge(
            ecoVision,
            "Eco-Friendly Manufacturing",
            "How can we make factory production greener? Propose simple solutions to reduce waste and emissions.",
            "Propose simple solutions to reduce waste and emissions in factory production.",
            new BigDecimal("100000.00"),
            LocalDate.of(2025, 2, 10),
            Arrays.asList(odsService.findByName("Climate Action"))
        );

        // Requirements y Benefits "generales"
        ecoVisionChallenge1.addRequirement(generalRequirement1);
        ecoVisionChallenge1.addRequirement(generalRequirement2);
        ecoVisionChallenge1.addRequirement(generalRequirement3);

        ecoVisionChallenge1.addBenefit(generalBenefit1);
        ecoVisionChallenge1.addBenefit(generalBenefit2);
        ecoVisionChallenge1.addBenefit(generalBenefit3);

        // Añadimos algo más específico al tema de fabricación ecológica
        ecoVisionChallenge1.addRequirement("Familiarity with waste reduction processes in manufacturing lines.");
        ecoVisionChallenge1.addBenefit("Possibility of integrating proposed solutions in existing ecoVision factories.");

        Challenge ecoVisionChallenge2 = new Challenge(
            ecoVision,
            "Eco-Friendly Building Materials",
            "Suggest innovative materials or processes that reduce the carbon footprint of construction.",
            "Innovative materials or processes to reduce the carbon footprint of construction.",
            new BigDecimal("110000.00"),
            LocalDate.of(2025, 2, 3),
            Arrays.asList(
                odsService.findByName("Industry, Innovation, and Infrastructure"),
                odsService.findByName("Sustainable Cities and Communities")
            )
        );

        // Requirements y Benefits "generales"
        ecoVisionChallenge2.addRequirement(generalRequirement1);
        ecoVisionChallenge2.addRequirement(generalRequirement2);
        ecoVisionChallenge2.addRequirement(generalRequirement3);

        ecoVisionChallenge2.addBenefit(generalBenefit1);
        ecoVisionChallenge2.addBenefit(generalBenefit2);
        ecoVisionChallenge2.addBenefit(generalBenefit3);

        // Algo más específico al tema de construcción sostenible
        ecoVisionChallenge2.addRequirement("Experience in construction engineering or green building standards.");
        ecoVisionChallenge2.addBenefit("Potential partnership with construction firms for pilot projects.");

        // =============== GreenHorizon Challenges ===============
        Challenge greenHorizonChallenge1 = new Challenge(
            greenHorizon,
            "Powering Remote Areas",
            "Create an easy-to-deploy solar power kit for small businesses or homes in off-grid areas.",
            "Easy-to-deploy solar power kit for off-grid areas.",
            new BigDecimal("150000.00"),
            LocalDate.of(2025, 2, 13),
            Arrays.asList(
                odsService.findByName("Affordable and Clean Energy"),
                odsService.findByName("Industry, Innovation, and Infrastructure")
            )
        );

        // Requirements y Benefits específicos
        greenHorizonChallenge1.addRequirement("Experience with solar or renewable energy solutions for remote areas.");
        greenHorizonChallenge1.addRequirement("Knowledge of battery storage and basic electronics for off-grid systems.");
        greenHorizonChallenge1.addRequirement("Ability to design modular, easy-to-install power kits.");

        greenHorizonChallenge1.addBenefit("Field testing opportunities in remote communities.");
        greenHorizonChallenge1.addBenefit("Collaboration with local NGOs for distribution and feedback.");
        greenHorizonChallenge1.addBenefit("Mentorship from experts in rural electrification.");

        Challenge greenHorizonChallenge2 = new Challenge(
            greenHorizon,
            "Smarter Energy Storage",
            "How can we make storing solar and wind energy more efficient and affordable? Share your ideas!",
            "Efficient and affordable energy storage solutions.",
            new BigDecimal("140000.00"),
            LocalDate.of(2025, 2, 15),
            Arrays.asList(
                odsService.findByName("Affordable and Clean Energy"),
                odsService.findByName("Climate Action")
            )
        );

        greenHorizonChallenge2.addRequirement(generalRequirement1);
        greenHorizonChallenge2.addRequirement(generalRequirement2);
        greenHorizonChallenge2.addRequirement(generalRequirement3);

        greenHorizonChallenge2.addBenefit(generalBenefit1);
        greenHorizonChallenge2.addBenefit(generalBenefit2);
        greenHorizonChallenge2.addBenefit(generalBenefit3);

        // Añadimos un requirement/benefit adicional más específico
        greenHorizonChallenge2.addRequirement("Familiarity with lithium-ion or alternative battery technologies.");
        greenHorizonChallenge2.addBenefit("Chance to test prototypes in existing wind/solar farms.");

        // =============== SolarPioneer Challenges ===============
        Challenge solarPioneerChallenge1 = new Challenge(
            solarPioneer,
            "Water-Saving Tech",
            "How can we help cities or businesses use less water? Propose an affordable, easy-to-implement solution.",
            "Affordable, easy-to-implement water-saving solutions.",
            new BigDecimal("80000.00"),
            LocalDate.of(2025, 2, 18),
            Arrays.asList(odsService.findByName("Clean Water and Sanitation"))
        );

        // Requirements y Benefits "generales"
        solarPioneerChallenge1.addRequirement(generalRequirement1);
        solarPioneerChallenge1.addRequirement(generalRequirement2);
        solarPioneerChallenge1.addRequirement(generalRequirement3);

        solarPioneerChallenge1.addBenefit(generalBenefit1);
        solarPioneerChallenge1.addBenefit(generalBenefit2);
        solarPioneerChallenge1.addBenefit(generalBenefit3);

        // Algo más específico al ahorro de agua
        solarPioneerChallenge1.addRequirement("Basic knowledge of water recycling or rainwater harvesting.");
        solarPioneerChallenge1.addBenefit("Opportunity to pilot solutions in water-scarce urban areas.");

        Challenge solarPioneerChallenge2 = new Challenge(
            solarPioneer,
            "A New Era of Solar Panels",
            "Design a creative way to integrate solar panels into everyday life – beyond rooftops!",
            "Creative integration of solar panels into everyday life.",
            new BigDecimal("130000.00"),
            LocalDate.of(2025, 3, 1),
            Arrays.asList(odsService.findByName("Affordable and Clean Energy"))
        );

        // Requirements y Benefits específicos
        solarPioneerChallenge2.addRequirement("Experience in solar technology integration into consumer products.");
        solarPioneerChallenge2.addRequirement("Understanding of design constraints and aesthetics for daily usage.");
        solarPioneerChallenge2.addRequirement("Knowledge of power generation and storage for small-scale devices.");

        solarPioneerChallenge2.addBenefit("Potential collaboration with product designers and architects.");
        solarPioneerChallenge2.addBenefit("Access to specialized solar panel materials or prototypes.");
        solarPioneerChallenge2.addBenefit("Option to showcase solutions at clean-energy expos.");

        // =============== BioCraft Challenges ===============
        Challenge bioCraftChallenge1 = new Challenge(
            bioCraft,
            "Farming Without Waste",
            "Design a solution to help farmers cut down food waste, whether through better storage, distribution, or alternative uses.",
            "Solutions to help farmers reduce food waste.",
            new BigDecimal("120000.00"),
            LocalDate.of(2025, 2, 16),
            Arrays.asList(
                odsService.findByName("Zero Hunger"),
                odsService.findByName("Life on Land")
            )
        );

        // Requirements y Benefits específicos
        bioCraftChallenge1.addRequirement("Knowledge in agritech or supply chain management.");
        bioCraftChallenge1.addRequirement("Experience in post-harvest handling or cold storage solutions.");
        bioCraftChallenge1.addRequirement("Understanding of rural distribution networks or alternative uses for surplus.");

        bioCraftChallenge1.addBenefit("Access to pilot programs with partner farms.");
        bioCraftChallenge1.addBenefit("Collaboration with food distribution networks.");
        bioCraftChallenge1.addBenefit("Possibility of scaling solutions across multiple agricultural sectors.");

        Challenge bioCraftChallenge2 = new Challenge(
            bioCraft,
            "Bye-Bye Plastic!",
            "Can you develop an alternative to plastic that is cheap, scalable, and biodegradable?",
            "Cheap, scalable, and biodegradable alternatives to plastic.",
            new BigDecimal("115000.00"),
            LocalDate.of(2025, 2, 20),
            Arrays.asList(odsService.findByName("Responsible Consumption and Production"))
        );

        // Requirements y Benefits específicos
        bioCraftChallenge2.addRequirement("Background in materials science or biodegradable polymers.");
        bioCraftChallenge2.addRequirement("Familiarity with plastic recycling processes.");
        bioCraftChallenge2.addRequirement("Understanding of large-scale production methods.");

        bioCraftChallenge2.addBenefit("Opportunity to test prototypes with partner businesses.");
        bioCraftChallenge2.addBenefit("Potential for patent or IP support.");
        bioCraftChallenge2.addBenefit("Access to specialized labs or materials testing.");

        // =============== UrbanFlow Challenges ===============
        Challenge urbanFlowChallenge1 = new Challenge(
            urbanFlow,
            "The Future of Recycling",
            "What’s the next big idea in recycling? Think beyond traditional bins and propose something that makes waste separation fun and easy.",
            "Innovative ideas for the future of recycling.",
            new BigDecimal("90000.00"),
            LocalDate.of(2025, 2, 24),
            Arrays.asList(odsService.findByName("Responsible Consumption and Production"))
        );

        // Requirements y Benefits específicos
        urbanFlowChallenge1.addRequirement("Understanding of current waste management or recycling processes.");
        urbanFlowChallenge1.addRequirement("Creative approach to user engagement and behavior change.");
        urbanFlowChallenge1.addRequirement("Familiarity with recycling technologies or upcycling innovations.");

        urbanFlowChallenge1.addBenefit("Pilot programs in partnership with municipal waste management.");
        urbanFlowChallenge1.addBenefit("Potential for public or private grants.");
        urbanFlowChallenge1.addBenefit("Opportunity for city-wide rollout if successful.");

        Challenge urbanFlowChallenge2 = new Challenge(
            urbanFlow,
            "Rethinking City Mobility",
            "What’s the next big idea in urban transportation? Help us make cities cleaner and more efficient.",
            "Innovative ideas for urban transportation.",
            new BigDecimal("105000.00"),
            LocalDate.of(2025, 2, 28),
            Arrays.asList(odsService.findByName("Sustainable Cities and Communities"))
        );

        // Requirements y Benefits específicos
        urbanFlowChallenge2.addRequirement("Experience with transportation planning or micro-mobility solutions.");
        urbanFlowChallenge2.addRequirement("Familiarity with traffic flow analysis or route optimization.");
        urbanFlowChallenge2.addRequirement("Understanding of sustainable fuel or electric vehicle infrastructure.");

        urbanFlowChallenge2.addBenefit("Collaboration with local government or city planners.");
        urbanFlowChallenge2.addBenefit("Access to traffic data for pilot testing.");
        urbanFlowChallenge2.addBenefit("Mentorship from mobility experts and urban developers.");

        // Lista final de todos los challenges
        List<Challenge> allChallenges = Arrays.asList(
            ecoVisionChallenge1, ecoVisionChallenge2,
            greenHorizonChallenge1, greenHorizonChallenge2,
            solarPioneerChallenge1, solarPioneerChallenge2,
            bioCraftChallenge1, bioCraftChallenge2,
            urbanFlowChallenge1, urbanFlowChallenge2
        );

        // Guardar solo los que no existen (según el título)
        allChallenges.forEach(challenge -> {
            if (!challengeService.existsByTitle(challenge.getTitle())) {
                challengeService.save(challenge);
            }
        });

        // Vincularlos a la compañía (si no es nula)
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
