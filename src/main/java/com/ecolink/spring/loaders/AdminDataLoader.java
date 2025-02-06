package com.ecolink.spring.loaders;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.ecolink.spring.entity.Admin;
import com.ecolink.spring.service.AdminService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Order(11)
public class AdminDataLoader implements CommandLineRunner {

    private final PasswordEncoder passwordEncoder;
    private final AdminService service;

    @Override
    public void run(String... args) throws Exception {
        String defaultPassword = "password";
        List<Admin> admins = Arrays.asList(
                new Admin("Daniel", "daniel@admin.com", defaultPassword),
                new Admin("Aaron", "aaron@admin.com", defaultPassword),
                new Admin("Cristian", "cristian@admin.com", defaultPassword),
                new Admin("Lorenzo", "lorenzo@admin.com", defaultPassword),
                new Admin("Carlos", "carlos@admin.com", defaultPassword));

        admins.forEach(admin -> {
            if (!service.existsByEmail(admin.getEmail())) {
                admin.setPassword(passwordEncoder.encode(admin.getPassword()));
                service.save(admin);
            }
        });

    }

}
