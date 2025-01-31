package com.ecolink.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ecolink.spring.entity.Ods;
import com.ecolink.spring.entity.Post;
import com.ecolink.spring.entity.Startup;
import com.ecolink.spring.repository.PostRepository;
import com.ecolink.spring.specification.PostSpecification;


@Service
public class PostService {
    @Autowired
    private PostRepository repository;

    public List<Post> getAllPosts(){
        return repository.findAll();
    }
    public List<Post> getRecentPost(){
        return repository.findTop4ByOrderByPostDateDesc();
    }
    
    public void save(Post post) {
        repository.save(post);
    }

    public Post findByTitle(String title) {
        return repository.findByTitle(title);
    }

    public Page<Post> findByFilterAndPagination(Startup startup , String title, List<Ods> ods, int page, int size) {
        Specification<Post> spec = PostSpecification.filters(startup, title, ods);
        Pageable pageable = PageRequest.of(page, size);
        return repository.findAll(spec, pageable);
    }

    public Post findById(Long id){

        return repository.findById(id).orElse(null);
    }
}

