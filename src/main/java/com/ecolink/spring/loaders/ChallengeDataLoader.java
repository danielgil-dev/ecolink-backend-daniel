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

        Company ecoSolutions = companyService.findByName("EcoSolutions");
               
        Company greenTech = companyService.findByName("GreenTech");
              
        Company solarInnovators = companyService.findByName("SolarInnovators");
             
        Company bioFuture = companyService.findByName("BioFuture");
            
        Company urbanRenew = companyService.findByName("UrbanRenew");  


        List<Challenge> challenges = Arrays.asList(
                new Challenge( ecoSolutions,
                        "Reducción de emisiones de carbono",
                        "Desarrollar soluciones innovadoras para reducir las emisiones de carbono en procesos industriales.",
                        new BigDecimal("100000.00"),
                        LocalDateTime.of(2025, 2, 6, 0, 0),
                        Arrays.asList(
                                odsService.findByName("Acción por el clima"))),
                new Challenge( greenTech,
                        "Energías renovables para comunidades rurales",
                        "Implementar sistemas de energía solar en comunidades rurales sin acceso a la electricidad.",
                        new BigDecimal("150000.00"),
                        LocalDateTime.of(2025, 2, 12, 0, 0),
                        Arrays.asList(
                                odsService.findByName("Energía asequible y no contaminante"),
                                odsService.findByName("Industria, innovación e infraestructura"))),
                new Challenge( solarInnovators,
                        "Gestión sostenible del agua",
                        "Crear tecnologías para la gestión eficiente del agua en zonas con escasez hídrica.",
                        new BigDecimal("80000.00"),
                        LocalDateTime.of(2025, 2, 24, 0, 0),
                        Arrays.asList(
                                odsService.findByName("Agua limpia y saneamiento"))),
                new Challenge( bioFuture,
                        "Agricultura sostenible",
                        "Desarrollar prácticas agrícolas que reduzcan el uso de pesticidas y promuevan la biodiversidad.",
                        new BigDecimal("120000.00"),
                        LocalDateTime.of(2025, 3, 6, 0, 0),
                        Arrays.asList(
                                odsService.findByName("Hambre cero"),
                                odsService.findByName("Vida de ecosistemas terrestres"))),
                new Challenge( urbanRenew,
                        "Reciclaje y gestión de residuos",
                        "Innovar en sistemas de reciclaje y gestión de residuos para reducir la contaminación por plásticos.",
                        new BigDecimal("90000.00"),
                        LocalDateTime.of(2025, 3, 12, 0, 0),
                        Arrays.asList(
                                odsService.findByName("Producción y consumo responsables"))));

        challenges.forEach(challenge -> {
            if (!service.existsByTitle(challenge.getTitle())) {
                service.save(challenge);
            }
        });
    }
}
