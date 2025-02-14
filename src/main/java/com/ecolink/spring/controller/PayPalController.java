package com.ecolink.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecolink.spring.entity.Order;
import com.ecolink.spring.entity.OrderStatus;
import com.ecolink.spring.entity.UserBase;
import com.ecolink.spring.exception.ErrorDetails;
import com.ecolink.spring.response.SuccessDetails;
import com.ecolink.spring.service.OrderService;
import com.ecolink.spring.service.PayPalService;
import com.ecolink.spring.service.UserBaseService;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

@RestController
@RequestMapping("/api/paypal")
public class PayPalController {
    @Autowired
    private PayPalService service;

    @Autowired
    UserBaseService userBaseService;

    @Autowired
    OrderService orderService;

    private static final String SUCCESS_URL = "http://localhost:8080/api/paypal/success";
    private static final String CANCEL_URL = "http://localhost:8080/api/paypal/cancel";

    @PostMapping("/pay")
    public String pay(@AuthenticationPrincipal UserBase userBase) {
        try {

            if (userBase == null) {
                return "redirect:/login";
            }

            Order order = orderService.findByUserAndStatus(userBase, OrderStatus.PENDING);

            if (order == null) {
                return "redirect:/";
            }

            Double total = order.getTotal().doubleValue();

            System.out.println("Total: " + total);

            Payment payment = service.createPayment(total, "EUR", "paypal",
                    "sale", "EcoLink PaypPal", CANCEL_URL, SUCCESS_URL);
            for (var link : payment.getLinks()) {
                if (link.getRel().equals("approval_url")) {
                    return "redirect:" + link.getHref();
                }
            }
            return "redirect:/";
        } catch (Exception e) {
            return "redirect:/";
        }
    }

    @GetMapping("/success")
    public ResponseEntity<?> success(@AuthenticationPrincipal UserBase user,
            @RequestParam("paymentId") String paymentId,
            @RequestParam("PayerID") String payerId) {
        try {

            if (user == null) {
                return ResponseEntity.badRequest().body(new ErrorDetails(HttpStatus.BAD_REQUEST, "User not found"));

            }

            Order order = orderService.findByUserAndStatus(user, OrderStatus.PENDING);
            if (order == null) {
                return ResponseEntity.badRequest().body(new ErrorDetails(HttpStatus.BAD_REQUEST, "Order not found"));
            }

            Payment payment = service.executePayment(paymentId, payerId);
            if (payment.getState().equals("approved")) {
                SuccessDetails details = new SuccessDetails(HttpStatus.OK, "Payment success");
                order.setStatus(OrderStatus.PROCESSING);
                orderService.save(order);
                return ResponseEntity.ok(details);
            }
        } catch (PayPalRESTException e) {
            ErrorDetails details = new ErrorDetails(HttpStatus.BAD_REQUEST, "Payment error");
            return ResponseEntity.badRequest().body(details);
        }
        ErrorDetails details = new ErrorDetails(HttpStatus.BAD_REQUEST, "Payment error");
        return ResponseEntity.badRequest().body(details);
    }

    @GetMapping("/cancel")

    public ResponseEntity<?> cancel() {
        SuccessDetails details = new SuccessDetails(HttpStatus.OK, "Payment cancelled");
        return ResponseEntity.ok(details);
    }

}
