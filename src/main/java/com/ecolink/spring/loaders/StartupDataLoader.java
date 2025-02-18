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
                                                "RightHub is building an all-in-one platform to help creators, IP professionals, and service providers simplify intellectual property management, protecting ideas and innovations from theft and misuse."),
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
                                                "SoilSense is an agri-tech company focused on tackling water scarcity, aiming to make a real impact on agriculture and address this global climate challenge."),

                                new Startup("Monta", Arrays.asList(
                                                odsService.findByName("Affordable and Clean Energy"),
                                                odsService.findByName("Sustainable Cities and Communities"),
                                                odsService.findByName("Climate Action")),
                                                "info@monta.com",
                                                "Monta accelerates electric vehicle adoption with a platform that connects drivers, businesses, and cities, promoting a greener future."),

                                new Startup("Doublepoint", Arrays.asList(
                                                odsService.findByName("Decent Work and Economic Growth"),
                                                odsService.findByName("Industry, Innovation, and Infrastructure"),
                                                odsService.findByName("Responsible Consumption and Production")),
                                                "contact@doublepoint.com",
                                                "Doublepoint creates gesture recognition software for smartwatches, enhancing AR/VR, wearables, IoT, and automotive control."),

                                new Startup("Strise", Arrays.asList(
                                                odsService.findByName("Decent Work and Economic Growth"),
                                                odsService.findByName("Peace, Justice and Strong Institutions")),
                                                "info@strise.com",
                                                "Strise offers an AML automation platform to help banks and fintechs combat financial crime efficiently."),

                                new Startup("Station", Arrays.asList(
                                                odsService.findByName("Quality Education"),
                                                odsService.findByName("Industry, Innovation, and Infrastructure"),
                                                odsService.findByName("Sustainable Cities and Communities")),
                                                "contact@station.com",
                                                "Station Fonden is a non-profit that empowers students to create positive change through inclusive communities."),

                                new Startup("Silvi", Arrays.asList(
                                                odsService.findByName("Good Health and Well-being"),
                                                odsService.findByName("Quality Education"),
                                                odsService.findByName("Reduced Inequalities")),
                                                "contact@silvi.com",
                                                "Silvi is an AI-powered tool that helps researchers conduct systematic reviews and meta-analyses faster."),

                                new Startup("Nutrish.ai", Arrays.asList(
                                                odsService.findByName("Good Health and Well-being"),
                                                odsService.findByName("Reduced Inequalities"),
                                                odsService.findByName("Responsible Consumption and Production")),
                                                "contact@nutrish.ai",
                                                "Nutrish.ai delivers personalized nutrition guidance through AI and expert insights via WhatsApp."),

                                new Startup("KodiakHub", Arrays.asList(
                                                odsService.findByName("Good Health and Well-being"),
                                                odsService.findByName("Gender Equality"),
                                                odsService.findByName("Sustainable Cities and Communities")),
                                                "contact@kodiakhub.com",
                                                "Kodiak Hub is a cloud-based platform that helps businesses build sustainable supplier relationships through SRM."),

                                new Startup("EcoTree", Arrays.asList(
                                                odsService.findByName("Responsible Consumption and Production"),
                                                odsService.findByName("Climate Action"),
                                                odsService.findByName("Life on Land")),
                                                "contact@ecotree.com",
                                                "EcoTree allows individuals and businesses to own trees and forests, offsetting carbon footprints while preserving biodiversity."),

                                new Startup("GoMore", Arrays.asList(
                                                odsService.findByName("Sustainable Cities and Communities"),
                                                odsService.findByName("Responsible Consumption and Production"),
                                                odsService.findByName("Climate Action")),
                                                "contact@gomore.com",
                                                "GoMore promotes car-sharing with a platform for ridesharing, car rentals, and leasing across multiple countries."),

                                new Startup("GoodWings", Arrays.asList(
                                                odsService.findByName("Climate Action"),
                                                odsService.findByName("Partnerships for the Goals")),
                                                "contact@goodwings.com",
                                                "Goodwings is a climate-focused travel platform helping businesses reduce emissions through AI-driven strategies."),

                                new Startup("GlintSolar", Arrays.asList(
                                                odsService.findByName("Affordable and Clean Energy"),
                                                odsService.findByName("Climate Action")),
                                                "contact@glintsolar.com",
                                                "Glint Solar accelerates solar energy adoption with software that identifies optimal sites for large-scale solar projects."),

                                new Startup("Spritju", Arrays.asList(
                                                odsService.findByName("Affordable and Clean Energy"),
                                                odsService.findByName("Sustainable Cities and Communities"),
                                                odsService.findByName("Climate Action")),
                                                "contact@spritju.com",
                                                "Spritju provides a B2B marketplace for energy certificates with real-time tracking, cutting costs and improving transparency."),

                                new Startup("DeepBlu", Arrays.asList(
                                                odsService.findByName("Good Health and Well-being"),
                                                odsService.findByName("Life Below Water")),
                                                "contact@deepblu.com",
                                                "Deepblu connects divers to share experiences and dive logs, enhancing the community and ocean exploration."),

                                new Startup("Vove", Arrays.asList(
                                                odsService.findByName("Responsible Consumption and Production"),
                                                odsService.findByName("Climate Action"),
                                                odsService.findByName("Life Below Water")),
                                                "contact@vove.com",
                                                "Vove creates eco-friendly household products, simplifying sustainable living without compromising on convenience."),

                                new Startup("PerPlant", Arrays.asList(
                                                odsService.findByName("Responsible Consumption and Production"),
                                                odsService.findByName("Climate Action"),
                                                odsService.findByName("Life on Land")),
                                                "contact@perplant.com",
                                                "PerPlant helps farmers transition to sustainable farming through AI-driven insights and precision plant health monitoring."),

                                new Startup("Dripdrop", Arrays.asList(
                                                odsService.findByName("Responsible Consumption and Production"),
                                                odsService.findByName("Life Below Water"),
                                                odsService.findByName("Life on Land")),
                                                "contact@dripdrop.com",
                                                "Dripdrop provides eco-friendly umbrella rentals to hotels, with each rental helping to plant a tree for sustainability."),

                                new Startup("Legitify", Arrays.asList(
                                                odsService.findByName("Peace, Justice and Strong Institutions")),
                                                "contact@legitify.com",
                                                "Legitify is an AI-powered platform that facilitates remote notarization through secure audio-video authentication."),

                                new Startup("WhistleSystem", Arrays.asList(
                                                odsService.findByName("Decent Work and Economic Growth"),
                                                odsService.findByName("Reduced Inequalities"),
                                                odsService.findByName("Peace, Justice and Strong Institutions")),
                                                "contact@whistlesystem.com",
                                                "WhistleSystem provides whistleblower compliance solutions for businesses to meet EU regulations efficiently."),

                                new Startup("BeCause", Arrays.asList(
                                                odsService.findByName("Responsible Consumption and Production"),
                                                odsService.findByName("Partnerships for the Goals")),
                                                "contact@because.com",
                                                "BeCause is a sustainability hub that uses AI to simplify data management and promote sustainable practices."),

                                new Startup("ChronosHub", Arrays.asList(
                                                odsService.findByName("Industry, Innovation, and Infrastructure"),
                                                odsService.findByName("Peace, Justice and Strong Institutions"),
                                                odsService.findByName("Partnerships for the Goals")),
                                                "contact@chronoshub.com",
                                                "ChronosHub simplifies academic publishing by helping authors comply with funding policies and Open Access agreements."));

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
