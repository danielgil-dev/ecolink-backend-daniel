package com.ecolink.spring.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;

@Configuration
public class PayPalConfig {
    @Value("${paypal.client.id}")
    private String clientId;

    @Value("${paypal.client.secret}")
    private String clientSecret;

	private String mode = "sandbox";

    @Bean
    public APIContext apiContext() throws PayPalRESTException {
        try {
            Map<String, String> sdkConfig = new HashMap<>();
            sdkConfig.put("mode", mode);

            // Crear la credencial OAuthTokenCredential
            OAuthTokenCredential authTokenCredential = new OAuthTokenCredential(clientId, clientSecret, sdkConfig);

            // Inicializar APIContext con clientId, clientSecret y modo
            APIContext apiContext = new APIContext(authTokenCredential.getClientID(),
                    authTokenCredential.getClientSecret(),
                    mode);
            apiContext.setConfigurationMap(sdkConfig);

            return apiContext;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;}
    }
}
