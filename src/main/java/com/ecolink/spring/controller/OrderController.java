package com.ecolink.spring.controller;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecolink.spring.dto.DTOConverter;
import com.ecolink.spring.dto.OrderDTO;
import com.ecolink.spring.entity.Order;
import com.ecolink.spring.entity.OrderStatus;
import com.ecolink.spring.entity.UserBase;
import com.ecolink.spring.exception.ErrorDetails;
import com.ecolink.spring.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;
    private final DTOConverter dtoConverter;

    @GetMapping("/cart")
    public ResponseEntity<?> getCart(@AuthenticationPrincipal UserBase user) {
        try {
            if (user == null) {
                ErrorDetails errorDetails = new ErrorDetails(HttpStatus.UNAUTHORIZED, "Not authorized");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorDetails);
            }

            Order cart = orderService.getCart(user);

            if (cart == null) {
                cart = new Order(user, OrderStatus.CART, LocalDate.now());
                orderService.save(cart);
            }

            OrderDTO orderDTO = dtoConverter.convertOrderToDTO(cart);

            return ResponseEntity.ok(orderDTO);
        } catch (Exception e) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        }
    }
}
