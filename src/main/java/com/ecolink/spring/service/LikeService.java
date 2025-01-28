package com.ecolink.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecolink.spring.entity.Like;
import com.ecolink.spring.entity.Post;
import com.ecolink.spring.entity.UserBase;
import com.ecolink.spring.repository.LikeRepository;

@Service
public class LikeService {
    @Autowired
    private LikeRepository repository;


    public boolean existsByPostAndUser(Post post, UserBase user){
        return repository.existsByPostAndUser(post, user);
    }

    public Long countByPostId(Long postId){
        return repository.countByPostId(postId);
    }

    public void save(Like like) {
       repository.save(like);
    }
}
