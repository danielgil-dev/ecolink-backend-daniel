package com.ecolink.spring.service;

import java.util.List;

import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.ecolink.spring.entity.Company;
import com.ecolink.spring.entity.Ods;
import com.ecolink.spring.entity.SortType;
import com.ecolink.spring.entity.Startup;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CompatibilityService {

    private final CompanyService companyService;
    private final StartupService startupService;

    public double calculateCompatibility(Company company, Startup startup) {
        double compatibilityScore = 0.0;

        // Calcular puntuación de ODS (máximo 80 puntos)
        double odsScore = calculateOdsCompability(company, startup);

        // Calcular puntuación de ubicación (máximo 20 puntos)
        double locationScore = calculateLocationCompability(company, startup);

        // Sumar ambas puntuaciones
        compatibilityScore = odsScore + locationScore;

        return compatibilityScore;
    }

    public double calculateOdsCompability(Company company, Startup startup) {
        double odsCompatibility = 0.0;
        int countOds = 0;
        List<Ods> companyOds = company.getOdsList();
        List<Ods> startupOds = startup.getOdsList();
        for (Ods ods : companyOds) {
            if (startupOds.contains(ods)) {
                countOds++;
            }
        }

        odsCompatibility = countOds * 40;
        if (odsCompatibility > 80) {
            odsCompatibility = 80;
        }
        return odsCompatibility;
    }

    public double calculateLocationCompability(Company company, Startup startup) {
        double locationCompability = 0.0;

        if (company.getLocation() != null && startup.getLocation() != null) {
            if (company.getLocation().equalsIgnoreCase(startup.getLocation().trim())) {
                locationCompability = 20;
            }
        }

        return locationCompability;
    }

    public Page<Startup> findCompatibleStartupsForCompany(Company company, int page, int size, SortType order,
            String locationFilter) {

        Pageable pageable = PageRequest.of(page, size);
        List<Startup> allStartups = startupService.findAll();

        for (Startup startup : allStartups) {
            startup.setCompapility(calculateCompatibility(company, startup));
        }
        // Removemos aquellas que no tengan ninguna compatibatibilidad
        allStartups.removeIf(startup -> startup.getCompability() <= 0);

        List<Startup> filteredStartups = allStartups.stream().filter(startup -> {

            // Si no hay un pais por el cual buscar devolvemos todas
            if (locationFilter == null || locationFilter.isEmpty()) {return true; }

            // Si hay un país por el cual buscar, seleccinoaremos todas las startups que
            // pertenezcan a ese país
            return startup.getLocation() != null && startup.getLocation().equalsIgnoreCase(locationFilter.trim());
        }).collect(Collectors.toList());

        if (order == SortType.DESC) {
            filteredStartups
                    .sort((startup1, startup2) -> Double.compare(startup2.getCompability(), startup1.getCompability()));
        } else {

            filteredStartups
                    .sort((startup1, startup2) -> Double.compare(startup1.getCompability(), startup2.getCompability()));

        }

        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), filteredStartups.size());

        return new PageImpl<>(filteredStartups.subList(start, end), pageable,
                filteredStartups.size());
    }

    public Page<Company> findCompatiblesCompanyForStartup(Startup startup, int page, int size, SortType order,
            String locationFilter) {

        Pageable pageable = PageRequest.of(page, size);

        List<Company> allCompanies = companyService.findAll();
        for (Company company : allCompanies) {

            company.setCompability(calculateCompatibility(company, startup));
        }

        // Removemos aquellas que no tengan ninguna compatibatibilidad
        allCompanies.removeIf(company -> company.getCompability() <= 0);

        List<Company> filteredCompanies = allCompanies.stream().filter(company -> {

            // Comprobamamos que haya algun pais por el cual buscar
            if (locationFilter == null || locationFilter.isEmpty()) {
                return true;
            }

            // Si si existe devolvemos una lista con el país que se solicita
            return company.getLocation() != null && company.getLocation().equalsIgnoreCase(locationFilter.trim());
        }).collect(Collectors.toList());
        if (order == SortType.DESC) {
            filteredCompanies
                    .sort((company1, company2) -> Double.compare(company2.getCompability(), company1.getCompability()));
        } else {
            filteredCompanies
                    .sort((company1, company2) -> Double.compare(company1.getCompability(), company2.getCompability()));
        }

        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), filteredCompanies.size());

        return new PageImpl<>(filteredCompanies.subList(start, end), pageable,
                filteredCompanies.size());
    }

}
