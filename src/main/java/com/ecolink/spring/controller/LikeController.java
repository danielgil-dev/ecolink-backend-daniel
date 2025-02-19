package com.ecolink.spring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecolink.spring.entity.Like;
import com.ecolink.spring.entity.Post;
import com.ecolink.spring.entity.UserBase;
import com.ecolink.spring.exception.ErrorDetails;
import com.ecolink.spring.exception.LikeAlredyExistsException;
import com.ecolink.spring.response.SuccessDetails;
import com.ecolink.spring.service.LikeService;
import com.ecolink.spring.service.PostService;
import com.ecolink.spring.service.UserBaseService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequestMapping("/api/like")
@RequiredArgsConstructor
@RestController
public class LikeController {
    private final LikeService service;
    private final PostService postService;
    private final UserBaseService userBaseService;

    @PostMapping
    public ResponseEntity<?> addLike(@AuthenticationPrincipal UserBase user, @RequestParam Long id_post) {
        try {
            if (user == null) {
                ErrorDetails errorDetails = new ErrorDetails(HttpStatus.UNAUTHORIZED.value(),
                        "The user must be logged in");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorDetails);
            }

            Post post = postService.findById(id_post);

            boolean likeExists = service.existsByPostAndUser(post, user);

            if (likeExists) {
                throw new LikeAlredyExistsException("The user has already liked the post");
            }

            Like like = new Like(post, user);
            user.addXp(10L);
            service.save(like);
            userBaseService.save(user);
            
            SuccessDetails successDetails = new SuccessDetails(HttpStatus.CREATED.value(),
                    "Like created successfully");

            return ResponseEntity.status(HttpStatus.CREATED).body(successDetails);

        } catch (LikeAlredyExistsException e) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        } catch (Exception e) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> removeLike(@AuthenticationPrincipal UserBase user, @RequestParam Long id_post) {
        try {
            if (user == null) {
                ErrorDetails errorDetails = new ErrorDetails(HttpStatus.UNAUTHORIZED.value(),
                        "The user must be logged in");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorDetails);
            }

            Post post = postService.findById(id_post);

            boolean likeExists = service.existsByPostAndUser(post, user);

            if (!likeExists) {
                throw new LikeAlredyExistsException("The user has not yet liked the post");
            }

            Like like = service.findByPostAndUser(post, user);
            if (like == null) {
                throw new LikeAlredyExistsException("The user has alredy liked the post");
            }
            user.removeXp(10L);
            service.delete(like);
            userBaseService.save(user);
            
            SuccessDetails successDetails = new SuccessDetails(HttpStatus.CREATED.value(), "Like deleted successfully");

            return ResponseEntity.status(HttpStatus.CREATED).body(successDetails);

        } catch (LikeAlredyExistsException e) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        } catch (Exception e) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        }
    }

}
