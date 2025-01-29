package com.ecolink.spring.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecolink.spring.dto.DTOConverter;
import com.ecolink.spring.dto.PaginationResponse;
import com.ecolink.spring.dto.PostDTO;
import com.ecolink.spring.entity.Post;
import com.ecolink.spring.service.PostService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;
    private final DTOConverter postDTOConverter;

    @GetMapping
    public ResponseEntity<?> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        if (posts.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<PostDTO> dtoList = posts.stream().map(postDTOConverter::convertPostToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/p")
    public ResponseEntity<?> getPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<Post> posts = postService.findByPagination(page, size);

        if (posts.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<PostDTO> dtoList = posts.stream().map(postDTOConverter::convertPostToDto)
                .collect(Collectors.toList());

        var response = new PaginationResponse<>(
                dtoList,
                posts.getNumber(),
                posts.getSize(),
                posts.getTotalElements(),
                posts.getTotalPages(),
                posts.isLast());

                return ResponseEntity.ok(response);
    }

    @GetMapping("/recent")
    public ResponseEntity<?> getRecentPost() {
        List<Post> posts = postService.getRecentPost();
        if (posts.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<PostDTO> dtoList = posts.stream().map(postDTOConverter::convertPostToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }

}
