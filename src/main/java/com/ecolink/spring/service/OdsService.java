package com.ecolink.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecolink.spring.repository.OdsRepository;

@Service
public class OdsService {
    @Autowired
    private OdsRepository odsRepository;
}
