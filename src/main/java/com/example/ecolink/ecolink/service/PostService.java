package com.example.ecolink.ecolink.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecolink.ecolink.repository.PostRepository;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
}
