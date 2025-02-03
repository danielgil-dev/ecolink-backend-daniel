package com.ecolink.spring.security.jwt.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

	@NotBlank
	private String username;
	
	@NotBlank
	private String password;
}
