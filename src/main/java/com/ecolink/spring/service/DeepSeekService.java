package com.ecolink.spring.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.ecolink.spring.dto.OdsResponse;
import com.ecolink.spring.entity.Ods;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeepSeekService {
    private static final Logger logger = LoggerFactory.getLogger(DeepSeekService.class);
    private static final String API_URL = "https://api.deepseek.com/v1/chat/completions";
    private static final String SDG_PATTERN = "SDG (\\d+)\\s*:\\s*([^\\n\\r]+?)\\s*\\**\\s*$";
    private static final Pattern SDG_REGEX = Pattern.compile(SDG_PATTERN, Pattern.MULTILINE);
    
    @Autowired
    private OdsService odsService;
    
    @Value("${deepseek.api.key}")
    private String apiKey;

    /**
     * Analiza una descripción para determinar los ODS relacionados.
     * 
     * @param descripcion Texto a analizar
     * @return ResponseEntity con lista de OdsResponse o mensaje de error
     */
    public ResponseEntity<?> getOdsByDescription(String descripcion) {
        try {
            // Utilizamos un único RestTemplate y ObjectMapper por llamada
            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper objectMapper = new ObjectMapper();
            
            // Construimos el prompt y realizamos la llamada
            String prompt = buildPrompt(descripcion);
            String apiResponse = callDeepSeekAPI(prompt, restTemplate, objectMapper);
            
            if (apiResponse != null && !apiResponse.isEmpty()) {
                List<OdsResponse> odsResponses = parseOdsResponses(apiResponse);
                return ResponseEntity.ok(odsResponses);
            } else {
                logger.warn("Respuesta vacía de la API de DeepSeek");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Collections.singletonList(new OdsResponse(0, "No se pudo clasificar", null)));
            }
        } catch (Exception e) {
            logger.error("Error al procesar la descripción: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonList(new OdsResponse(0, "Error: " + e.getMessage(), null)));
        }
    }

    /**
     * Construye el prompt para enviar a la API
     */
    private String buildPrompt(String descripcion) {
        return "You are an SDG classifier. IMPORTANT: RESPOND ONLY with the exact format below, without ANY additional " +
               "text, explanation or analysis:\r\n" + 
               "SDG [number]: [Full name of the SDG]\r\n\r\n" +
               "If the description relates to multiple SDGs, list them one per line using EXACTLY the same format for each. " +
               "DO NOT include any other text. Description to analyze: " + descripcion;
    }

    /**
     * Realiza la llamada a la API de DeepSeek
     */
    private String callDeepSeekAPI(String prompt, RestTemplate restTemplate, ObjectMapper objectMapper) 
            throws JsonProcessingException, RestClientException {
        // Creamos los headers con autenticación
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.set("Content-Type", "application/json");
        
        // Construcción del JSON para la API
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("model", "deepseek-chat");

        List<Map<String, String>> messages = new ArrayList<>();
        Map<String, String> userMessage = new HashMap<>();
        userMessage.put("role", "user");
        userMessage.put("content", prompt);
                  
        messages.add(userMessage);
        requestMap.put("messages", messages);
        
        // Convertimos el Map a JSON con Jackson
        String requestBody = objectMapper.writeValueAsString(requestMap);
        
        // Realizamos la llamada HTTP
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<Map> response = restTemplate.exchange(API_URL, HttpMethod.POST, entity, Map.class);
        
        // Extraemos el contenido de la respuesta
        return extractContentFromResponse(response);
    }

    /**
     * Extrae el contenido de la respuesta de la API
     */
    private String extractContentFromResponse(ResponseEntity<Map> response) {
        Map<String, Object> responseBody = response.getBody();
        
        if (responseBody != null && responseBody.containsKey("choices")) {
            List<Map<String, Object>> choices = (List<Map<String, Object>>) responseBody.get("choices");
            logger.debug("Respuesta recibida de DeepSeek: {}", choices);
            
            if (!choices.isEmpty()) {
                Map<String, Object> firstChoice = choices.get(0);
                if (firstChoice.containsKey("message")) {
                    Map<String, Object> message = (Map<String, Object>) firstChoice.get("message");
                    if (message.containsKey("content")) {
                        return (String) message.get("content");
                    }
                }
            }
        }
        
        logger.warn("La respuesta no contiene el formato esperado");
        return null;
    }

    /**
     * Parsea el contenido para extraer múltiples ODS
     */
    private List<OdsResponse> parseOdsResponses(String content) {
        List<OdsResponse> odsList = new ArrayList<>();
        try {
            Matcher matcher = SDG_REGEX.matcher(content);
            boolean foundMatch = false;

            while (matcher.find()) {
                foundMatch = true;
                int numero = Integer.parseInt(matcher.group(1));
                String nombre = matcher.group(2).trim();
                String imagenUrl = getOdsImageUrl(numero);
                
                logger.debug("ODS encontrado: {} - {} (imagen: {})", numero, nombre, imagenUrl);
                odsList.add(new OdsResponse(numero, nombre, imagenUrl));
            }
            
            if (!foundMatch) {
                logger.warn("No se encontraron ODS en el formato esperado. Contenido: {}", content);
                odsList.add(new OdsResponse(0, content, null));
            }
        } catch (Exception e) {
            logger.error("Error al procesar la respuesta: {}", e.getMessage(), e);
            odsList.add(new OdsResponse(0, "Error al procesar la respuesta: " + e.getMessage(), null));
        }
        
        return odsList;
    }

    /**
     * Obtiene la URL de la imagen del ODS por su número
     */
    private String getOdsImageUrl(int numeroOds) {
        Ods ods = odsService.findById(Long.valueOf(numeroOds));
        return ods != null ? ods.getImage() : "default-image-url";
    }
}
