package com.ecolink.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecolink.spring.entity.Ods;
import com.ecolink.spring.entity.Post;
import com.ecolink.spring.entity.Startup;
import com.ecolink.spring.repository.PostRepository;

@Service
public class PostService {
    @Autowired
    private PostRepository repository;

    public List<Post> getAllPosts(){
        return repository.findAll();
    }
    
    public void save(Post post) {
        repository.save(post);
    }

    public Boolean existsByTitleAndStartupAndOds(String title, Startup startup, Ods ods) {
        return repository.existsByTitleAndStartupAndOds(title, startup, ods);
    }

    public Post findByTitleAndStartupAndOds(String title, Startup startup, Ods ods){
        return repository.findByTitleAndStartupAndOds(title, startup, ods);
    }

    public Post findByTitle(String title) {
        return repository.findByTitle(title);
    }
}
