package com.ecolink.spring.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ecolink.spring.dto.OdsResponse;
import com.ecolink.spring.entity.Ods;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class DeepSeekService {

    @Value("${deepseek.api.key}")
    private String apiKey;

    private static final String API_URL = "https://api.deepseek.com/v1/chat/completions";

    @Autowired
    private  OdsService odsService;


public ResponseEntity<?> getOdsByDescription(String descripcion) {
    try {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.set("Content-Type", "application/json");

        // Construcción del JSON
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("model", "deepseek-chat");

        List<Map<String, String>> messages = new ArrayList<>();
        Map<String, String> userMessage = new HashMap<>();
        userMessage.put("role", "user");
        userMessage.put("content", "You are an SDG classifier. IMPORTANT: RESPOND ONLY with the exact format below, without ANY additional text, explanation or analysis:\r\n" + 
                        "SDG [number]: [Full name of the SDG]\r\n\r\n" +
                        "If the description relates to multiple SDGs, list them one per line using EXACTLY the same format for each. DO NOT include any other text. Description to analyze: " + descripcion);
                  
        messages.add(userMessage);
        requestMap.put("messages", messages);

        // Convertimos el Map a JSON con Jackson
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(requestMap);

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<Map> response = restTemplate.exchange(API_URL, HttpMethod.POST, entity, Map.class);
        
        // Procesamos la respuesta para extraer solo lo importante
        Map<String, Object> responseBody = response.getBody();
        
        if (responseBody != null && responseBody.containsKey("choices")) {
            List<Map<String, Object>> choices = (List<Map<String, Object>>) responseBody.get("choices");
            System.out.println("Respuesta de deppseek" + choices );
            
            if (!choices.isEmpty()) {
                Map<String, Object> firstChoice = choices.get(0);
                Map<String, Object> message = (Map<String, Object>) firstChoice.get("message");
                String content = (String) message.get("content");
                
                // Parseamos el contenido para obtener múltiples ODS si existen
                List<OdsResponse> odsResponses = parseMultipleOdsResponse(content);
                return ResponseEntity.ok().body(odsResponses);
            }
        }
        
        // Si no hay respuesta válida, devolvemos una lista con un único elemento de error
        List<OdsResponse> errorList = new ArrayList<>();
        errorList.add(new OdsResponse(0, "No se pudo clasificar", null));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorList);
    } catch (Exception e) {
        e.printStackTrace();
        // En caso de error, también devolvemos una lista con un único elemento de error
        List<OdsResponse> errorList = new ArrayList<>();
        errorList.add(new OdsResponse(0, "Error: " + e.getMessage(), null));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorList);
    }
}

/**
 * Parsea la respuesta de texto de DeepSeek para extraer múltiples ODS si existen
 * y asigna las URLs de las imágenes correspondientes
 */
private List<OdsResponse> parseMultipleOdsResponse(String content) {
    List<OdsResponse> odsList = new ArrayList<>();
    try {
        // Patrón esperado: "SDG X: Nombre del ODS" (ahora en inglés)
        String pattern = "SDG (\\d+)\\s*:\\s*([^\\n\\r]+?)\\s*\\**\\s*$"; 
        Pattern r = Pattern.compile(pattern,Pattern.MULTILINE);
        Matcher m = r.matcher(content);
        
        // Si no se encuentra ninguna coincidencia, intentaremos devolver toda la respuesta como un solo ODS
        boolean foundMatch = false;

        while (m.find()) {
            foundMatch = true;
            int numero = Integer.parseInt(m.group(1));
            String nombre = m.group(2).trim();
            
            // Generar la URL de la imagen correspondiente al número de ODS
            String imagenUrl = generarUrlImagenOds(numero);
            System.out.println("Imagen de la ods" + imagenUrl);
            
            odsList.add(new OdsResponse(numero, nombre, imagenUrl));
        }
       
           
       
        
        
        // Si no encontramos ningún ODS con el patrón, intentamos devolver todo el contenido como respuesta
        if (!foundMatch) {
            odsList.add(new OdsResponse(0, content, null));
        }
    } catch (Exception e) {
        e.printStackTrace();
        // En caso de error, devolvemos un ODS genérico de error
        odsList.add(new OdsResponse(0, "Error al procesar la respuesta: " + e.getMessage(), null));
    }
    
    return odsList;
}

/**
 * Genera la URL de la imagen correspondiente al número de ODS
 */
private String generarUrlImagenOds(int numeroOds) {
   
    Ods ods = odsService.findById(Long.valueOf(numeroOds));
    if (ods != null) {
        return ods.getImage();
    }
    
    // Return a default or placeholder value if no ODS is found
    return "default-image-url";
}

}
