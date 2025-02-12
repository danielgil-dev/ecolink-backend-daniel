package com.ecolink.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class Ecolink1Application {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();
        dotenv.entries().forEach(entry-> {
            System.setProperty(entry.getKey(), entry.getValue());
	        System.out.println("Cargando variable de entorno: " + entry.getKey() + "=" + entry.getValue());
        });
		
		SpringApplication.run(Ecolink1Application.class, args);
	}

}
