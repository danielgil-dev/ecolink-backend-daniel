package com.ecolink.spring.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckoutDTO {
    private String shippingAddress = "";
    private String shippingCity = "";
    private String shippingZipCode = "";
    private String shippingCountry = "";
    private String shippingPhone = "";
}
