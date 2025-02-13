package com.ecolink.spring.dto;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PayDTO {
    @NotBlank(message = "Card number cannot be blank")
    @Pattern(regexp = "\\d{16}", message = "Card number must be 16 digits")
    private String cardNumber;

    @NotBlank(message = "Card holder name cannot be blank")
    private String cardHolder;

    @NotBlank(message = "Expiration date cannot be blank")
    @Pattern(regexp = "(0[1-9]|1[0-2])\\/\\d{2}", message = "Expiration must be in MM/YY format")
    private String cardExpiration;

    @NotBlank(message = "CVC cannot be blank")
    @Pattern(regexp = "\\d{3,4}", message = "CVC must be 3 or 4 digits")
    private String cardCVC;

    private String cardType;
    private String cardCountry;
    private String cardZipCode;
}
