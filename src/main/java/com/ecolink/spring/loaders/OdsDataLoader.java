package com.ecolink.spring.loaders;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.ecolink.spring.entity.Ods;
import com.ecolink.spring.service.OdsService;

@Component
@Order(1)
public class OdsDataLoader implements CommandLineRunner {
    @Autowired
    private OdsService service;

    @Override
    public void run(String... args) throws Exception {
        List<Ods> odsList = Arrays.asList(
                new Ods("Fin de la pobreza"),
                new Ods("Hambre cero"),
                new Ods("Salud y bienestar"),
                new Ods("Educación de calidad"),
                new Ods("Igualdad de género"),
                new Ods("Agua limpia y saneamiento"),
                new Ods("Energía asequible y no contaminante"),
                new Ods("Trabajo decente y crecimiento económico"),
                new Ods("Industria, innovación e infraestructura"),
                new Ods("Reducción de las desigualdades"),
                new Ods("Ciudades y comunidades sostenibles"),
                new Ods("Producción y consumo responsables"),
                new Ods("Acción por el clima"),
                new Ods("Vida submarina"),
                new Ods("Vida de ecosistemas terrestres"),
                new Ods("Paz, justicia e instituciones sólidas"),
                new Ods("Alianzas para lograr los objetivos"));

        odsList.forEach(ods -> {
            if (!service.existByName(ods.getName())) {  
                service.save(ods);
            }
        });
    }
}
