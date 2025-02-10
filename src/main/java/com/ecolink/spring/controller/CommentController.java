package com.ecolink.spring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecolink.spring.entity.Admin;
import com.ecolink.spring.entity.Comment;
import com.ecolink.spring.entity.Post;
import com.ecolink.spring.entity.UserBase;
import com.ecolink.spring.exception.CommentNotValidException;
import com.ecolink.spring.exception.ErrorDetails;
import com.ecolink.spring.service.CommentService;
import com.ecolink.spring.service.PostService;
import com.ecolink.spring.service.UserBaseService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/api/comment")
@RequiredArgsConstructor
@RestController
public class CommentController {
    private final CommentService service;
    private final PostService postService;
    private final UserBaseService userBaseService;

    @PostMapping("/new")
    public ResponseEntity<?> newComment(@AuthenticationPrincipal UserBase user,
            @RequestParam String comment,
            @RequestParam("id_post") Long idPost) {
        try {
            if (user == null) {
                ErrorDetails errorDetails = new ErrorDetails(HttpStatus.UNAUTHORIZED.value(),
                        "The user is not authenticated");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorDetails);
            }

            if (comment == null || comment.isEmpty() || idPost == null) {
                ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST.value(),
                        "The comment is not valid");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
            }

            Post post = postService.findById(idPost);
            if (post == null) {
                ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST.value(),
                        "The post is not valid");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
            }

            Boolean exists = service.existsByUserAndPost(user, post);

            if (exists) {
                throw new CommentNotValidException("The user already commented on this post");
            }

            Comment newComment = new Comment();

            newComment.setComment(comment);
            newComment.setPost(post);
            newComment.setUser(user);

            user.addComment(newComment);

            service.save(newComment);
            userBaseService.save(user); 

            return ResponseEntity.status(HttpStatus.CREATED).body(newComment);

        } catch (CommentNotValidException e) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST.value(), e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Ocurrió un error interno en el servidor");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateComment(@AuthenticationPrincipal UserBase user, @PathVariable Long id,
            @RequestParam String comment) {
        try {
            if (user == null) {
                ErrorDetails errorDetails = new ErrorDetails(HttpStatus.UNAUTHORIZED.value(),
                        "The user is not authenticated");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorDetails);

            }

            Comment commentToUpdate = service.findById(id);
            if (commentToUpdate == null || comment.isEmpty()) {
                ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST.value(),
                        "The comment is not valid");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
            }
            if (!commentToUpdate.getUser().equals(commentToUpdate.getUser())) {
                ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST.value(),
                        "The user is not the owner of the comment");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
            }

            commentToUpdate.setComment(comment);
            service.save(commentToUpdate);

            return ResponseEntity.status(HttpStatus.OK).body(commentToUpdate);

        } catch (Exception e) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Ocurrió un error interno en el servidor");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@AuthenticationPrincipal UserBase user, @PathVariable Long id) {
        try {
            if (user == null) {
                ErrorDetails errorDetails = new ErrorDetails(HttpStatus.UNAUTHORIZED.value(),
                        "The user is not authenticated");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorDetails);

            }

            Comment commentToDelete = service.findById(id);
            if (commentToDelete == null) {
                ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST.value(),
                        "The comment is not valid");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
            }

            if (!commentToDelete.getUser().getId().equals(user.getId()) && !(user instanceof Admin)) {
                ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST.value(),
                        "The user is not the owner of the comment");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
            }

            Post post = commentToDelete.getPost();

            if (post == null) {
                ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST.value(),
                        "The post is not valid");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
            }

            post.removeComment(commentToDelete);
            postService.save(post);

            return ResponseEntity.status(HttpStatus.OK).body(commentToDelete);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Ocurrió un error interno en el servidor");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        }
    }
}
