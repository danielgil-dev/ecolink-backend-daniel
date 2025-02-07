package com.ecolink.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecolink.spring.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    
}