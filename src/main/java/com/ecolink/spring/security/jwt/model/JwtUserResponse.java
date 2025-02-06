package com.ecolink.spring.security.jwt.model;


import com.ecolink.spring.dto.GetUserDTO;
import com.ecolink.spring.entity.UserType;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JwtUserResponse extends GetUserDTO {

	private String token;
	
	@Builder(builderMethodName="jwtUserResponseBuilder")
	public JwtUserResponse(Long id, String username, UserType rol, String imageUrl,  String token) {
		super(id, username, rol, imageUrl);
		this.token = token;
	}
}
