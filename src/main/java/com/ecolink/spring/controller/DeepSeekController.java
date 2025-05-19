package com.ecolink.spring.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecolink.spring.service.DeepSeekService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/deepseek")
public class DeepSeekController {

    private final DeepSeekService deepSeekService;

    @PostMapping("/ods")
    public ResponseEntity<?> getODSByDescription(@RequestBody  String description) {
        System.out.println("This is the description: " + description);
        return ResponseEntity.ok().body(deepSeekService.getOdsByDescription(description));
    }
    
}
