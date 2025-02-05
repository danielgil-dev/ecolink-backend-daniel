package com.ecolink.spring.loaders;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.ecolink.spring.entity.Startup;
import com.ecolink.spring.service.OdsService;
import com.ecolink.spring.service.StartupService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Order(2)
public class StartupDataLoader implements CommandLineRunner {

        @Autowired
        private StartupService service;

        @Autowired
        private OdsService odsService;

        private final PasswordEncoder passwordEncoder;

        @Override
        public void run(String... args) throws Exception {
                String defaultPassword = "password";
                List<Startup> startups = Arrays.asList(
                                new Startup("VhAT", Arrays.asList(
                                                odsService.findByName("Industry, Innovation, and Infrastructure"),
                                                odsService.findByName("Responsible Consumption and Production"),
                                                odsService.findByName("Partnerships for the Goals")),
                                                "contact@vhat.com",
                                                "Automation platform for the industry that optimizes production through AI and sustainable processes."),

                                new Startup("GamingBuddy", Arrays.asList(
                                                odsService.findByName("Quality Education"),
                                                odsService.findByName("Reduced Inequalities"),
                                                odsService.findByName("Decent Work and Economic Growth")),
                                                "info@gamingbuddy.com",
                                                "Educational app that uses video games to promote inclusive and accessible learning for everyone."),

                                new Startup("Nørs", Arrays.asList(
                                                odsService.findByName("Good Health and Well-being"),
                                                odsService.findByName("Gender Equality"),
                                                odsService.findByName("Reduced Inequalities")),
                                                "hello@nors.com",
                                                "Startup focused on developing wearable technology for health monitoring with a gender equity approach."),

                                new Startup("AndLight", Arrays.asList(
                                                odsService.findByName("Responsible Consumption and Production"),
                                                odsService.findByName("Industry, Innovation, and Infrastructure"),
                                                odsService.findByName("Climate Action")),
                                                "contact@andlight.com",
                                                "Company developing sustainable lighting solutions with renewable energy technology and recyclable materials."),

                                new Startup("Influencer Marketing Hub", Arrays.asList(
                                                odsService.findByName("Decent Work and Economic Growth"),
                                                odsService.findByName("Reduced Inequalities"),
                                                odsService.findByName("Partnerships for the Goals")),
                                                "info@imhub.com",
                                                "Platform connecting brands with ethical influencers for responsible and sustainable marketing campaigns."),

                                new Startup("Too Good To Go", Arrays.asList(
                                                odsService.findByName("Zero Hunger"),
                                                odsService.findByName("Responsible Consumption and Production"),
                                                odsService.findByName("Climate Action")),
                                                "contact@toogoodtogo.com",
                                                "App combating food waste by allowing users to buy surplus food at reduced prices."),

                                new Startup("Doublepoint", Arrays.asList(
                                                odsService.findByName("Industry, Innovation, and Infrastructure"),
                                                odsService.findByName("Responsible Consumption and Production"),
                                                odsService.findByName("Decent Work and Economic Growth")),
                                                "info@doublepoint.com",
                                                "Technology company developing advanced sensors to optimize industrial processes with lower environmental impact."),

                                new Startup("BeCause", Arrays.asList(
                                                odsService.findByName("Industry, Innovation, and Infrastructure"),
                                                odsService.findByName("Responsible Consumption and Production")),
                                                "contact@because.com",
                                                "Sustainability management platform for companies, facilitating responsible decision-making."),

                                new Startup("Bitlig Renewable Fuels", Arrays.asList(
                                                odsService.findByName("Climate Action"),
                                                odsService.findByName("Affordable and Clean Energy")),
                                                "info@bitligrenewables.com",
                                                "Developing technology to reduce sustainable fuel production costs."),

                                new Startup("Mavi Bioscience", Arrays.asList(
                                                odsService.findByName("Climate Action"),
                                                odsService.findByName("Responsible Consumption and Production")),
                                                "contact@mavibioscience.com",
                                                "Innovative solution to reduce livestock methane emissions by 80%."),

                                new Startup("Doecos", Arrays.asList(
                                                odsService.findByName("Affordable and Clean Energy"),
                                                odsService.findByName("Sustainable Cities and Communities")),
                                                "info@doecos.com",
                                                "Helping customers adopt more sustainable heating sources at home."),

                                new Startup("VISCAN", Arrays.asList(
                                                odsService.findByName("Industry, Innovation, and Infrastructure"),
                                                odsService.findByName("Sustainable Cities and Communities")),
                                                "contact@viscan.com",
                                                "3D capture of buildings to improve efficiency and reduce carbon footprint."),

                                new Startup("NXT Catch AS", Arrays.asList(
                                                odsService.findByName("Life Below Water"),
                                                odsService.findByName("Responsible Consumption and Production")),
                                                "info@nxtcatch.com",
                                                "Innovations for sustainable fishing and waste reduction in fishing equipment."),

                                new Startup("SquareRoot", Arrays.asList(
                                                odsService.findByName("Sustainable Cities and Communities"),
                                                odsService.findByName("Climate Action")),
                                                "contact@squareroot.com",
                                                "Platform to promote green infrastructure in cities."),

                                new Startup("Altered Power AS", Arrays.asList(
                                                odsService.findByName("Affordable and Clean Energy"),
                                                odsService.findByName("Climate Action")),
                                                "info@alteredpower.com",
                                                "Portable renewable energy for outdoor enthusiasts."),

                                new Startup("Besen Group AS", Arrays.asList(
                                                odsService.findByName("Affordable and Clean Energy"),
                                                odsService.findByName("Sustainable Cities and Communities")),
                                                "contact@besengroup.com",
                                                "Distribution of electric chargers for sustainable mobility."),

                                new Startup("Seacirc", Arrays.asList(
                                                odsService.findByName("Responsible Consumption and Production"),
                                                odsService.findByName("Industry, Innovation, and Infrastructure")),
                                                "info@seacirc.com",
                                                "Software to improve business sustainability through data usage."),

                                new Startup("Manolin", Arrays.asList(
                                                odsService.findByName("Life Below Water"),
                                                odsService.findByName("Industry, Innovation, and Infrastructure")),
                                                "contact@manolin.com",
                                                "Digital health management for sustainable aquaculture."),

                                new Startup("Algaepro AS", Arrays.asList(
                                                odsService.findByName("Responsible Consumption and Production"),
                                                odsService.findByName("Climate Action")),
                                                "info@algaepro.com",
                                                "Production of biomass from microalgae for bioplastics and food."),

                                new Startup("Elife AS", Arrays.asList(
                                                odsService.findByName("Sustainable Cities and Communities"),
                                                odsService.findByName("Affordable and Clean Energy")),
                                                "contact@elife.com",
                                                "Manufacturing and distribution of electric bicycles."),

                                new Startup("Nordic Electrofuel", Arrays.asList(
                                                odsService.findByName("Climate Action"),
                                                odsService.findByName("Affordable and Clean Energy")),
                                                "info@nordicelectrofuel.com",
                                                "Transforming electric energy into renewable fuels."),

                                new Startup("Evoltec", Arrays.asList(
                                                odsService.findByName("Industry, Innovation, and Infrastructure"),
                                                odsService.findByName("Climate Action")),
                                                "contact@evoltec.com",
                                                "Technology services and products for the sustainable energy industry."),

                                new Startup("EcoCharge", Arrays.asList(
                                                odsService.findByName("Affordable and Clean Energy"),
                                                odsService.findByName("Sustainable Cities and Communities")),
                                                "info@ecocharge.com",
                                                "Solar charging stations for electric vehicles."),

                                new Startup("WaterWise", Arrays.asList(
                                                odsService.findByName("Clean Water and Sanitation"),
                                                odsService.findByName("Climate Action")),
                                                "contact@waterwise.com",
                                                "Monitoring technology for efficient water use."),

                                new Startup("GreenTransport", Arrays.asList(
                                                odsService.findByName("Sustainable Cities and Communities"),
                                                odsService.findByName("Climate Action")),
                                                "info@greentransport.com",
                                                "Electric and shared public transportation solutions."),

                                new Startup("EcoPackaging", Arrays.asList(
                                                odsService.findByName("Responsible Consumption and Production"),
                                                odsService.findByName("Climate Action")),
                                                "contact@ecopackaging.com",
                                                "Development of biodegradable and compostable packaging."),

                                new Startup("SustainableFashion", Arrays.asList(
                                                odsService.findByName("Responsible Consumption and Production"),
                                                odsService.findByName("Decent Work and Economic Growth")),
                                                "info@sustainablefashion.com",
                                                "Sustainable fashion with recycled materials and fair trade."));

                startups.forEach(startup -> {
                        if (!service.existsByName(startup.getName())) {
                                startup.setPassword(passwordEncoder.encode(defaultPassword));
                                service.save(startup);
                        }
                });
        }

}
