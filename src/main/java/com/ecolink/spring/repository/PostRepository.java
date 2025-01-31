package com.ecolink.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecolink.spring.entity.Ods;
import com.ecolink.spring.entity.Post;
import com.ecolink.spring.entity.Startup;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {


    public Post findByTitle(String title);

    public List<Post> findTop4ByOrderByPostDateDesc();

}
