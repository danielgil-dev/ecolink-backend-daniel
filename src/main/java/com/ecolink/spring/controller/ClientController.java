package com.ecolink.spring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecolink.spring.entity.Client;
import com.ecolink.spring.exception.ClientNotFoundException;
import com.ecolink.spring.exception.ErrorDetails;
import com.ecolink.spring.service.ClientService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@RequestMapping("api/client")
@RestController
public class ClientController {
    private final ClientService service;

    @GetMapping("/{id}")
    public ResponseEntity<?> getMethodName(@PathVariable Long id) {
        try {
            Client client = service.findById(id);
            if (client == null) {
                throw new ClientNotFoundException("No existe el cliente con id=" + id);
            }

            return ResponseEntity.ok(client);
        } catch (ClientNotFoundException e) {
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Ocurrió un error interno en el servidor");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        }
    }

}
