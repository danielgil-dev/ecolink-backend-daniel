package com.ecolink.spring.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecolink.spring.service.CommentService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/comment")
@RestController
public class CommentController {
    private final CommentService service;

    @PostMapping("/new")
    public ResponseEntity<?> newComment(AuthenticationPrincipal UserBase user, @RequestPart("product") String productJson){

            }
}
