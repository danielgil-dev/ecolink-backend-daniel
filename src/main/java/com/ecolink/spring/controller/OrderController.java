package com.ecolink.spring.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecolink.spring.dto.AddProductDTO;
import com.ecolink.spring.dto.CheckoutDTO;
import com.ecolink.spring.dto.DTOConverter;
import com.ecolink.spring.dto.OrderDTO;
import com.ecolink.spring.dto.PayDTO;
import com.ecolink.spring.dto.UpdateLineDTO;
import com.ecolink.spring.entity.Order;
import com.ecolink.spring.entity.OrderLine;
import com.ecolink.spring.entity.OrderStatus;
import com.ecolink.spring.entity.Product;
import com.ecolink.spring.entity.UserBase;
import com.ecolink.spring.exception.ErrorDetails;
import com.ecolink.spring.exception.OrderLineNotFoundException;
import com.ecolink.spring.exception.ProductNotFoundException;
import com.ecolink.spring.response.SuccessDetails;
import com.ecolink.spring.service.EmailServiceImpl;
import com.ecolink.spring.service.OrderLineService;
import com.ecolink.spring.service.OrderService;
import com.ecolink.spring.service.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;
    private final OrderLineService orderLineService;
    private final ProductService productService;
    private final DTOConverter dtoConverter;
    private final EmailServiceImpl emailService;

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
    public ResponseEntity<?> addProductToCart(@AuthenticationPrincipal UserBase user,
            @RequestBody AddProductDTO addProductDTO) {
        try {
            if (user == null) {
                ErrorDetails errorDetails = new ErrorDetails(HttpStatus.UNAUTHORIZED, "Not authorized");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorDetails);
            }

            if (addProductDTO.getId_product() == null || addProductDTO.getAmount() == null) {
                ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST, "Missing fields");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);

            }

            Long id_product = addProductDTO.getId_product();
            Integer amount = addProductDTO.getAmount();

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

    @DeleteMapping("/remove-product/{id_orderLine}")
    public ResponseEntity<?> removeProductFromCart(@AuthenticationPrincipal UserBase user,
            @PathVariable Long id_orderLine) {
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
    public ResponseEntity<?> updateProductInCart(@AuthenticationPrincipal UserBase user,
            @RequestBody UpdateLineDTO updateLineDTO) {
        try {
            if (user == null) {
                ErrorDetails errorDetails = new ErrorDetails(HttpStatus.UNAUTHORIZED, "Not authorized");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorDetails);
            }

            if (updateLineDTO.getId_orderLine() == null || updateLineDTO.getAmount() == null) {
                ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST, "Missing fields");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
            }

            Long id_orderLine = updateLineDTO.getId_orderLine();
            Integer amount = updateLineDTO.getAmount();

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
    public ResponseEntity<?> checkout(@AuthenticationPrincipal UserBase user, @RequestBody CheckoutDTO checkoutDTO) {
        try {
            if (user == null) {
                ErrorDetails errorDetails = new ErrorDetails(HttpStatus.UNAUTHORIZED, "Not authorized");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorDetails);
            }

            if (orderService.existsByUserAndStatus(user, OrderStatus.PENDING)) {
                ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST,
                        "You already have a pending order");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
            }

            Order cart = orderService.getCart(user);

            if (cart == null) {
                throw new Exception("Cart not found");
            }

            if (cart.getOrderLines() == null || cart.getOrderLines().isEmpty()) {
                ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST, "Cart is empty");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
            }

            if (checkoutDTO.getFirstName() == null || checkoutDTO.getFirstName().isEmpty()
                    || checkoutDTO.getLastName() == null || checkoutDTO.getLastName().isEmpty()
                    || checkoutDTO.getShippingPhone() == null || checkoutDTO.getShippingPhone().isEmpty()
                    || checkoutDTO.getShippingAddress() == null || checkoutDTO.getShippingAddress().isEmpty()
                    || checkoutDTO.getShippingCity() == null || checkoutDTO.getShippingCity().isEmpty()
                    || checkoutDTO.getShippingCountry() == null || checkoutDTO.getShippingCountry().isEmpty()
                    || checkoutDTO.getShippingZipCode() == null || checkoutDTO.getShippingZipCode().isEmpty()) {
                ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST, "Missing fields");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
            }

            cart.setFirstName(checkoutDTO.getFirstName());
            cart.setLastName(checkoutDTO.getLastName());
            cart.setShippingPhone(checkoutDTO.getShippingPhone());
            cart.setShippingAddress(checkoutDTO.getShippingAddress());
            cart.setShippingCity(checkoutDTO.getShippingCity());
            cart.setShippingCountry(checkoutDTO.getShippingCountry());
            cart.setShippingZipCode(checkoutDTO.getShippingZipCode());

            cart.setStatus(OrderStatus.PENDING);

            orderService.save(cart);

            SuccessDetails successDetails = new SuccessDetails(HttpStatus.OK, "Order placed successfully");

            return ResponseEntity.ok(successDetails);

        } catch (Exception e) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        }
    }

    @PostMapping("/cancel")
    public ResponseEntity<?> cancelOrder(@AuthenticationPrincipal UserBase user) {
        try {
            if (user == null) {
                ErrorDetails errorDetails = new ErrorDetails(HttpStatus.UNAUTHORIZED, "Not authorized");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorDetails);
            }

            Order order = orderService.findByUserAndStatus(user, OrderStatus.PENDING);

            if (order == null) {
                ErrorDetails errorDetails = new ErrorDetails(HttpStatus.NOT_FOUND, "Order not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
            }

            order.setStatus(OrderStatus.CANCELLED);

            orderService.save(order);

            emailService.sendPaymentCancelled(user.getEmail());

            SuccessDetails successDetails = new SuccessDetails(HttpStatus.OK, "Order cancelled successfully");

            return ResponseEntity.ok(successDetails);

        } catch (Exception e) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        }
    }

    @PostMapping("/pay")
    public ResponseEntity<?> payOrder(@AuthenticationPrincipal UserBase user,
            @Valid @RequestBody PayDTO payDTO,
            BindingResult bindingResult) {
        try {
            if (user == null) {
                ErrorDetails errorDetails = new ErrorDetails(HttpStatus.UNAUTHORIZED, "Not authorized");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorDetails);
            }

            if (bindingResult.hasErrors()) {
                Map<String, String> errors = new HashMap<>();
                for (FieldError error : bindingResult.getFieldErrors()) {
                    errors.put(error.getField(), error.getDefaultMessage());
                }
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
            }

            Order order = orderService.findByUserAndStatus(user, OrderStatus.PENDING);

            if (order == null) {
                ErrorDetails errorDetails = new ErrorDetails(HttpStatus.NOT_FOUND, "Order not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
            }

            emailService.sendPaymentSuccess(user.getEmail());
            order.setStatus(OrderStatus.PROCESSING);

            orderService.save(order);
            SuccessDetails successDetails = new SuccessDetails(HttpStatus.OK, "Payment processed successfully");
            return ResponseEntity.ok(successDetails);

        } catch (Exception e) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        }
    }

    @GetMapping("/amount")
    public ResponseEntity<?> getAmount(@AuthenticationPrincipal UserBase user) {
        try {
            if (user == null) {
                Integer amount = 0;
                Map<String, Integer> response = new HashMap<>();
                response.put("amount", amount);
                response.put("status", HttpStatus.OK.value());
    
                return ResponseEntity.ok(response);
            }

            Order cart = orderService.getCart(user);

            if (cart == null) {
                cart = new Order(user, OrderStatus.CART, LocalDate.now());
                orderService.save(cart);
            }

            Integer amount = 0;
            for (OrderLine orderLine : cart.getOrderLines()) {
                amount += orderLine.getAmount();
            }

            Map<String, Integer> response = new HashMap<>();
            response.put("amount", amount);
            response.put("status", HttpStatus.OK.value());

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        }
    }

}
