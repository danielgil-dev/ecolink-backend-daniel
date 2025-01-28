package com.ecolink.spring.loaders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.ecolink.spring.entity.Product;
import com.ecolink.spring.entity.Startup;
import com.ecolink.spring.service.ProductService;
import com.ecolink.spring.service.StartupService;

@Component
@Order(3)
public class ProductoDataLoader implements CommandLineRunner {
    @Autowired
    private ProductService service;

    @Autowired
    private StartupService startupService;

    @Override
    public void run(String... args) throws Exception {
        Startup vhat = startupService.findByName("VhAT");
        Startup gamingBuddy = startupService.findByName("GamingBuddy");
        Startup nors = startupService.findByName("Nørs");
        Startup andLight = startupService.findByName("AndLight");
        Startup imHub = startupService.findByName("Influencer Marketing Hub");
        Startup tooGoodToGo = startupService.findByName("Too Good To Go");
        Startup doublepoint = startupService.findByName("Doublepoint");

        List<Product> products = Arrays.asList(
                new Product(vhat, "VhAT Smart Glasses", "Gafas inteligentes para realidad aumentada",
                        new BigDecimal("499.99"), LocalDate.now()),
                new Product(vhat, "VhAT AR Kit", "Kit de desarrollo para realidad aumentada", new BigDecimal("299.99"),
                        LocalDate.now()),

                new Product(gamingBuddy, "GamingBuddy Pro", "Suscripción premium para gamers", new BigDecimal("9.99"),
                        LocalDate.now()),
                new Product(gamingBuddy, "GamingBuddy Controller", "Controlador ergonómico para juegos",
                        new BigDecimal("59.99"), LocalDate.now()),

                new Product(nors, "Nørs Health Tracker", "Pulsera inteligente para monitoreo de salud",
                        new BigDecimal("129.99"), LocalDate.now()),
                new Product(nors, "Nørs Meditation App", "Aplicación de meditación guiada", new BigDecimal("4.99"),
                        LocalDate.now()),

                new Product(andLight, "AndLight Solar Panel", "Panel solar de alta eficiencia",
                        new BigDecimal("199.99"), LocalDate.now()),
                new Product(andLight, "AndLight LED Bulb", "Bombilla LED ecológica", new BigDecimal("14.99"),
                        LocalDate.now()),

                new Product(imHub, "IMHub Analytics", "Herramienta de análisis para influencers",
                        new BigDecimal("49.99"), LocalDate.now()),
                new Product(imHub, "IMHub Campaign Manager", "Gestor de campañas publicitarias",
                        new BigDecimal("99.99"), LocalDate.now()),

                new Product(tooGoodToGo, "Too Good To Go Box", "Caja de alimentos rescatados", new BigDecimal("9.99"),
                        LocalDate.now()),
                new Product(tooGoodToGo, "Too Good To Go App", "Aplicación para combatir el desperdicio de alimentos",
                        new BigDecimal("0.00"), LocalDate.now()),

                new Product(doublepoint, "Doublepoint Smartwatch", "Reloj inteligente con GPS integrado",
                        new BigDecimal("199.99"), LocalDate.now()),
                new Product(doublepoint, "Doublepoint Fitness Tracker",
                        "Dispositivo para seguimiento de actividad física", new BigDecimal("79.99"), LocalDate.now()));

        products.forEach(product -> {
            if (!service.existsByNameAndStartup(product.getName(), product.getStartup())) {
                service.save(product);
            }
        });

    }

}
