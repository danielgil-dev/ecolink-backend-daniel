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
@Order(4)
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
                Startup beCause = startupService.findByName("BeCause");
                Startup bitligRenewableFuels = startupService.findByName("Bitlig Renewable Fuels");
                Startup Mavibioscience = startupService.findByName("Mavi Bioscience");
                Startup doecos = startupService.findByName("Doecos");
                Startup viscan = startupService.findByName("VISCAN");
                Startup nxtCatchas = startupService.findByName("NXT Catch AS");
                Startup squareRoot = startupService.findByName("SquareRoot");
                Startup alteredPowerAs = startupService.findByName("Altered Power AS");
                Startup besenGroupAs = startupService.findByName("Besen Group AS");
                Startup seacirc = startupService.findByName("Seacirc");
                Startup manolin = startupService.findByName("Manolin");
                Startup algaeproAs = startupService.findByName("Algaepro AS");
                Startup elifeAs = startupService.findByName("Elife AS");
                Startup nordicElectrofuel = startupService.findByName("Nordic Electrofuel");
                Startup evoltec = startupService.findByName("Evoltec");
                Startup ecoCharge = startupService.findByName("EcoCharge");
                Startup waterWise = startupService.findByName("WaterWise");
                Startup greenTransport = startupService.findByName("GreenTransport");
                Startup ecoPackaging = startupService.findByName("EcoPackaging");
                Startup sustainableFashion = startupService.findByName("SustainableFashion");

                List<Product> products = Arrays.asList(
                                // VhAT
                                new Product(vhat, "VhAT Smart Glasses", "Smart glasses for augmented reality",
                                                new BigDecimal("499.99"), LocalDate.now()),
                                new Product(vhat, "VhAT AR Kit", "Development kit for augmented reality",
                                                new BigDecimal("299.99"), LocalDate.now()),

                                // GamingBuddy
                                new Product(gamingBuddy, "GamingBuddy Pro", "Premium subscription for gamers",
                                                new BigDecimal("9.99"), LocalDate.now()),
                                new Product(gamingBuddy, "GamingBuddy Controller", "Ergonomic gaming controller",
                                                new BigDecimal("59.99"), LocalDate.now()),

                                // Nørs
                                new Product(nors, "Nørs Health Tracker", "Smart wristband for health monitoring",
                                                new BigDecimal("129.99"), LocalDate.now()),
                                new Product(nors, "Nørs Meditation App", "Guided meditation application",
                                                new BigDecimal("4.99"), LocalDate.now()),

                                // AndLight
                                new Product(andLight, "AndLight Solar Panel", "High-efficiency solar panel",
                                                new BigDecimal("199.99"), LocalDate.now()),
                                new Product(andLight, "AndLight LED Bulb", "Eco-friendly LED bulb",
                                                new BigDecimal("14.99"), LocalDate.now()),

                                // Influencer Marketing Hub
                                new Product(imHub, "IMHub Analytics", "Analytics tool for influencers",
                                                new BigDecimal("49.99"), LocalDate.now()),
                                new Product(imHub, "IMHub Campaign Manager", "Advertising campaign manager",
                                                new BigDecimal("99.99"), LocalDate.now()),

                                // Too Good To Go
                                new Product(tooGoodToGo, "Too Good To Go Box", "Rescued food box",
                                                new BigDecimal("9.99"), LocalDate.now()),
                                new Product(tooGoodToGo, "Too Good To Go App", "App to fight food waste",
                                                new BigDecimal("0.00"), LocalDate.now()),

                                // Doublepoint
                                new Product(doublepoint, "Doublepoint Smartwatch", "Smartwatch with integrated GPS",
                                                new BigDecimal("199.99"), LocalDate.now()),
                                new Product(doublepoint, "Doublepoint Fitness Tracker",
                                                "Device for tracking physical activity",
                                                new BigDecimal("79.99"), LocalDate.now()),

                                // BeCause
                                new Product(beCause, "BeCause Sustainability Dashboard",
                                                "Platform for monitoring corporate sustainability",
                                                new BigDecimal("99.99"), LocalDate.now()),
                                new Product(beCause, "BeCause Carbon Tracker", "Tool to track and reduce CO2 emissions",
                                                new BigDecimal("149.99"), LocalDate.now()),

                                // Bitlig Renewable Fuels
                                new Product(bitligRenewableFuels, "Bitlig BioFuel",
                                                "Sustainable fuel for industrial and domestic use",
                                                new BigDecimal("3.49"), LocalDate.now()),
                                new Product(bitligRenewableFuels, "Bitlig Fuel Efficiency Kit",
                                                "Kit to optimize fuel consumption and reduce waste",
                                                new BigDecimal("89.99"), LocalDate.now()),

                                // Mavi Bioscience
                                new Product(Mavibioscience, "Mavi Methane Reducer",
                                                "Feed additive to cut livestock methane emissions by 80%",
                                                new BigDecimal("59.99"), LocalDate.now()),
                                new Product(Mavibioscience, "Mavi Soil Booster",
                                                "Organic soil enrichment for sustainable farming",
                                                new BigDecimal("39.99"), LocalDate.now()),

                                // Doecos
                                new Product(doecos, "Doecos Smart Heater",
                                                "Smart heating system with renewable energy integration",
                                                new BigDecimal("299.99"), LocalDate.now()),
                                new Product(doecos, "Doecos Insulation Kit",
                                                "Home insulation kit for energy efficiency",
                                                new BigDecimal("89.99"), LocalDate.now()),

                                // VISCAN
                                new Product(viscan, "VISCAN 3D Mapping System",
                                                "Advanced 3D scanning for buildings and urban planning",
                                                new BigDecimal("1499.99"), LocalDate.now()),
                                new Product(viscan, "VISCAN Carbon Footprint Report",
                                                "AI-powered analysis of buildings' energy efficiency",
                                                new BigDecimal("199.99"), LocalDate.now()),

                                // NXT Catch AS
                                new Product(nxtCatchas, "NXT Sustainable Fishing Net",
                                                "Biodegradable fishing net to reduce ocean waste",
                                                new BigDecimal("299.99"), LocalDate.now()),
                                new Product(nxtCatchas, "NXT Smart Buoy",
                                                "IoT buoy for tracking sustainable fishing practices",
                                                new BigDecimal("399.99"), LocalDate.now()),

                                // SquareRoot
                                new Product(squareRoot, "SquareRoot Green Roof Kit",
                                                "Modular green roof system for urban areas",
                                                new BigDecimal("799.99"), LocalDate.now()),
                                new Product(squareRoot, "SquareRoot Smart Irrigation",
                                                "Automated irrigation system for sustainable cities",
                                                new BigDecimal("249.99"), LocalDate.now()),

                                // Altered Power AS
                                new Product(alteredPowerAs, "Altered Power Portable Solar Charger",
                                                "Portable solar charger for outdoor use",
                                                new BigDecimal("129.99"), LocalDate.now()),
                                new Product(alteredPowerAs, "Altered Power Foldable Solar Panel",
                                                "Compact solar panel for off-grid adventures",
                                                new BigDecimal("199.99"), LocalDate.now()),

                                // Besen Group AS
                                new Product(besenGroupAs, "Besen EV Charger", "Home electric vehicle charger",
                                                new BigDecimal("349.99"), LocalDate.now()),
                                new Product(besenGroupAs, "Besen Public Charging Station",
                                                "Fast-charging station for urban areas",
                                                new BigDecimal("7999.99"), LocalDate.now()),

                                // Seacirc
                                new Product(seacirc, "Seacirc Circular Economy Software",
                                                "AI-based software for waste optimization",
                                                new BigDecimal("399.99"), LocalDate.now()),
                                new Product(seacirc, "Seacirc Sustainable Supply Chain",
                                                "Tool for monitoring sustainable supply chains",
                                                new BigDecimal("299.99"), LocalDate.now()),

                                // Manolin
                                new Product(manolin, "Manolin AquaHealth", "Aquaculture health monitoring platform",
                                                new BigDecimal("599.99"), LocalDate.now()),
                                new Product(manolin, "Manolin Smart Sensors", "Water quality sensors for fish farms",
                                                new BigDecimal("149.99"), LocalDate.now()),

                                // Algaepro AS
                                new Product(algaeproAs, "Algaepro BioPlastic", "Biodegradable plastic made from algae",
                                                new BigDecimal("49.99"), LocalDate.now()),
                                new Product(algaeproAs, "Algaepro Protein Powder",
                                                "Sustainable algae-based protein supplement",
                                                new BigDecimal("29.99"), LocalDate.now()),

                                // Elife AS
                                new Product(elifeAs, "Elife Electric Bike", "Eco-friendly electric bicycle",
                                                new BigDecimal("1199.99"), LocalDate.now()),
                                new Product(elifeAs, "Elife Smart Battery", "Long-lasting battery pack for e-bikes",
                                                new BigDecimal("399.99"), LocalDate.now()),

                                // Nordic Electrofuel
                                new Product(nordicElectrofuel, "Nordic SynFuel",
                                                "Synthetic fuel made from renewable energy",
                                                new BigDecimal("5.99"), LocalDate.now()),
                                new Product(nordicElectrofuel, "Nordic Carbon Capture System",
                                                "Carbon capture and recycling unit",
                                                new BigDecimal("2499.99"), LocalDate.now()),

                                // Evoltec
                                new Product(evoltec, "Evoltec Smart Grid Optimizer",
                                                "AI-powered system to enhance energy efficiency in industrial facilities.",
                                                new BigDecimal("249.99"), LocalDate.now()),

                                // EcoCharge
                                new Product(ecoCharge, "EcoCharge Solar Charging Station",
                                                "Solar-powered charging dock for EVs",
                                                new BigDecimal("4999.99"), LocalDate.now()),

                                // WaterWise
                                new Product(waterWise, "WaterWise Leak Detector",
                                                "Smart sensor that detects water leaks in real-time to prevent waste and reduce costs.",
                                                new BigDecimal("119.99"), LocalDate.now()),

                                // GreenTransport
                                new Product(greenTransport, "GreenTransport E-Scooter",
                                                "Affordable electric scooter for urban mobility",
                                                new BigDecimal("699.99"), LocalDate.now()),

                                // EcoPackaging
                                new Product(ecoPackaging, "EcoPackaging Compostable Bag",
                                                "100% biodegradable packaging",
                                                new BigDecimal("2.99"), LocalDate.now()),

                                // SustainableFashion
                                new Product(sustainableFashion, "SustainableFashion Recycled Sneakers",
                                                "Shoes made from recycled plastics",
                                                new BigDecimal("89.99"), LocalDate.now())

                );

                products.forEach(product -> {
                        if (!service.existsByNameAndStartup(product.getName(), product.getStartup())) {
                                service.save(product);
                        }
                });

        }

}
