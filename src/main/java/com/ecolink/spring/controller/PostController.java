package com.ecolink.spring.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.internal.bytebuddy.TypeCache.Sort;
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
import com.ecolink.spring.dto.PostItemPageDTO;
import com.ecolink.spring.dto.PostRelevantDTO;
import com.ecolink.spring.entity.Ods;
import com.ecolink.spring.entity.Post;
import com.ecolink.spring.entity.SortType;
import com.ecolink.spring.exception.ErrorDetails;
import com.ecolink.spring.exception.PostNotFoundException;
import com.ecolink.spring.service.OdsService;
import com.ecolink.spring.service.PostService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;
    private final OdsService odsService;
    private final DTOConverter postDTOConverter;

    @GetMapping
    public ResponseEntity<?> getPosts(
            @RequestParam(required = false) String startupName,
            @RequestParam(required = false) String title,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) List<Long> odsIdList,
            @RequestParam(required =  false) SortType sortLikesBy,
            @RequestParam(required =  false) SortType sortCreatedBy
            ) {

        try {

            List<Ods> odsList = new ArrayList<>();
            if (odsIdList != null && !odsIdList.isEmpty()) {
                odsIdList.forEach(odsId -> {
                    Ods ods = odsService.findById(odsId);
                    if (ods != null) {
                        odsList.add(ods);
                    }
                });
            }

            if (sortLikesBy == null) {
                sortLikesBy = SortType.DESC;
            }
            if (sortCreatedBy == null) {
                sortCreatedBy = SortType.DESC;
            }

            Page<Post> posts = postService.findByFilterAndPagination(startupName, title, odsList, page, size, sortLikesBy, sortCreatedBy);

            if (posts.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDetails(HttpStatus.NOT_FOUND.value(),
                        "No se encontraron post en la página especificada"));
            }

            List<PostItemPageDTO> dtoList = posts.getContent().stream().map(postDTOConverter::convertPostItemPageToDTO)
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
            System.out.println(e.getMessage());
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
            System.out.println(e.getMessage());
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Ocurrió un error interno en el servidor");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        }
    }

    @GetMapping("/relevant/{id}")
    public ResponseEntity<?> getRelevantPost(@PathVariable Long id) {

        try {
            Post post = postService.findById(id);
            if (post == null) {
                throw new PostNotFoundException("No existe un post por el id " + id);
            }
            List<Ods> odsList = post.getOdsList();
            List<Post> posts = postService.getRelevantPost(odsList, post.getId());

            if (posts.size() < 4) {
                int size = 4 - posts.size();
                if (size > 0) {
                    List<Post> otherPosts = postService.getRecentPostIngoringPosts(post.getId());
                    int index = 0;
                    while (posts.size() < 4 && index < otherPosts.size()) {
                        System.out.println("Entrado en el bucle");
                        Post otherPost = otherPosts.get(index);
                        if (!posts.contains(otherPost) && !otherPost.getId().equals(post.getId())) {
                            posts.add(otherPost);
                        }
                        index++;
                    }
                } else {
                    posts = postService.getRecentPostIngoringPosts(post.getId());
                }
            }

            List<PostRelevantDTO> dtoList = posts.stream().map(postDTOConverter::convertPostRelevantToDTO)
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
}
