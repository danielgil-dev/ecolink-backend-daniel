package com.ecolink.spring.security.jwt;

import org.springframework.stereotype.Component;

import com.ecolink.spring.entity.UserBase;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import lombok.extern.java.Log;

@Log
@Component
public class JwtProvider {
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_TYPE = "JWT ";
    
    @Value("${spring.jwt.private-token}")
    private String jwtSecreto;

    private int jwtDurationTokenEnSegundos = 864000; // ! Son 10 dias en segundos

    public String generateToken(Authentication authentication) {
        UserBase user = (UserBase) authentication.getPrincipal();

        Date tokenExpirationDate = new Date(System.currentTimeMillis() + jwtDurationTokenEnSegundos * 1000);

        return Jwts.builder()
                .header().add("typ", TOKEN_TYPE).and()
                .subject(Long.toString(user.getId()))
                .issuedAt(new Date())
                .expiration(tokenExpirationDate)
                .claim("email", user.getUsername())
                .claim("userType", user.getUserType())
                .claim("imageUrl", user.getImageUrl())
                .claim("user", user.getName())
                .signWith(Keys.hmacShaKeyFor(jwtSecreto.getBytes()))
                .compact();
    }

    public Long getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(jwtSecreto.getBytes()))
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return Long.parseLong(claims.getSubject());
    }

    public boolean validateToken(String authToken) {
		
		try {
			Jwts.parser().verifyWith(Keys.hmacShaKeyFor(jwtSecreto.getBytes()))
					.build().parseSignedClaims(authToken);
			return true;
		} catch (SecurityException ex) {
			log.info("Error en la firma del token JWT: " + ex.getMessage());
		} catch (MalformedJwtException ex) {
			log.info("Token malformado: " + ex.getMessage());
		} catch (ExpiredJwtException ex) {
			log.info("El token ha expirado: " + ex.getMessage());
		} catch (UnsupportedJwtException ex) {
			log.info("Token JWT no soportado: " + ex.getMessage());
		} catch (IllegalArgumentException ex) {
			log.info("JWT claims vacío");
		}
		return false;
	}

}
