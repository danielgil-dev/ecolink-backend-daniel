package com.ecolink.spring.loaders;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.ecolink.spring.entity.Startup;
import com.ecolink.spring.service.OdsService;
import com.ecolink.spring.service.StartupService;

@Component
@Order(2)
public class StartupDataLoader implements CommandLineRunner {

    @Autowired
    private StartupService service;

    @Autowired
    private OdsService odsService;

    @Override
    public void run(String... args) throws Exception {
        List<Startup> startups = Arrays.asList(
                new Startup("VhAT", Arrays.asList(
                        odsService.findByName("Industria, innovación e infraestructura"),
                        odsService.findByName("Producción y consumo responsables"),
                        odsService.findByName("Alianzas para lograr los objetivos")), 
                        "contact@vhat.com", 
                        "Plataforma de automatización para la industria que optimiza la producción mediante IA y procesos sostenibles."),
                
                new Startup("GamingBuddy", Arrays.asList(
                        odsService.findByName("Educación de calidad"),
                        odsService.findByName("Reducción de las desigualdades"),
                        odsService.findByName("Trabajo decente y crecimiento económico")), 
                        "info@gamingbuddy.com", 
                        "Aplicación educativa que utiliza videojuegos para fomentar el aprendizaje inclusivo y accesible para todos."),
                
                new Startup("Nørs", Arrays.asList(
                        odsService.findByName("Salud y bienestar"),
                        odsService.findByName("Igualdad de género"),
                        odsService.findByName("Reducción de las desigualdades")), 
                        "hello@nors.com", 
                        "Startup enfocada en el desarrollo de tecnología wearable para el monitoreo de la salud con enfoque en equidad de género."),
                
                new Startup("AndLight", Arrays.asList(
                        odsService.findByName("Producción y consumo responsables"),
                        odsService.findByName("Industria, innovación e infraestructura"),
                        odsService.findByName("Acción por el clima")), 
                        "contact@andlight.com", 
                        "Empresa que desarrolla soluciones de iluminación sostenible con tecnología de energía renovable y materiales reciclables."),
                
                new Startup("Influencer Marketing Hub", Arrays.asList(
                        odsService.findByName("Trabajo decente y crecimiento económico"),
                        odsService.findByName("Reducción de las desigualdades"),
                        odsService.findByName("Alianzas para lograr los objetivos")), 
                        "info@imhub.com", 
                        "Plataforma que conecta marcas con influencers éticos para campañas de marketing responsables y sostenibles."),
                
                new Startup("Too Good To Go", Arrays.asList(
                        odsService.findByName("Hambre cero"),
                        odsService.findByName("Producción y consumo responsables"),
                        odsService.findByName("Acción por el clima")), 
                        "contact@toogoodtogo.com", 
                        "Aplicación que combate el desperdicio de alimentos permitiendo a los usuarios comprar excedentes de comida a precios reducidos."),
                
                new Startup("Doublepoint", Arrays.asList(
                        odsService.findByName("Industria, innovación e infraestructura"),
                        odsService.findByName("Producción y consumo responsables"),
                        odsService.findByName("Trabajo decente y crecimiento económico")), 
                        "info@doublepoint.com", 
                        "Empresa de tecnología que desarrolla sensores avanzados para optimizar procesos industriales con menor impacto ambiental.")
            );
            

        startups.forEach(startup -> {
            if (!service.existsByName(startup.getName())) {
                service.save(startup);
            }
        });
    }

}
