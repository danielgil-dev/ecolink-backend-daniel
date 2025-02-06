package com.ecolink.spring.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecolink.spring.entity.UserBase;
import com.ecolink.spring.repository.UserBaseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserBaseService {
    @Autowired
    private UserBaseRepository repository;

    private final PasswordEncoder passwordEncoder;

    public UserBase newUser(UserBase user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    public Optional<UserBase> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public Optional<UserBase> findById(Long id) {
        return repository.findById(id);
    }

    public Boolean existsByEmail(String email){
        return repository.existsByEmail(email);
    }

    public void save(UserBase user){
        repository.save(user);
    }
}
