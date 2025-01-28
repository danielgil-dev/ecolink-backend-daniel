package com.ecolink.spring.controller;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecolink.spring.dto.DTOConverter;
import com.ecolink.spring.dto.PostDTO;
import com.ecolink.spring.entity.Post;
import com.ecolink.spring.service.PostService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;
    private final DTOConverter postDTOConverter;

    @GetMapping
    public ResponseEntity<?>  getAllPosts(){
        List<Post> posts = postService.getAllPosts();
        if(posts.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        List<PostDTO> dtoList = posts.stream().map(postDTOConverter::convertPostToDTO)
        .collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }
}
