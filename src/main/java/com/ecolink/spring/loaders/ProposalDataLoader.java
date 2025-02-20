package com.ecolink.spring.loaders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.ecolink.spring.entity.Challenge;
import com.ecolink.spring.entity.Proposal;
import com.ecolink.spring.entity.Startup;
import com.ecolink.spring.service.ChallengeService;
import com.ecolink.spring.service.ProposalService;
import com.ecolink.spring.service.StartupService;

import jakarta.transaction.Transactional;

import com.ecolink.spring.entity.Status;

@Component
@Order(11)
public class ProposalDataLoader implements CommandLineRunner {

        @Autowired
        public ProposalService service;

        @Autowired
        public StartupService startupService;

        @Autowired
        public ChallengeService challengeService;

        @Transactional
        public void run(String... args) {

                Startup flavorScale = startupService.findByName("FlavorScale");
                Startup climaider = startupService.findByName("Climaider");
                Startup seasony = startupService.findByName("Seasony");
                Startup tribe = startupService.findByName("Tribe");
                Startup peltarion = startupService.findByName("Peltarion");
                Startup donkeyRepublic = startupService.findByName("Donkey Republic");
                Startup pickYourPour = startupService.findByName("Pick Your Pour AS");
                Startup aliceAi = startupService.findByName("Alice AI");
                Startup leiaHealth = startupService.findByName("LEIA Health");
                Startup rightHub = startupService.findByName("RightHub");
                Startup memmora = startupService.findByName("Memmora");
                Startup soilSense = startupService.findByName("SoilSense");
                Startup monta = startupService.findByName("Monta");
                Startup doublepoint = startupService.findByName("Doublepoint");
                Startup strise = startupService.findByName("Strise");
                Startup station = startupService.findByName("Station");
                Startup silvi = startupService.findByName("Silvi");
                Startup nutrishAi = startupService.findByName("Nutrish.ai");
                Startup kodiakHub = startupService.findByName("KodiakHub");
                Startup ecoTree = startupService.findByName("EcoTree");
                Startup goMore = startupService.findByName("GoMore");
                Startup goodWings = startupService.findByName("GoodWings");
                Startup glintSolar = startupService.findByName("GlintSolar");
                Startup spritju = startupService.findByName("Spritju");
                Startup deepBlu = startupService.findByName("DeepBlu");
                Startup vove = startupService.findByName("Vove");
                Startup perPlant = startupService.findByName("PerPlant");
                Startup dripdrop = startupService.findByName("Dripdrop");
                Startup legitify = startupService.findByName("Legitify");
                Startup whistleSystem = startupService.findByName("WhistleSystem");
                Startup beCause = startupService.findByName("BeCause");
                Startup chronosHub = startupService.findByName("ChronosHub");

                Challenge teslaChallenge1 = challengeService.findByTitle("Electric Vehicle Innovation");
                Challenge teslaChallenge2 = challengeService.findByTitle("Sustainable Energy Storage");
                Challenge googleChallenge1 = challengeService.findByTitle("AI for Climate Change");
                Challenge googleChallenge2 = challengeService.findByTitle("Sustainable Data Centers");
                Challenge appleChallenge1 = challengeService.findByTitle("Eco-Friendly Product Design");
                Challenge appleChallenge2 = challengeService.findByTitle("Renewable Energy Integration");
                Challenge microsoftChallenge1 = challengeService.findByTitle("Carbon Negative Solutions");
                Challenge microsoftChallenge2 = challengeService.findByTitle("Sustainable Software Development");
                Challenge amazonChallenge1 = challengeService.findByTitle("Sustainable Packaging");
                Challenge amazonChallenge2 = challengeService.findByTitle("Green Logistics");

                List<Proposal> proposals = Arrays.asList(
                                new Proposal(flavorScale, teslaChallenge1,
                                                "Innovative Battery Materials",
                                                "Proposal to develop new battery materials to improve efficiency and range of electric vehicles.",
                                                LocalDate.of(2025, 1, 15), Status.ACCEPTED),
                                new Proposal(climaider, teslaChallenge2,
                                                "Advanced Energy Storage",
                                                "Development of advanced energy storage solutions for renewable energy systems.",
                                                LocalDate.of(2025, 2, 10), Status.PENDING),
                                new Proposal(seasony, googleChallenge1,
                                                "AI for Climate Prediction",
                                                "Using AI to predict climate patterns and improve climate resilience.",
                                                LocalDate.of(2025, 3, 5), Status.PENDING),
                                new Proposal(tribe, googleChallenge2,
                                                "Energy-Efficient Data Centers",
                                                "Designing energy-efficient data centers to reduce carbon footprint.",
                                                LocalDate.of(2025, 3, 20), Status.REJECTED),
                                new Proposal(peltarion, appleChallenge1,
                                                "Eco-Friendly Product Line",
                                                "Creating a new line of eco-friendly products with sustainable materials.",
                                                LocalDate.of(2025, 4, 1), Status.ACCEPTED),
                                new Proposal(donkeyRepublic, appleChallenge2,
                                                "Renewable Energy Integration",
                                                "Integrating renewable energy solutions into Apple's supply chain.",
                                                LocalDate.of(2025, 4, 15), Status.REJECTED),
                                new Proposal(pickYourPour, microsoftChallenge1,
                                                "Carbon Capture Technology",
                                                "Developing carbon capture technology to help Microsoft achieve its carbon negative goals.",
                                                LocalDate.of(2025, 5, 10), Status.PENDING),
                                new Proposal(aliceAi, microsoftChallenge2,
                                                "Sustainable Software Practices",
                                                "Creating sustainable software development practices to reduce environmental impact.",
                                                LocalDate.of(2025, 6, 1), Status.PENDING),
                                new Proposal(leiaHealth, amazonChallenge1,
                                                "Sustainable Packaging Solutions",
                                                "Developing sustainable packaging solutions to reduce waste.",
                                                LocalDate.of(2025, 6, 10), Status.ACCEPTED),
                                new Proposal(rightHub, amazonChallenge2,
                                                "Green Logistics",
                                                "Creating green logistics solutions to reduce carbon emissions in Amazon's supply chain.",
                                                LocalDate.of(2025, 7, 1), Status.PENDING));

                proposals.forEach(proposal -> {
                        if (!service.existsByStartupAndChallenge(proposal.getStartup(), proposal.getChallenge())) {
                                Startup startup = proposal.getStartup();
                                Challenge challenge = proposal.getChallenge();
                                startup.addProposal(proposal);
                                challenge.addProposal(proposal);
                                service.save(proposal);
                        }
                });
        }
}
