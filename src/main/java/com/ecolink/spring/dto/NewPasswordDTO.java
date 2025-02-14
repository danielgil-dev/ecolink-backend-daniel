package com.ecolink.spring.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewPasswordDTO {
    private String email;
    private String code;
    private String newPassword;
}
