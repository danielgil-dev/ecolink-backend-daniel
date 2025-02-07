package com.ecolink.spring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.ecolink.spring.dto.ProductPostDTO;
import com.ecolink.spring.entity.Comment;
import com.ecolink.spring.entity.Post;
import com.ecolink.spring.entity.UserBase;
import com.ecolink.spring.exception.ErrorDetails;
import com.ecolink.spring.service.CommentService;
import com.ecolink.spring.service.PostService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@RequestMapping("/api/comment")
@RequiredArgsConstructor
@RestController
public class CommentController {
    private final CommentService service;
    private final PostService postService;

    @PostMapping("/new")
    public ResponseEntity<?> newComment(@AuthenticationPrincipal UserBase user,
            @RequestBody String comment, @RequestBody Long id_post) {
        try {
            if (user == null) {
                ErrorDetails errorDetails = new ErrorDetails(HttpStatus.UNAUTHORIZED.value(),
                        "The user is not authenticated");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorDetails);

            }

            if (comment  == null ||comment.isEmpty() || id_post == null) {
                ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST.value(),
                        "The comment is not valid");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
                
            }
            
            Post post = postService.findById(id_post);
            if (post == null) {
                ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST.value(),
                        "The post is not valid");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
            }

            Comment newComment = new Comment();
            newComment.setComment(comment);
            newComment.setPost(post);
            newComment.setUser(user);

            service.save(newComment);

            return ResponseEntity.status(HttpStatus.CREATED).body(newComment);

        } catch (Exception e) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Ocurrió un error interno en el servidor");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        }
    }
}
