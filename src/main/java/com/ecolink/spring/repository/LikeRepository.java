package com.ecolink.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecolink.spring.entity.Like;
import com.ecolink.spring.entity.Post;
import com.ecolink.spring.entity.UserBase;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    boolean existsByPostAndUser(Post post, UserBase user);
}
