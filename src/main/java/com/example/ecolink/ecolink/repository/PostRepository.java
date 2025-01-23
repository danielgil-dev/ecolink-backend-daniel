package com.example.ecolink.ecolink.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ecolink.ecolink.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
