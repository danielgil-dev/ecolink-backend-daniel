package com.ecolink.spring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecolink.spring.dto.DTOConverter;
import com.ecolink.spring.dto.GetUserDTO;
import com.ecolink.spring.entity.UserBase;
import com.ecolink.spring.security.jwt.JwtProvider;
import com.ecolink.spring.security.jwt.model.JwtUserResponse;
import com.ecolink.spring.security.jwt.model.LoginRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtProvider tokenProvider;
    private final DTOConverter converter;

    @PostMapping("/auth/login")
    public ResponseEntity<JwtUserResponse> login(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(), loginRequest.getPassword()));


        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserBase user = (UserBase) authentication.getPrincipal();
        System.out.println(user.getEmail());
        String jwtToken = tokenProvider.generateToken(authentication);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(convertUserEntityAndTokenToJwtUserResponse(user, jwtToken));
    }

    private JwtUserResponse convertUserEntityAndTokenToJwtUserResponse(UserBase user, String jwtToken) {
        return JwtUserResponse.jwtUserResponseBuilder()
                .username(user.getUsername())
                .avatar(user.getImageUrl())
                .rol(user.getUserType())
                .token(jwtToken)
                .build();
    }

    @GetMapping("/user/me")
    public GetUserDTO me(@AuthenticationPrincipal UserBase user) {
        return converter.convertUserDTO(user);
    }
}