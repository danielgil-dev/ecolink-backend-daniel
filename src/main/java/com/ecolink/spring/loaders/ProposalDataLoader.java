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

import com.ecolink.spring.entity.Status;

@Component
@Order(8)
public class ProposalDataLoader implements CommandLineRunner  {

    @Autowired
    public ProposalService  service;

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

        Challenge carbonReductionChallenge = challengeService.findByTitle("Reducción de emisiones de carbono");
        Challenge ruralRenewablesChallenge = challengeService.findByTitle("Energías renovables para comunidades rurales");
        Challenge waterManagementChallenge = challengeService.findByTitle("Gestión sostenible del agua");
        Challenge sustainableAgricultureChallenge = challengeService.findByTitle("Agricultura sostenible");
        Challenge wasteRecyclingChallenge = challengeService.findByTitle("Reciclaje y gestión de residuos");
    
        List<Proposal> proposals = Arrays.asList(
            new Proposal(vhat, carbonReductionChallenge, "Propuesta para implementar filtros innovadores en chimeneas industriales.", LocalDate.of(2024, 1, 15),Status.ACCEPTED ),
            new Proposal(gamingBuddy, ruralRenewablesChallenge, "Desarrollo de paneles solares portátiles para comunidades rurales.", LocalDate.of(2024, 2, 10), Status.PENDING),
            new Proposal(nors, waterManagementChallenge, "Tecnología para purificación de agua en zonas con sequía extrema.", LocalDate.of(2024, 3, 5), Status.PENDING),
            new Proposal(andLight, sustainableAgricultureChallenge, "Sistema de riego automatizado que minimiza el desperdicio de agua.", LocalDate.of(2024, 3, 20), Status.REJECTED),
            new Proposal(imHub, wasteRecyclingChallenge, "Campaña educativa sobre reciclaje inteligente en comunidades urbanas.", LocalDate.of(2024, 4, 1), Status.ACCEPTED),
            new Proposal(tooGoodToGo, carbonReductionChallenge, "Optimización de rutas logísticas para reducir emisiones en transporte.", LocalDate.of(2024, 4, 15), Status.REJECTED),
            new Proposal(doublepoint, sustainableAgricultureChallenge, "Drones para monitoreo de cultivos y reducción de pesticidas.", LocalDate.of(2024, 5, 10), Status.PENDING)
        );

        proposals.forEach(proposal ->{
            if(proposal.getId() == null || !service.existsById(proposal.getId())){
                System.out.println("Entrando en el foreach");
                service.save(proposal);
            }

            System.out.println(proposal.getId());
        });
        

    }
}
