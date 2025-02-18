package com.ecolink.spring.loaders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ecolink.spring.entity.Comment;
import com.ecolink.spring.entity.Ods;
import com.ecolink.spring.entity.Post;
import com.ecolink.spring.entity.Startup;
import com.ecolink.spring.service.CommentService;
import com.ecolink.spring.service.OdsService;
import com.ecolink.spring.service.PostService;
import com.ecolink.spring.service.StartupService;

@Component
@Order(5)
public class PostDataLoader implements CommandLineRunner {

        @Autowired
        private PostService service;

        @Autowired
        private StartupService startupService;

        @Autowired
        private OdsService odsService;

        @Autowired
        private CommentService commentService;

        @Transactional
        @Override
        public void run(String... args) throws Exception {
                Startup flavorScale = startupService.findByName("FlavorScale");
                Startup climaider = startupService.findByName("Climaider");
                Startup seasony = startupService.findByName("Seasony");
                Startup tribe = startupService.findByName("Tribe");
                Startup peltarion = startupService.findByName("Peltarion");
                Startup donkeyRepublic = startupService.findByName("Donkey Republic");
                Startup pickYourPour = startupService.findByName("Pick Your Pour AS");
                Startup aliceAi = startupService.findByName("Alice AI");
                Startup leiaHealth = startupService.findByName("LEIA Health");
                Startup rightHub = startupService.findByName("RightHub");
                Startup memmora = startupService.findByName("Memmora");
                Startup soilSense = startupService.findByName("SoilSense");
                Startup monta = startupService.findByName("Monta");
                Startup doublepoint = startupService.findByName("Doublepoint");
                Startup strise = startupService.findByName("Strise");
                Startup station = startupService.findByName("Station");
                Startup silvi = startupService.findByName("Silvi");
                Startup nutrishAi = startupService.findByName("Nutrish.ai");
                Startup kodiakHub = startupService.findByName("KodiakHub");
                Startup ecoTree = startupService.findByName("EcoTree");
                Startup goMore = startupService.findByName("GoMore");
                Startup goodWings = startupService.findByName("GoodWings");
                Startup glintSolar = startupService.findByName("GlintSolar");
                Startup spritju = startupService.findByName("Spritju");
                Startup deepBlu = startupService.findByName("DeepBlu");
                Startup vove = startupService.findByName("Vove");
                Startup perPlant = startupService.findByName("PerPlant");
                Startup dripdrop = startupService.findByName("Dripdrop");
                Startup legitify = startupService.findByName("Legitify");
                Startup whistleSystem = startupService.findByName("WhistleSystem");
                Startup beCause = startupService.findByName("BeCause");
                Startup chronosHub = startupService.findByName("ChronosHub");

                // Sustainable Development Goals (SDGs)
                Ods zeroHunger = odsService.findByName("Zero Hunger");
                Ods goodHealthWellBeing = odsService.findByName("Good Health and Well-being");
                Ods qualityEducation = odsService.findByName("Quality Education");
                Ods cleanWaterSanitation = odsService.findByName("Clean Water and Sanitation");
                Ods affordableCleanEnergy = odsService.findByName("Affordable and Clean Energy");
                Ods industryInnovation = odsService.findByName("Industry, Innovation, and Infrastructure");
                Ods reducedInequalities = odsService.findByName("Reduced Inequalities");
                Ods sustainableCities = odsService.findByName("Sustainable Cities and Communities");
                Ods responsibleConsumption = odsService.findByName("Responsible Consumption and Production");
                Ods climateAction = odsService.findByName("Climate Action");
                Ods lifeBelowWater = odsService.findByName("Life Below Water");
                Ods lifeOnLand = odsService.findByName("Life on Land");
                Ods partnershipsForTheGoals = odsService.findByName("Partnerships for the Goals");
                Ods genderEquality = odsService.findByName("Gender Equality");
                Ods decentWorkAndEconomicGrowth = odsService.findByName("Decent Work and Economic Growth");
                Ods peaceJusticeAndStrongInstitutions = odsService.findByName("Peace, Justice, and Strong Institutions");

                List<Post> posts = Arrays.asList(
                                new Post(flavorScale, "Revolutionizing Food Discovery",
                                                "Connecting food enthusiasts with restaurants.",
                                                "FlavorScale is transforming the way people discover and connect with restaurants, making the process seamless and enjoyable.",
                                                LocalDate.now()),
                                new Post(climaider, "Empowering SMEs for Climate Action",
                                                "Helping businesses measure and reduce their carbon footprint.",
                                                "Climaider provides tools for small and medium-sized enterprises to measure, report, and take action on their ESG indicators.",
                                                LocalDate.now().minusDays(5)),
                                new Post(seasony, "Making Vertical Farming Profitable",
                                                "Smart automation solutions for vertical farms.",
                                                "Seasony offers low-cost automation solutions to make vertical farming scalable and efficient, promoting sustainable food practices.",
                                                LocalDate.now().minusDays(10)),
                                new Post(tribe, "Simplifying Insurance",
                                                "Peer-to-peer insurance for a fairer world.",
                                                "Tribe aims to simplify the insurance process by offering clear and fair coverage without unnecessary complexity.",
                                                LocalDate.now().minusWeeks(1)),
                                new Post(peltarion, "AI for a Better Future",
                                                "Leveraging AI for health, wealth, and sustainability.",
                                                "Peltarion's platform allows users to build AI models to solve problems in health, wealth, and sustainability.",
                                                LocalDate.now().minusMonths(1)),
                                new Post(donkeyRepublic, "Leading Bike Sharing",
                                                "Promoting sustainable urban mobility.",
                                                "Donkey Republic provides shared bike services to promote sustainable mobility in cities across Europe.",
                                                LocalDate.now()),
                                new Post(pickYourPour, "Personalized Tasting Experiences",
                                                "Digital menus for food and drink pairings.",
                                                "Pick Your Pour offers a unique digital menu that personalizes food and drink pairings based on individual preferences.",
                                                LocalDate.now().minusDays(3)),
                                new Post(aliceAi, "Democratizing Learning",
                                                "Personalized learning experiences for students.",
                                                "Alice AI provides an ultimate learning platform that offers personalized and engaging learning experiences for students.",
                                                LocalDate.now().minusDays(8)),
                                new Post(leiaHealth, "Supporting Parents with AI",
                                                "Digitizing the parental journey.",
                                                "LEIA Health offers AI-powered support to parents, helping them navigate the challenges of parenthood.",
                                                LocalDate.now().minusWeeks(2)),
                                new Post(rightHub, "Simplifying IP Management",
                                                "All-in-one platform for intellectual property.",
                                                "RightHub provides a comprehensive platform to help creators and professionals manage their intellectual property efficiently.",
                                                LocalDate.now().minusDays(12)),
                                new Post(memmora, "Caring for Graves with Technology",
                                                "Combining technology with gardening.",
                                                "Memmora offers a platform for maintaining graves and sharing memories, regardless of distance.",
                                                LocalDate.now().minusDays(6)),
                                new Post(soilSense, "Tackling Water Scarcity",
                                                "Agri-tech solutions for sustainable farming.",
                                                "SoilSense provides technology to optimize water use in agriculture, addressing the global challenge of water scarcity.",
                                                LocalDate.now().minusDays(15)),
                                new Post(monta, "Powering EV Charging",
                                                "Integrated software for electric vehicle charging.",
                                                "Monta offers a platform to manage electric vehicle charging stations, promoting the adoption of EV technology.",
                                                LocalDate.now().minusDays(7)),
                                new Post(doublepoint, "Gesture Recognition Technology",
                                                "Innovative software for smartwatches.",
                                                "Doublepoint develops gesture recognition software for various devices, enhancing user interaction.",
                                                LocalDate.now().minusDays(20)),
                                new Post(strise, "Fighting Financial Crime",
                                                "AML automation for financial institutions.",
                                                "Strise provides an AML automation cloud to help financial institutions combat financial crime effectively.",
                                                LocalDate.now().minusWeeks(3)),
                                new Post(station, "Empowering Students",
                                                "Creating inclusive student communities.",
                                                "Station Fonden supports students in higher education by fostering meaningful and inclusive communities.",
                                                LocalDate.now().minusDays(18)),
                                new Post(silvi, "AI for Research",
                                                "Tools for systematic literature reviews.",
                                                "Silvi offers AI-powered tools to assist researchers in conducting systematic literature reviews and meta-analyses.",
                                                LocalDate.now().minusWeeks(4)),
                                new Post(nutrishAi, "Personalized Nutrition",
                                                "AI-driven diet recommendations.",
                                                "Nutrish.ai provides personalized nutrition guidance through AI, helping users achieve their dietary goals.",
                                                LocalDate.now().minusDays(9)),
                                new Post(kodiakHub, "Sustainable Supplier Relationships",
                                                "SRM platform for procurement teams.",
                                                "Kodiak Hub offers a platform to help procurement teams build sustainable relationships with suppliers.",
                                                LocalDate.now().minusDays(14)),
                                new Post(ecoTree, "Owning Trees for Sustainability",
                                                "Making sustainability accessible.",
                                                "EcoTree allows individuals and businesses to own trees and contribute to reforestation efforts.",
                                                LocalDate.now().minusDays(11)),
                                new Post(goMore, "Sharing Cars for a Greener Future",
                                                "Platform for ridesharing and car rentals.",
                                                "GoMore promotes car sharing and rentals, reducing the environmental impact of transportation.",
                                                LocalDate.now().minusWeeks(5)),
                                new Post(goodWings, "Climate-Friendly Travel",
                                                "Reducing emissions through sustainable travel.",
                                                "GoodWings offers a travel management platform focused on reducing emissions and promoting sustainable travel.",
                                                LocalDate.now().minusDays(22)),
                                new Post(glintSolar, "Accelerating Solar Energy",
                                                "Screening and analysis software for solar projects.",
                                                "Glint Solar provides tools to identify optimal sites for large-scale solar projects.",
                                                LocalDate.now().minusDays(16)),
                                new Post(spritju, "Revolutionizing Energy Tracking",
                                                "Marketplace for energy certificates.",
                                                "Spritju offers a B2B marketplace for energy certificates, providing real-time tracking of energy usage.",
                                                LocalDate.now().minusDays(19)),
                                new Post(deepBlu, "Connecting Divers",
                                                "Community for sharing dive logs.",
                                                "Deepblu offers a platform for divers to share their dive logs and connect with other enthusiasts.",
                                                LocalDate.now().minusWeeks(6)),
                                new Post(vove, "Sustainable Household Essentials",
                                                "Eco-friendly products for everyday use.",
                                                "Vove provides eco-friendly household products, making sustainability convenient and accessible.",
                                                LocalDate.now().minusDays(21)),
                                new Post(perPlant, "AI for Sustainable Farming",
                                                "Sensor technology for precision agriculture.",
                                                "PerPlant offers AI-compatible sensors to optimize farming practices and reduce waste.",
                                                LocalDate.now().minusDays(13)),
                                new Post(dripdrop, "Eco-Friendly Umbrella Rentals",
                                                "Sustainable solutions for hotels.",
                                                "Dripdrop provides recycled umbrellas for hotels, promoting sustainability and guest satisfaction.",
                                                LocalDate.now().minusDays(17)),
                                new Post(legitify, "Remote Online Notarization",
                                                "AI-powered platform for legal documents.",
                                                "Legitify offers a secure platform for remote online notarization, streamlining the legal process.",
                                                LocalDate.now().minusDays(23)),
                                new Post(whistleSystem, "Compliance Solutions",
                                                "Tools for whistleblower compliance.",
                                                "WhistleSystem provides solutions to help businesses meet EU regulations for whistleblower compliance.",
                                                LocalDate.now().minusDays(12)),
                                new Post(beCause, "Sustainability Management",
                                                "AI-powered platform for businesses.",
                                                "BeCause offers a platform to help businesses make sustainable choices and manage their sustainability data.",
                                                LocalDate.now().minusDays(18)),
                                new Post(chronosHub, "Simplifying Academic Publishing",
                                                "Tools for compliance and reporting.",
                                                "ChronosHub provides tools to help researchers comply with funding policies and streamline the publishing process.",
                                                LocalDate.now().minusDays(15)));

                posts.forEach(post -> {
                        switch (post.getStartup().getName()) {
                                case "FlavorScale":
                                        post.addOds(zeroHunger);
                                        post.addOds(industryInnovation);
                                        break;
                                case "Climaider":
                                        post.addOds(climateAction);
                                        post.addOds(responsibleConsumption);
                                        break;
                                case "Seasony":
                                        post.addOds(zeroHunger);
                                        post.addOds(sustainableCities);
                                        break;
                                case "Tribe":
                                        post.addOds(reducedInequalities);
                                        break;
                                case "Peltarion":
                                        post.addOds(goodHealthWellBeing);
                                        post.addOds(affordableCleanEnergy);
                                        break;
                                case "Donkey Republic":
                                        post.addOds(sustainableCities);
                                        post.addOds(partnershipsForTheGoals);
                                        break;
                                case "Pick Your Pour AS":
                                        post.addOds(qualityEducation);
                                        post.addOds(genderEquality);
                                        break;
                                case "Alice AI":
                                        post.addOds(qualityEducation);
                                        post.addOds(decentWorkAndEconomicGrowth);
                                        break;
                                case "LEIA Health":
                                        post.addOds(genderEquality);
                                        post.addOds(goodHealthWellBeing);
                                        break;
                                case "RightHub":
                                        post.addOds(genderEquality);
                                        post.addOds(decentWorkAndEconomicGrowth);
                                        break;
                                case "Memmora":
                                        post.addOds(cleanWaterSanitation);
                                        post.addOds(sustainableCities);
                                        break;
                                case "SoilSense":
                                        post.addOds(cleanWaterSanitation);
                                        post.addOds(responsibleConsumption);
                                        break;
                                case "Monta":
                                        post.addOds(affordableCleanEnergy);
                                        post.addOds(sustainableCities);
                                        break;
                                case "Doublepoint":
                                        post.addOds(decentWorkAndEconomicGrowth);
                                        post.addOds(industryInnovation);
                                        break;
                                case "Strise":
                                        post.addOds(decentWorkAndEconomicGrowth);
                                        post.addOds(peaceJusticeAndStrongInstitutions);
                                        break;
                                case "Station":
                                        post.addOds(qualityEducation);
                                        post.addOds(industryInnovation);
                                        break;
                                case "Silvi":
                                        post.addOds(goodHealthWellBeing);
                                        post.addOds(qualityEducation);
                                        break;
                                case "Nutrish.ai":
                                        post.addOds(goodHealthWellBeing);
                                        post.addOds(responsibleConsumption);
                                        break;
                                case "KodiakHub":
                                        post.addOds(goodHealthWellBeing);
                                        post.addOds(genderEquality);
                                        break;
                                case "EcoTree":
                                        post.addOds(responsibleConsumption);
                                        post.addOds(climateAction);
                                        break;
                                case "GoMore":
                                        post.addOds(sustainableCities);
                                        post.addOds(responsibleConsumption);
                                        break;
                                case "GoodWings":
                                        post.addOds(climateAction);
                                        post.addOds(partnershipsForTheGoals);
                                        break;
                                case "GlintSolar":
                                        post.addOds(affordableCleanEnergy);
                                        post.addOds(climateAction);
                                        break;
                                case "Spritju":
                                        post.addOds(affordableCleanEnergy);
                                        post.addOds(sustainableCities);
                                        break;
                                case "DeepBlu":
                                        post.addOds(goodHealthWellBeing);
                                        post.addOds(lifeBelowWater);
                                        break;
                                case "Vove":
                                        post.addOds(responsibleConsumption);
                                        post.addOds(climateAction);
                                        break;
                                case "PerPlant":
                                        post.addOds(responsibleConsumption);
                                        post.addOds(climateAction);
                                        break;
                                case "Dripdrop":
                                        post.addOds(responsibleConsumption);
                                        post.addOds(lifeBelowWater);
                                        break;
                                case "Legitify":
                                        post.addOds(peaceJusticeAndStrongInstitutions);
                                        break;
                                case "WhistleSystem":
                                        post.addOds(decentWorkAndEconomicGrowth);
                                        post.addOds(reducedInequalities);
                                        break;
                                case "BeCause":
                                        post.addOds(responsibleConsumption);
                                        post.addOds(partnershipsForTheGoals);
                                        break;
                                case "ChronosHub":
                                        post.addOds(industryInnovation);
                                        post.addOds(peaceJusticeAndStrongInstitutions);
                                        break;
                                default:
                                        System.out.println("No se encontró el post de la startup: " + post.getStartup().getName());
                                        break;
                        }

                        if (service.findByTitle(post.getTitle()) == null) {
                                service.save(post);

                                Comment newComment = new Comment();
                                newComment.setComment("I love " + post.getTitle());
                                newComment.setPost(post);
                                newComment.setUser(flavorScale); // Example user

                                flavorScale.addComment(newComment);

                                commentService.save(newComment);
                                startupService.save(flavorScale);
                                service.save(post);
                        }
                });
        }
}