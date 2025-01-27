package com.ecolink.spring.loaders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;

import com.ecolink.spring.entity.Company;
import com.ecolink.spring.repository.CompanyRepository;
import com.ecolink.spring.entity.userType;

@Component
@Order(3)
public class CompanyDataLoader implements CommandLineRunner {

    @Autowired
    private CompanyRepository repository;

    @Override
    public void run(String... args) throws Exception {
        List<Company> companyList = Arrays.asList(
            new Company(null, "EcoSolutions", "eco@solutions.com", "password123", 1L, LocalDate.of(2023, 1, 15)),
            new Company(null,"GreenTech", "contact@greentech.com", "securepass", 2L, LocalDate.of(2023, 2, 10)),
            new Company(null, "SolarInnovators", "info@solarinno.com", "solarpower", 3L, LocalDate.of(2023, 3, 5)),
            new Company(null, "BioFuture", "support@biofuture.com", "future2023", 2L, LocalDate.of(2023, 4, 1)),
            new Company(null, "UrbanRenew", "urban@renew.com", "urbanpass", 1L, LocalDate.of(2023, 5, 20))
        );

        repository.saveAll(companyList); // Guarda todas las compañías en la base de datos
    }
}
