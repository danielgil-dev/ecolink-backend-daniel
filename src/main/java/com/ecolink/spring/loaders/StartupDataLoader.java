package com.ecolink.spring.loaders;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.ecolink.spring.entity.Startup;
import com.ecolink.spring.entity.Status;
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
                                new Startup("FlavorScale", Arrays.asList(
                                                odsService.findByName("No Poverty"),
                                                odsService.findByName("Zero Hunger"),
                                                odsService.findByName("Industry, Innovation, and Infrastructure")),
                                                "contact@flavorscale.com",
                                                "FlavorScale is a foodtech startup aiming to revolutionize how people connect with restaurants. The current landscape is outdated and fragmented, leaving food enthusiasts and establishments disconnected."),

                                new Startup("Climaider", Arrays.asList(
                                                odsService.findByName("No Poverty"),
                                                odsService.findByName("Responsible Consumption and Production"),
                                                odsService.findByName("Climate Action")),
                                                "info@climaider.com",
                                                "Climaider is a fast-growing startup that enables SMEs to measure and report their ESG indicators and take real action on climate change."),

                                new Startup("Seasony", Arrays.asList(
                                                odsService.findByName("Zero Hunger"),
                                                odsService.findByName("Sustainable Cities and Communities"),
                                                odsService.findByName("Responsible Consumption and Production")),
                                                "hello@seasony.com",
                                                "Seasony makes vertical farms profitable, scalable, and efficient. Since its founding in 2018, sustainability has been the cornerstone of its mission. It provides smart, low-cost automation solutions."),

                                new Startup("Tribe", Arrays.asList(
                                                odsService.findByName("Zero Hunger")),
                                                "contact@tribe.com",
                                                "Tribe is a peer-to-peer insurance company offering life and non-life insurance. Their goal is to simplify and make the world of insurance fairer by eliminating complexity and unnecessary costs."),

                                new Startup("Peltarion", Arrays.asList(
                                                odsService.findByName("Good Health and Well-being"),
                                                odsService.findByName("Affordable and Clean Energy"),
                                                odsService.findByName("Climate Action")),
                                                "info@peltarion.com",
                                                "Peltarion leverages artificial intelligence to improve health, wealth, and sustainability. The Peltarion platform allows users to build their own AI models through a single software platform."),

                                new Startup("Donkey Republic", Arrays.asList(
                                                odsService.findByName("Good Health and Well-being"),
                                                odsService.findByName("Sustainable Cities and Communities"),
                                                odsService.findByName("Partnerships for the Goals")),
                                                "contact@donkeyrepublic.com",
                                                "Donkey Republic is a leading provider of shared bike services in Europe. The company has more than 20,000 bikes and ebikes in over 70 cities. It promotes sustainable mobility."),

                                new Startup("Pick Your Pour AS", Arrays.asList(
                                                odsService.findByName("Quality Education"),
                                                odsService.findByName("Gender Equality"),
                                                odsService.findByName("Decent Work and Economic Growth")),
                                                "info@pickyourpour.com",
                                                "'Pick Your Pour' is a unique digital menu that opens the world of taste in food and drinks for everyone. We customize a menu for each guest based on what they are looking for through a series of detailed questions."),

                                new Startup("Alice AI", Arrays.asList(
                                                odsService.findByName("Quality Education"),
                                                odsService.findByName("Decent Work and Economic Growth"),
                                                odsService.findByName("Industry, Innovation, and Infrastructure")),
                                                "contact@aliceai.com",
                                                "Alice AI aims to democratize access to optimal learning by being the ultimate learning platform for students, offering personalized and engaging learning experiences tailored to their unique needs and contexts at affordable prices."),

                                new Startup("LEIA Health", Arrays.asList(
                                                odsService.findByName("Gender Equality"),
                                                odsService.findByName("Good Health and Well-being")),
                                                "info@leiahealth.com",
                                                "LEIA Health is a Stockholm-based startup with a mission to support the next billion parents by digitizing the parental journey and providing AI-powered support when parents need it most."),

                                new Startup("RightHub", Arrays.asList(
                                                odsService.findByName("Gender Equality"),
                                                odsService.findByName("Decent Work and Economic Growth"),
                                                odsService.findByName("Industry, Innovation, and Infrastructure")),
                                                "contact@righthub.com",
                                                "RightHub is developing an all-in-one platform to help creators, IP professionals, and service providers simplify intellectual property management. Our mission is to protect brilliant ideas and innovations from theft and misuse with our efficient and collaborative solution."),

                                new Startup("Memmora", Arrays.asList(
                                                odsService.findByName("Clean Water and Sanitation"),
                                                odsService.findByName("Sustainable Cities and Communities"),
                                                odsService.findByName("Climate Action")),
                                                "info@memmora.com",
                                                "Memmora is a grave care concept that combines technology with gardening through an easy-to-use platform. Our gardeners maintain graves year-round and send photos to members, allowing for maintenance and sharing of memories regardless of distance."),

                                new Startup("SoilSense", Arrays.asList(
                                                odsService.findByName("Clean Water and Sanitation"),
                                                odsService.findByName("Responsible Consumption and Production"),
                                                odsService.findByName("Climate Action")),
                                                "contact@soilsense.com",
                                                "SoilSense is an agri-tech company working to solve one of the greatest climate challenges of this century: water scarcity. We are driven by the ambition to make a real difference in the world, focusing on agriculture to solve the global challenge of water scarcity."),

                                new Startup("Monta", Arrays.asList(
                                                odsService.findByName("Affordable and Clean Energy"),
                                                odsService.findByName("Sustainable Cities and Communities"),
                                                odsService.findByName("Climate Action")),
                                                "info@monta.com",
                                                "Monta is the operating platform powering the electric vehicle charging ecosystem, serving drivers, companies, cities, and the electricity grid with an integrated software solution. We believe that accelerating and democratizing the adoption of electric vehicle technology is key to building a better future."),

                                new Startup("Doublepoint", Arrays.asList(
                                                odsService.findByName("Decent Work and Economic Growth"),
                                                odsService.findByName("Industry, Innovation, and Infrastructure"),
                                                odsService.findByName("Responsible Consumption and Production")),
                                                "contact@doublepoint.com",
                                                "Doublepoint creates gesture recognition software for smartwatches. Our technologies are used to control AR/VR, IoT, wearables, automotive, AI assistants, TVs, and much more. Our smartwatch algorithms detect subtle hand gestures and need to be accurate, responsive, low power, and generalizable across populations."),

                                new Startup("Strise", Arrays.asList(
                                                odsService.findByName("Decent Work and Economic Growth"),
                                                odsService.findByName("Peace, Justice and Strong Institutions")),
                                                "info@strise.com",
                                                "Strise is a rapidly expanding tech company based in Oslo. We have created a revolutionary AML (anti-money laundering) automation cloud to help top-tier banks, fintechs, and other financial institutions fight financial crime."),

                                new Startup("Station", Arrays.asList(
                                                odsService.findByName("Quality Education"),
                                                odsService.findByName("Industry, Innovation, and Infrastructure"),
                                                odsService.findByName("Sustainable Cities and Communities")),
                                                "contact@station.com",
                                                "Station Fonden is a non-profit volunteer foundation dedicated to youth, well-being, and empowering students. Our mission is to create meaningful and inclusive communities that allow students from higher education institutions across the country to transform their engagement, curiosity, and ideas into positive change for themselves and the world."),

                                new Startup("Silvi", Arrays.asList(
                                                odsService.findByName("Good Health and Well-being"),
                                                odsService.findByName("Quality Education"),
                                                odsService.findByName("Reduced Inequalities")),
                                                "contact@silvi.com",
                                                "Silvi is an AI-powered tool for systematic literature reviews and meta-analyses. It connects to databases like PubMed and ClinicalTrials.gov to keep information up to date. Researchers stay in control while AI speeds up data collection and analysis."),

                                new Startup("Nutrish.ai", Arrays.asList(
                                                odsService.findByName("Good Health and Well-being"),
                                                odsService.findByName("Reduced Inequalities"),
                                                odsService.findByName("Responsible Consumption and Production")),
                                                "contact@nutrish.ai",
                                                "Nutrish.ai provides personalized nutrition guidance through AI and expert insights, all via WhatsApp. It solves the problem of confusing advice and inaccessible diet plans by offering custom meal plans and real-time, science-backed support."),

                                new Startup("KodiakHub", Arrays.asList(
                                                odsService.findByName("Good Health and Well-being"),
                                                odsService.findByName("Gender Equality"),
                                                odsService.findByName("Sustainable Cities and Communities")),
                                                "contact@kodiakhub.com",
                                                "Kodiak Hub is a cloud-based Supplier Relationship Management (SRM) platform that helps procurement teams source smarter and build sustainable supplier relationships. By combining cutting-edge technology, user-friendly design, and data-driven insights, it enables businesses to unlock and maximize value across their supply chain."),

                                new Startup("EcoTree", Arrays.asList(
                                                odsService.findByName("Responsible Consumption and Production"),
                                                odsService.findByName("Climate Action"),
                                                odsService.findByName("Life on Land")),
                                                "contact@ecotree.com",
                                                "EcoTree is a green-tech company making sustainability accessible by allowing individuals and businesses to own trees and forests. By planting and maintaining forests, we help offset carbon footprints while ensuring biodiversity and responsible forestry."),

                                new Startup("GoMore", Arrays.asList(
                                                odsService.findByName("Sustainable Cities and Communities"),
                                                odsService.findByName("Responsible Consumption and Production"),
                                                odsService.findByName("Climate Action")),
                                                "contact@gomore.com",
                                                "GoMore's mission is to help people share cars. We do this by providing an online platform for ridesharing, peer-to-peer car rental, and car leasing with a lease-and-rent-out model. We are present in Denmark, Spain, Sweden, Finland, Switzerland, Austria, and Estonia, with over 3 million members showing the way forward."),

                                new Startup("GoodWings", Arrays.asList(
                                                odsService.findByName("Climate Action"),
                                                odsService.findByName("Partnerships for the Goals")),
                                                "contact@goodwings.com",
                                                "Goodwings is a climate-focused travel management platform that helps businesses reduce emissions while booking and managing travel. Using AI-driven strategies, behavior-based reduction plans, and Sustainable Aviation Fuel (SAF) purchases, we enable companies to actively lower their carbon footprint."),

                                new Startup("GlintSolar", Arrays.asList(
                                                odsService.findByName("Affordable and Clean Energy"),
                                                odsService.findByName("Climate Action")),
                                                "contact@glintsolar.com",
                                                "Glint Solar accelerates solar energy adoption with cutting-edge screening and analysis software, helping developers identify optimal sites for large-scale solar projects. Starting with floating solar, one of the fastest-growing energy sectors, we have expanded into land-based solar to drive renewable energy growth."),

                                new Startup("Spritju", Arrays.asList(
                                                odsService.findByName("Affordable and Clean Energy"),
                                                odsService.findByName("Sustainable Cities and Communities"),
                                                odsService.findByName("Climate Action")),
                                                "contact@spritju.com",
                                                "Spritju is revolutionizing energy tracking with a B2B marketplace for energy certificates, offering 8,760 times more granularity than traditional systems. While outdated certificates cost companies €24.4 billion annually with little transparency, Spritju cuts costs by 20% and provides real-time, precise tracking of energy usage."),

                                new Startup("DeepBlu", Arrays.asList(
                                                odsService.findByName("Good Health and Well-being"),
                                                odsService.findByName("Life Below Water")),
                                                "contact@deepblu.com",
                                                "Deepblu is a community designed for divers to share dive logs and their passion for the ocean. The Deepblu diving community transforms the way divers connect with one another. Experience exciting dive adventures through the eyes of other divers and bring others onto your journey by sharing your dive logs."),

                                new Startup("Vove", Arrays.asList(
                                                odsService.findByName("Responsible Consumption and Production"),
                                                odsService.findByName("Climate Action"),
                                                odsService.findByName("Life Below Water")),
                                                "contact@vove.com",
                                                "Vove is redefining sustainable household essentials by creating the best eco-friendly products without compromising quality or convenience. Using technology, we simplify the buying process, making it easier to find, replenish, and manage home essentials."),

                                new Startup("PerPlant", Arrays.asList(
                                                odsService.findByName("Responsible Consumption and Production"),
                                                odsService.findByName("Climate Action"),
                                                odsService.findByName("Life on Land")),
                                                "contact@perplant.com",
                                                "PerPlant is an agtech startup dedicated to helping farmers transition to sustainable farming by making AI-driven insights accessible. Our advanced sensor technology provides unmatched precision in monitoring plant health."),

                                new Startup("Dripdrop", Arrays.asList(
                                                odsService.findByName("Responsible Consumption and Production"),
                                                odsService.findByName("Life Below Water"),
                                                odsService.findByName("Life on Land")),
                                                "contact@dripdrop.com",
                                                "Dripdrop is a tech startup helping top hotels provide eco-friendly umbrella rentals while streamlining their guest experience. Our umbrellas are made from recycled plastic, and with every rental, we plant a tree, reinforcing our commitment to sustainability."),

                                new Startup("Legitify", Arrays.asList(
                                                odsService.findByName("Peace, Justice and Strong Institutions")),
                                                "contact@legitify.com",
                                                "Legitify is an AI-powered platform that enables remote online notarization (RON) through secure audio-video authentication, eliminating the need for in-person visits. Designed for Europe and beyond, it leverages cutting-edge technology to stay ahead of evolving legal frameworks."),

                                new Startup("WhistleSystem", Arrays.asList(
                                                odsService.findByName("Decent Work and Economic Growth"),
                                                odsService.findByName("Reduced Inequalities"),
                                                odsService.findByName("Peace, Justice and Strong Institutions")),
                                                "contact@whistlesystem.com",
                                                "WhistleSystem is a fast-growing SaaS company providing whistleblower compliance solutions for European businesses with 50+ employees. With the EU mandate requiring compliance by December 2023, we offer the simplest, most efficient way for companies to meet regulations."),

                                new Startup("BeCause", Arrays.asList(
                                                odsService.findByName("Responsible Consumption and Production"),
                                                odsService.findByName("Partnerships for the Goals")),
                                                "contact@because.com",
                                                "BeCause is a sustainability management hub using AI-powered technology to make sustainable choices seamless for businesses, particularly in travel and tourism. Our platform, because.eco, simplifies and automates sustainability data management."),

                                new Startup("ChronosHub", Arrays.asList(
                                                odsService.findByName("Industry, Innovation, and Infrastructure"),
                                                odsService.findByName("Peace, Justice and Strong Institutions"),
                                                odsService.findByName("Partnerships for the Goals")),
                                                "contact@chronoshub.com",
                                                "ChronosHub simplifies academic publishing by helping authors find the right journals while ensuring compliance with funding policies and Open Access (OA) agreements. Our platform streamlines APC management, reporting, and compliance tracking."));

                startups.forEach(startup -> {
                        if (!service.existsByName(startup.getName())) {
                                startup.setPassword(passwordEncoder.encode(defaultPassword));
                                startup.setVerified(true);
                                startup.setStatus(Status.ACCEPTED);
                                service.save(startup);
                        }
                });
        }

}
