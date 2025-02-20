package com.ecolink.spring.loaders;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.ecolink.spring.entity.Company;
import com.ecolink.spring.entity.Status;
import com.ecolink.spring.service.CompanyService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Order(7)
public class CompanyDataLoader implements CommandLineRunner {

        @Autowired
        private CompanyService service;

        private final PasswordEncoder passwordEncoder;

        public void run(String... args) {
                String defaultPassword = "password";
                List<Company> companyList = Arrays.asList(
                                new Company("Tesla", "contact@tesla.com",
                                                "We innovate in electric mobility, sustainable energy, and storage for a cleaner future.", "tesla.jpg"),
                                new Company("Google", "sustainability@google.com"
                                ,
                                                "Revolutionizing technology with search, AI, cloud, and more. Always pushing the limits.", "google.jpg"),
                                new Company("Apple", "environment@apple.com",
                                                "We create products that blend design, innovation, and privacy, shaping the future of technology.", "apple.jpg"),
                                new Company("Microsoft", "eco@microsoft.com",
                                                "Developing software, hardware, and cloud services to empower people and businesses worldwide.", "microsoft.jpg"),
                                new Company("Amazon", "sustainability@amazon.com",
                                                "Transforming the way the world shops, works, and connects through technology.", "amazon.jpg"));

                companyList.forEach(company -> {
                        if (!service.existsByName(company.getName())) {
                                company.setPassword(passwordEncoder.encode(defaultPassword));
                                company.setVerified(true);
                                company.setStatus(Status.ACCEPTED);
                                service.save(company);
                        }
                });
        }
}

// usaurio
// ods
// categoria de productos
// roles usaurio