package com.ecolink.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecolink.spring.entity.Comment;
import com.ecolink.spring.entity.Post;
import com.ecolink.spring.entity.UserBase;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    public Boolean existsByUserAndPost(UserBase user, Post post);
}