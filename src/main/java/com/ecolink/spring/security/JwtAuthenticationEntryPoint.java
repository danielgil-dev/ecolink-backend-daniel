package com.ecolink.spring.security;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.ecolink.spring.exception.ErrorDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	private final ObjectMapper mapper;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {

		response.setContentType("application/json");
		response.setStatus(HttpStatus.UNAUTHORIZED.value());

		ErrorDetails errorDetails = new ErrorDetails(HttpStatus.UNAUTHORIZED, authException.getMessage());

		String strApiError = mapper.writeValueAsString(errorDetails);

		PrintWriter writer = response.getWriter();
		writer.println(strApiError);
	}

}
