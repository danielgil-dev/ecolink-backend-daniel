package com.ecolink.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecolink.spring.entity.Comment;
import com.ecolink.spring.entity.Post;
import com.ecolink.spring.entity.UserBase;
import com.ecolink.spring.repository.CommentRepository;

@Service
public class CommentService {
    @Autowired
    private CommentRepository repository;

    public void save(Comment comment) {
        repository.save(comment);
    }

    public Comment findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public boolean existsByUserAndPost(UserBase user, Post post) {
        return repository.existsByUserAndPost(user, post);
    }

    public void delete(Comment comment) {
        repository.delete(comment);
    }
}