package com.ecolink.spring.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckoutDTO {
    private String firstName = "";
    private String lastName = "";
    private String shippingPhone = "";
    private String shippingAddress = "";
    private String shippingCity = "";
    private String shippingCountry = "";
    private String shippingZipCode = "";
}
