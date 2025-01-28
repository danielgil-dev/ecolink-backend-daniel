package com.ecolink.spring.loaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import com.ecolink.spring.entity.Challenge;
import com.ecolink.spring.entity.Startup;
import com.ecolink.spring.service.ChallengeService;
import com.ecolink.spring.service.StartupService;

public class ProposalDataLoader implements CommandLineRunner  {

    @Autowired
    public StartupService startupService;

    @Autowired
    public ChallengeService challengeService;

    public void run (String... args){

        Startup vhat = startupService.findByName("VhAT");
        Startup gamingBuddy = startupService.findByName("GamingBuddy");
        Startup nors = startupService.findByName("Nørs");
        Startup andLight = startupService.findByName("AndLight");
        Startup imHub = startupService.findByName("Influencer Marketing Hub");
        Startup tooGoodToGo = startupService.findByName("Too Good To Go");
        Startup doublepoint = startupService.findByName("Doublepoint");

        Challenge

    }
}
