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

        Startup vhat = startupService.findByName("VhAT");
        Startup gamingBuddy = startupService.findByName("GamingBuddy");
        Startup nors = startupService.findByName("Nørs");
        Startup andLight = startupService.findByName("AndLight");
        Startup imHub = startupService.findByName("Influencer Marketing Hub");
        Startup tooGoodToGo = startupService.findByName("Too Good To Go");
        Startup doublepoint = startupService.findByName("Doublepoint");

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
              
                new Proposal(
                        vhat,
                        carbonReductionChallenge,
                        "Proposal to implement innovative filters in industrial smokestacks.",
                        LocalDate.of(2025, 1, 15),
                        Status.ACCEPTED),
                new Proposal(
                        gamingBuddy,
                        ruralRenewablesChallenge,
                        "Development of portable solar panels for rural communities.",
                        LocalDate.of(2025, 2, 10),
                        Status.PENDING),
                new Proposal(
                        nors,
                        waterManagementChallenge,
                        "Technology for water purification in areas facing extreme drought.",
                        LocalDate.of(2025, 3, 5),
                        Status.PENDING),
                new Proposal(
                        andLight,
                        sustainableAgricultureChallenge,
                        "Automated irrigation system that minimizes water waste.",
                        LocalDate.of(2025, 3, 20),
                        Status.REJECTED),
                new Proposal(
                        imHub,
                        wasteRecyclingChallenge,
                        "Educational campaign about smart recycling in urban areas.",
                        LocalDate.of(2025, 4, 1),
                        Status.ACCEPTED),
                new Proposal(
                        tooGoodToGo,
                        carbonReductionChallenge,
                        "Route optimization to reduce transport emissions.",
                        LocalDate.of(2025, 4, 15),
                        Status.REJECTED),
                new Proposal(
                        doublepoint,
                        sustainableAgricultureChallenge,
                        "Drones to monitor crops and reduce pesticide usage.",
                        LocalDate.of(2025, 5, 10),
                        Status.PENDING),

                new Proposal(
                        vhat,
                        newEra,
                        "Implementation of next-generation photovoltaic cells to boost solar efficiency by 25%.",
                        LocalDate.of(2025, 6, 1),
                        Status.PENDING),
                new Proposal(
                        nors,
                        farminWaste,
                        "A zero-waste farming model that converts organic residues into natural fertilizers.",
                        LocalDate.of(2025, 6, 10),
                        Status.ACCEPTED),
                new Proposal(
                        imHub,
                        byeBye,
                        "A global campaign encouraging businesses and consumers to switch from single-use plastics to compostable alternatives.",
                        LocalDate.of(2025, 7, 1),
                        Status.PENDING),
                new Proposal(
                        tooGoodToGo,
                        theFutureRecycling,
                        "An AI-driven sorting system designed to increase material recovery rates in urban recycling centers.",
                        LocalDate.of(2025, 7, 15),
                        Status.REJECTED),
                new Proposal(
                        andLight,
                        cityMobility,
                        "Development of a city-wide bike-sharing and carpooling platform powered by renewable energy sources.",
                        LocalDate.of(2025, 8, 1),
                        Status.PENDING));

        proposals.forEach(proposal -> {
            if (!service.existsByStartupAndChallenge(proposal.getStartup(), proposal.getChallenge())) {
                    System.out.println("Startup actual :" + proposal.getStartup().getEmail() );
                    System.out.println("Challenge titulo :" + proposal.getChallenge().getTitle());

                    Startup startup = proposal.getStartup();
                startup.addProposal(proposal);
                service.save(proposal);
            }
        });

    }
}
