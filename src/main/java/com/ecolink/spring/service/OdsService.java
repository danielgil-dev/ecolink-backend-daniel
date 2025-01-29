package com.ecolink.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecolink.spring.entity.Ods;
import com.ecolink.spring.repository.OdsRepository;

@Service
public class OdsService {
    @Autowired
    private OdsRepository repository;


    public Boolean existByName(String name){
        return repository.existsByName(name);
    }

    public void save(Ods ods){
        repository.save(ods);
    }

    public Ods findByName(String name){
        return repository.findByName(name);
    }

    public Ods findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Ods> findAllById(List<Long> odsIds){
        return repository.findAllById(odsIds);
    }
}
