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

                Challenge carbonReductionChallenge = challengeService.findByTitle("Eco-Friendly Manufacturing");
                Challenge ruralRenewablesChallenge = challengeService.findByTitle("Eco-Friendly Building Materials");
                Challenge waterManagementChallenge = challengeService.findByTitle("Powering Remote Areas");
                Challenge sustainableAgricultureChallenge = challengeService.findByTitle("Smarter Energy Storage");
                Challenge wasteRecyclingChallenge = challengeService.findByTitle("Water-Saving Tech");
                Challenge newEra = challengeService.findByTitle("A New Era of Solar Panels");
                Challenge farminWaste = challengeService.findByTitle("Farming Without Waste");
                Challenge byeBye = challengeService.findByTitle("Bye-Bye Plastic!");
                Challenge theFutureRecycling = challengeService.findByTitle("The Future of Recycling");
                Challenge cityMobility = challengeService.findByTitle("Rethinking City Mobility");

                List<Proposal> proposals = Arrays.asList(
                                new Proposal(flavorScale, carbonReductionChallenge,
                                                "Innovative Filters",
                                                "Proposal to implement innovative filters in industrial smokestacks.",
                                                LocalDate.of(2025, 1, 15), Status.ACCEPTED),
                                new Proposal(climaider, ruralRenewablesChallenge,
                                                "Portable Solar Panels",
                                                "Development of portable solar panels for rural communities.",
                                                LocalDate.of(2025, 2, 10), Status.PENDING),
                                new Proposal(seasony, waterManagementChallenge,
                                                "Water Purification Tech",
                                                "Technology for water purification in areas facing extreme drought.",
                                                LocalDate.of(2025, 3, 5), Status.PENDING),
                                new Proposal(tribe, sustainableAgricultureChallenge,
                                                "Automated Irrigation",
                                                "Automated irrigation system that minimizes water waste.",
                                                LocalDate.of(2025, 3, 20), Status.REJECTED),
                                new Proposal(peltarion, wasteRecyclingChallenge,
                                                "Smart Recycling Campaign",
                                                "Educational campaign about smart recycling in urban areas.",
                                                LocalDate.of(2025, 4, 1), Status.ACCEPTED),
                                new Proposal(donkeyRepublic, carbonReductionChallenge,
                                                "Transport Emission Reduction",
                                                "Route optimization to reduce transport emissions.",
                                                LocalDate.of(2025, 4, 15), Status.REJECTED),
                                new Proposal(pickYourPour, sustainableAgricultureChallenge,
                                                "Crop Monitoring Drones",
                                                "Drones to monitor crops and reduce pesticide usage.",
                                                LocalDate.of(2025, 5, 10), Status.PENDING),
                                new Proposal(aliceAi, newEra,
                                                "Next-Gen Photovoltaic Cells",
                                                "Implementation of next-generation photovoltaic cells to boost solar efficiency by 25%.",
                                                LocalDate.of(2025, 6, 1), Status.PENDING),
                                new Proposal(leiaHealth, farminWaste,
                                                "Zero-Waste Farming",
                                                "A zero-waste farming model that converts organic residues into natural fertilizers.",
                                                LocalDate.of(2025, 6, 10), Status.ACCEPTED),
                                new Proposal(rightHub, byeBye,
                                                "Compostable Alternatives Campaign",
                                                "A global campaign encouraging businesses and consumers to switch from single-use plastics to compostable alternatives.",
                                                LocalDate.of(2025, 7, 1), Status.PENDING),
                                new Proposal(memmora, theFutureRecycling,
                                                "AI-Driven Sorting System",
                                                "An AI-driven sorting system designed to increase material recovery rates in urban recycling centers.",
                                                LocalDate.of(2025, 7, 15), Status.REJECTED),
                                new Proposal(soilSense, cityMobility,
                                                "Renewable Energy Bike-Sharing",
                                                "Development of a city-wide bike-sharing and carpooling platform powered by renewable energy sources.",
                                                LocalDate.of(2025, 8, 1), Status.PENDING));

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
