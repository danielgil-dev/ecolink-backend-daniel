package com.ecolink.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecolink.spring.entity.Ods;
import com.ecolink.spring.entity.Post;
import com.ecolink.spring.entity.Startup;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    public boolean existsByTitleAndStartupAndOds(String title, Startup startup, Ods ods);

    public Post findByTitleAndStartupAndOds(String title, Startup startup, Ods ods);

    public Post findByTitle(String title);

}
