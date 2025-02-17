package com.ecolink.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ecolink.spring.entity.Ods;
import com.ecolink.spring.entity.Post;
import com.ecolink.spring.entity.SortType;
import com.ecolink.spring.repository.PostRepository;
import com.ecolink.spring.specification.PostSpecification;

@Service
public class PostService {
    @Autowired
    private PostRepository repository;

    public List<Post> getAllPosts() {
        return repository.findAll();
    }

    public List<Post> getRecentPost() {
        return repository.findTop4ByOrderByPostDateDesc();
    }

    public void save(Post post) {

        if (post.getComments() == null || post.getComments().size() == 0) {
            post.setComments(new ArrayList<>());
        }
        repository.save(post);
    }

    public Post findByTitle(String title) {
        return repository.findByTitle(title);
    }

    public Page<Post> findByFilterAndPagination(String startupName, String title, List<Ods> ods, int page, int size,
            SortType sortLikesBy, SortType sortCreatedBy) {
        Specification<Post> spec = PostSpecification.filters(startupName, title, ods, sortLikesBy, sortCreatedBy);
        Pageable pageable = PageRequest.of(page, size);
        return repository.findAll(spec, pageable);
    }

    public Post findById(Long id) {

        return repository.findById(id).orElse(null);
    }

    public List<Post> getRelevantPost(List<Ods> odsList, Long id) {
        return repository.findTop4ByOdsListInAndIdNotOrderByPostDateDesc(odsList, id);
    }

    public List<Post> getRecentPostIngoringPosts(Long id) {
        return repository.findTop10ByIdNotOrderByPostDateDesc(id);
    }

    public void delete(Post post) {
        repository.delete(post);
    }
}
