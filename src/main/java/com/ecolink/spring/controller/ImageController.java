package com.ecolink.spring.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecolink.spring.exception.ImageGetExcepcion;

import lombok.RequiredArgsConstructor;

@RequestMapping("/api/image")
@RequiredArgsConstructor
@RestController
public class ImageController {

    @Value("${spring.users.upload.dir}")
    private String uploadUserDir;

    @GetMapping
    public ResponseEntity<?> findImage(@RequestParam(required = true) String type,
            @RequestParam(required = true) String name_image) {
        try {

            String full_url = "";
            switch (type) {
                case "user":
                    full_url = uploadUserDir + name_image;
                    break;
                case "ods":
                    // Añadir mas adelante la carpeta de ods
                    break;
            }
            if (full_url.isEmpty()) {
                if (full_url == null || full_url.isEmpty()) {
                    throw new ImageGetExcepcion("Error to get the image");
                }
            }
            Path imagePath = Paths.get(full_url).toAbsolutePath().normalize();
            System.out.println("Ruta absoluta: " + imagePath);
            

            if (!Files.exists(imagePath)) {
                throw new ImageGetExcepcion("The image not exists");
            }

            byte[] imageBytes = Files.readAllBytes(imagePath);
            String mimeType = Files.probeContentType(imagePath);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, mimeType)
                    .body(imageBytes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener la imagen: " + e.getMessage());
        }
    }
}
