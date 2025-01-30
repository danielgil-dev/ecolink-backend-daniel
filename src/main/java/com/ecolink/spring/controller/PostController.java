package com.ecolink.spring.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecolink.spring.dto.DTOConverter;
import com.ecolink.spring.dto.PaginationResponse;
import com.ecolink.spring.dto.PostDTO;
import com.ecolink.spring.entity.Post;
import com.ecolink.spring.exception.ErrorDetails;
import com.ecolink.spring.exception.PostNotFoundException;
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

        try {

            List<Post> posts = postService.getAllPosts();
            if (posts.isEmpty()) {
                throw new PostNotFoundException("No se encontraron post en la base de datos");
            }
            List<PostDTO> dtoList = posts.stream().map(postDTOConverter::convertPostToDto)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(dtoList);

        } catch (PostNotFoundException e) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);

        } catch (Exception e) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Ocurrio un error interno en el servidor");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        }

    }

    @GetMapping("/pagination")
    public ResponseEntity<?> getPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        try {
            Page<Post> posts = postService.findByPagination(page, size);

            if (posts.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDetails(HttpStatus.NOT_FOUND.value(),
                        "No se encontraron post en la página especificada"));
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
        } catch (PostNotFoundException e) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
        } catch (Exception e) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Ocurrió un error interno en el servidor");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        }

    }

    @GetMapping("/recent")
    public ResponseEntity<?> getRecentPost() {

        try {

            List<Post> posts = postService.getRecentPost();
            if (posts.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ErrorDetails(HttpStatus.NOT_FOUND.value(), "No se encontraron post relevantes"));
            }

            List<PostDTO> dtoList = posts.stream().map(postDTOConverter::convertPostToDto)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(dtoList);
        } catch (PostNotFoundException e) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
        } catch (Exception e) {

            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Ocurrió un error interno en el servidor");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMethodName(@PathVariable Long id) {

        try {
            Post post = postService.findById(id);
            if (post == null) {
                throw new PostNotFoundException("No existe un post por el id " + id);
            }
            PostDTO dtoPost = postDTOConverter.convertPostToDto(post);

            return ResponseEntity.ok(dtoPost);
        } catch (PostNotFoundException e) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
        } catch (Exception e) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Ocurrió un error interno en el servidor");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        }
    }

}
