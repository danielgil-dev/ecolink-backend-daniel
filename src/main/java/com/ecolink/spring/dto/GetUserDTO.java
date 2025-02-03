package com.ecolink.spring.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.ecolink.spring.entity.UserType;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class GetUserDTO {
    private Long id;
    private String username;
	private UserType userType;
}
