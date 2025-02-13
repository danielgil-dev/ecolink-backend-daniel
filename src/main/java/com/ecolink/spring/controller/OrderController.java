package com.ecolink.spring.controller;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecolink.spring.dto.CheckoutDTO;
import com.ecolink.spring.dto.DTOConverter;
import com.ecolink.spring.dto.OrderDTO;
import com.ecolink.spring.entity.Order;
import com.ecolink.spring.entity.OrderLine;
import com.ecolink.spring.entity.OrderStatus;
import com.ecolink.spring.entity.Product;
import com.ecolink.spring.entity.UserBase;
import com.ecolink.spring.exception.ErrorDetails;
import com.ecolink.spring.exception.OrderLineNotFoundException;
import com.ecolink.spring.exception.ProductNotFoundException;
import com.ecolink.spring.service.OrderLineService;
import com.ecolink.spring.service.OrderService;
import com.ecolink.spring.service.ProductService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;
    private final OrderLineService orderLineService;
    private final ProductService productService;
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

    @PostMapping("/add-product")
    public ResponseEntity<?> addProductToCart(@AuthenticationPrincipal UserBase user, @RequestBody Long id_product,
            @RequestBody Integer amount) {
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
            Product product = productService.findById(id_product);

            if (product == null) {
                throw new ProductNotFoundException("Product not found");
            }

            OrderLine orderLine = orderLineService.findByOrderAndProduct(cart, product);
            if (amount == null || amount <= 0) {
                amount = 1;
            }
            if (orderLine == null) {
                orderLine = new OrderLine();
                orderLine.setOrder(cart);
                orderLine.setProduct(product);
                orderLine.setAmount(amount);
            } else {
                orderLine.setAmount(orderLine.getAmount() + amount);
            }

            cart.addOrderLine(orderLine);
            orderLineService.save(orderLine);
            orderService.save(cart);

            OrderDTO orderDTO = dtoConverter.convertOrderToDTO(cart);

            return ResponseEntity.ok(orderDTO);
        } catch (ProductNotFoundException e) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.NOT_FOUND, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
        }

        catch (Exception e) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        }
    }

    @DeleteMapping("/remove-product")
    public ResponseEntity<?> removeProductFromCart(@AuthenticationPrincipal UserBase user,
            @RequestBody Long id_orderLine) {
        try {
            if (user == null) {
                ErrorDetails errorDetails = new ErrorDetails(HttpStatus.UNAUTHORIZED, "Not authorized");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorDetails);
            }

            Order cart = orderService.getCart(user);

            if (cart == null) {
                throw new Exception("Cart not found");
            }

            OrderLine orderLine = orderLineService.findByIdAndOrder(id_orderLine, cart);
            if (orderLine == null) {
                throw new OrderLineNotFoundException("OrderLine not found");
            }

            cart.removeOrderLine(orderLine);

            orderLineService.delete(orderLine);

            orderService.save(cart);

            OrderDTO orderDTO = dtoConverter.convertOrderToDTO(cart);

            return ResponseEntity.ok(orderDTO);
        } catch (ProductNotFoundException | OrderLineNotFoundException e) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.NOT_FOUND, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
        }

        catch (Exception e) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        }
    }

    @PutMapping("/update-product")
    public ResponseEntity<?> updateProductInCart(@AuthenticationPrincipal UserBase user, @RequestBody Long id_orderLine,
            @RequestBody Integer amount) {
        try {
            if (user == null) {
                ErrorDetails errorDetails = new ErrorDetails(HttpStatus.UNAUTHORIZED, "Not authorized");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorDetails);
            }

            Order cart = orderService.getCart(user);

            if (cart == null) {
                throw new Exception("Cart not found");
            }

            OrderLine orderLine = orderLineService.findByIdAndOrder(id_orderLine, cart);

            if (orderLine == null) {
                throw new OrderLineNotFoundException("OrderLine not found");
            }

            if (amount == null || amount <= 0) {
                amount = 1;
            }

            orderLine.setAmount(amount);

            orderLineService.save(orderLine);

            orderService.save(cart);

            OrderDTO orderDTO = dtoConverter.convertOrderToDTO(cart);

            return ResponseEntity.ok(orderDTO);
        } catch (ProductNotFoundException | OrderLineNotFoundException e) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.NOT_FOUND, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
        }

        catch (Exception e) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        }
    }

    @PostMapping("/checkout")
    public ResponseEntity<?> checkout(@AuthenticationPrincipal UserBase user, CheckoutDTO checkoutDTO) {
        try {
            if (user == null) {
                ErrorDetails errorDetails = new ErrorDetails(HttpStatus.UNAUTHORIZED, "Not authorized");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorDetails);
            }

            Order cart = orderService.getCart(user);

            if (cart == null) {
                throw new Exception("Cart not found");
            }

            if (cart.getOrderLines() == null || cart.getOrderLines().isEmpty()) {
                ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST, "Cart is empty");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
            }

            // if (checkoutDTO.get) {
                
            // }
            

            cart.setStatus(OrderStatus.PENDING);

            orderService.save(cart);

            OrderDTO orderDTO = dtoConverter.convertOrderToDTO(cart);

            return ResponseEntity.ok(orderDTO);
        } catch (Exception e) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        }
    }

}
