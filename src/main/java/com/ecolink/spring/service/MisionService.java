package com.ecolink.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecolink.spring.repository.MisionRepository;

@Service
public class MisionService {

	@Autowired
	private MisionRepository misionRepository;

}
