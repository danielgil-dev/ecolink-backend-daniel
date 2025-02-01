package com.ecolink.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecolink.spring.repository.UserBaseRepository;

@Service
public class UserBaseService {
    @Autowired
    private UserBaseRepository repository;  
}
