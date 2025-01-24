
package com.ecolink.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecolink.spring.repository.StartupRepository;

@Service
public class StartupService {
    @Autowired
    private StartupRepository repository;
}
