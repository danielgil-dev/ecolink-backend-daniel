package com.ecolink.spring.loaders;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.ecolink.spring.entity.Company;
import com.ecolink.spring.service.CompanyService;

@Component
@Order(5)
public class CompanyDataLoader implements CommandLineRunner {

    @Autowired
    private CompanyService service;

    public void run(String... args) {
        List<Company> companyList = Arrays.asList(
                new Company("EcoSolutions", "eco@solutions.com", 1L,
                        "Empresa dedicada al desarrollo de tecnologías sostenibles para la gestión de residuos y la reducción de la huella de carbono."),
                new Company("GreenTech", "contact@greentech.com", 2L,
                        "Startup especializada en soluciones de energía renovable, como paneles solares y turbinas eólicas de alta eficiencia."),
                new Company("SolarInnovators", "info@solarinno.com", 3L,
                        "Compañía líder en el diseño y fabricación de paneles solares innovadores con materiales de última generación."),
                new Company("BioFuture", "support@biofuture.com", 2L,
                        "Empresa biotecnológica enfocada en la creación de bioplásticos y materiales biodegradables para reducir la contaminación."),
                new Company("UrbanRenew", "urban@renew.com", 1L,
                        "Iniciativa que desarrolla soluciones para ciudades inteligentes, incluyendo movilidad sostenible y eficiencia energética en edificios urbanos.")

        );

        companyList.forEach(company -> {
            if (!service.existsByName(company.getName())) {
                service.save(company);
            }
        });
    }
}