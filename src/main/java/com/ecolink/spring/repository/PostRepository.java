package com.ecolink.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.ecolink.spring.entity.Ods;
import com.ecolink.spring.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>, JpaSpecificationExecutor<Post>  {


    public Post findByTitle(String title);

    public List<Post> findTop4ByOrderByPostDateDesc();

    public List<Post>  findTop10ByIdNotOrderByPostDateDesc(Long id);

    List<Post> findTop4ByOdsListInAndIdNotOrderByPostDateDesc(List<Ods> odsList, Long id);
}
