package com.ecolink.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecolink.spring.repository.PostRepository;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
}
