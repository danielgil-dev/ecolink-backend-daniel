package com.ecolink.spring.loaders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ecolink.spring.entity.Client;
import com.ecolink.spring.entity.Comment;
import com.ecolink.spring.entity.Ods;
import com.ecolink.spring.entity.Post;
import com.ecolink.spring.entity.Startup;
import com.ecolink.spring.service.ClientService;
import com.ecolink.spring.service.CommentService;
import com.ecolink.spring.service.OdsService;
import com.ecolink.spring.service.PostService;
import com.ecolink.spring.service.StartupService;
import com.ecolink.spring.service.UserBaseService;

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


                List<Post> posts = Arrays.asList(
                                new Post(
                                                vhat,
                                                "VhAT: Revolutionizing industrial automation",
                                                "Optimizing production with AI.",
                                                "Our AI-driven platform optimizes production processes, reducing waste and enhancing efficiency.",
                                                LocalDate.now()),

                                new Post(
                                                gamingBuddy,
                                                "GamingBuddy: Learning through play",
                                                "Making education fun for all ages.",
                                                "We offer educational games that promote learning in a fun and engaging way for all ages.",
                                                LocalDate.now().minusDays(5)),

                                new Post(
                                                nors,
                                                "Nørs: Enhancing personal health",
                                                "Wearable technology for better health.",
                                                "Our wearable technology tracks vital signs, encouraging healthier lifestyles.",
                                                LocalDate.now().minusDays(10)),

                                new Post(
                                                andLight,
                                                "AndLight: Affordable solar solutions",
                                                "Providing clean energy through solar power.",
                                                "We develop solar-powered lighting systems to provide clean energy access.",
                                                LocalDate.now().minusWeeks(1)),

                                new Post(
                                                imHub,
                                                "IM Hub: Supporting fair influencer marketing",
                                                "Connecting ethical influencers with brands.",
                                                "We connect brands with ethical influencers to promote fair trade and ethical marketing practices.",
                                                LocalDate.now().minusMonths(1)),

                                new Post(
                                                tooGoodToGo,
                                                "Too Good To Go: Fighting hunger with rescued food",
                                                "Reducing food waste, feeding communities.",
                                                "We bridge the gap between surplus food and those in need, reducing food waste.",
                                                LocalDate.now()),

                                new Post(
                                                doublepoint,
                                                "Doublepoint: Towards a zero-carbon future",
                                                "Helping businesses track and cut emissions.",
                                                "Our technology helps businesses track and reduce their carbon footprint effectively.",
                                                LocalDate.now().minusDays(3)),

                                new Post(
                                                beCause,
                                                "BeCause: Building sustainable business networks",
                                                "Collaborating for a greener future.",
                                                "We enable companies to collaborate towards achieving global sustainability goals.",
                                                LocalDate.now().minusDays(8)),

                                new Post(
                                                bitligRenewableFuels,
                                                "Bitlig Renewable Fuels: Clean energy for all",
                                                "Affordable renewable fuel solutions.",
                                                "We produce cost-effective renewable fuels to replace fossil energy sources.",
                                                LocalDate.now().minusWeeks(2)),

                                new Post(
                                                Mavibioscience,
                                                "Mavi Bioscience: Protecting ecosystems",
                                                "Reducing livestock emissions for sustainability.",
                                                "Our solutions reduce livestock emissions, preserving natural habitats and biodiversity.",
                                                LocalDate.now().minusDays(12)),

                                new Post(
                                                doecos,
                                                "Doecos: Smart water heating solutions",
                                                "Efficient water and energy-saving systems.",
                                                "We develop water heating systems that minimize water and energy consumption.",
                                                LocalDate.now().minusDays(6)),

                                new Post(
                                                viscan,
                                                "VISCAN: Smart city planning",
                                                "Technology for sustainable urban growth.",
                                                "Our technology supports sustainable urban development and reduces environmental impact.",
                                                LocalDate.now().minusDays(15)),

                                new Post(
                                                nxtCatchas,
                                                "NXT Catch: Sustainable fishing gear",
                                                "Protecting marine life with innovation.",
                                                "We design innovative fishing solutions that protect marine life and reduce bycatch.",
                                                LocalDate.now().minusDays(7)),

                                new Post(
                                                squareRoot,
                                                "SquareRoot: Inclusive urban green spaces",
                                                "Bringing greenery to underserved areas.",
                                                "We bring green infrastructure to underserved communities, improving urban quality of life.",
                                                LocalDate.now().minusDays(20)),

                                new Post(
                                                alteredPowerAs,
                                                "Altered Power: Solar solutions for remote areas",
                                                "Portable solar energy for off-grid regions.",
                                                "Our portable solar devices bring electricity to off-grid communities.",
                                                LocalDate.now().minusWeeks(3)),

                                new Post(
                                                besenGroupAs,
                                                "Besen Group: Charging the future",
                                                "Expanding EV charging infrastructure.",
                                                "We install EV charging stations to promote clean mobility in urban areas.",
                                                LocalDate.now().minusDays(18)),

                                new Post(
                                                seacirc,
                                                "Seacirc: Enabling circular economy",
                                                "Helping businesses adopt sustainable production.",
                                                "Our software helps companies transition to sustainable production models.",
                                                LocalDate.now().minusWeeks(4)),

                                new Post(
                                                manolin,
                                                "Manolin: Ensuring aquaculture health",
                                                "Monitoring aquatic animal health.",
                                                "We monitor aquatic animal health to ensure sustainable and healthy seafood production.",
                                                LocalDate.now().minusDays(9)),

                                new Post(
                                                algaeproAs,
                                                "Algaepro: Sustainable bioproducts",
                                                "Eco-friendly bioplastics from algae.",
                                                "We produce eco-friendly bioplastics from algae, reducing land and marine pollution.",
                                                LocalDate.now().minusDays(14)),

                                new Post(
                                                elifeAs,
                                                "Elife: Eco-friendly urban mobility",
                                                "Electric bikes for greener cities.",
                                                "We offer electric bikes as a sustainable transportation option for city dwellers.",
                                                LocalDate.now().minusDays(11)),

                                new Post(
                                                nordicElectrofuel,
                                                "Nordic Electrofuel: Pioneering synthetic fuels",
                                                "Renewable energy transformed into fuel.",
                                                "We transform renewable energy into sustainable fuel solutions.",
                                                LocalDate.now().minusWeeks(5)),

                                new Post(
                                                evoltec,
                                                "Evoltec: Innovating for energy efficiency",
                                                "Advanced tech for industrial energy savings.",
                                                "We provide advanced technologies to improve energy efficiency in industries.",
                                                LocalDate.now().minusDays(22)),

                                new Post(
                                                ecoCharge,
                                                "EcoCharge: Solar-powered EV charging",
                                                "Making electric mobility even greener.",
                                                "We develop solar charging stations to make electric mobility greener.",
                                                LocalDate.now().minusDays(16)),

                                new Post(
                                                waterWise,
                                                "WaterWise: Smart water management",
                                                "Optimizing water usage efficiently.",
                                                "Our technology helps optimize water use, ensuring access to clean water.",
                                                LocalDate.now().minusDays(19)),

                                new Post(
                                                greenTransport,
                                                "GreenTransport: Electrifying public transit",
                                                "Reducing emissions through electric transport.",
                                                "We offer electric public transport solutions to reduce emissions and improve air quality.",
                                                LocalDate.now().minusWeeks(6)));

                posts.forEach(post -> {

                        switch (post.getStartup().getName()) {
                                case "VhAT":
                                        post.addOds(industryInnovation);
                                        post.addOds(responsibleConsumption);
                                        post.addOds(climateAction);
                                        break;
                                case "GamingBuddy":
                                        post.addOds(qualityEducation);
                                        post.addOds(reducedInequalities);
                                        break;
                                case "Nørs":
                                        post.addOds(goodHealthWellBeing);
                                        post.addOds(industryInnovation);
                                        break;
                                case "AndLight":
                                        post.addOds(affordableCleanEnergy);
                                        post.addOds(climateAction);
                                        break;
                                case "Influencer Marketing Hub":
                                        post.addOds(reducedInequalities);
                                        post.addOds(industryInnovation);
                                        break;
                                case "Too Good To Go":
                                        post.addOds(responsibleConsumption);
                                        post.addOds(climateAction);
                                        post.addOds(zeroHunger);
                                        break;
                                case "Doublepoint":
                                        post.addOds(climateAction);
                                        post.addOds(responsibleConsumption);
                                        break;
                                case "BeCause":
                                        post.addOds(industryInnovation);
                                        break;
                                case "Bitlig Renewable Fuels":
                                        post.addOds(affordableCleanEnergy);
                                        break;
                                case "Mavi Bioscience":
                                        post.addOds(lifeOnLand);
                                        break;
                                case "Doecos":
                                        post.addOds(cleanWaterSanitation);
                                        break;
                                case "VISCAN":
                                        post.addOds(sustainableCities);
                                        break;
                                case "NXT Catch AS":
                                        post.addOds(lifeBelowWater);
                                        break;
                                case "SquareRoot":
                                        post.addOds(sustainableCities);
                                        break;
                                case "Altered Power AS":
                                        post.addOds(affordableCleanEnergy);
                                        break;
                                case "Besen Group AS":
                                        post.addOds(sustainableCities);
                                        break;
                                case "Seacirc":
                                        post.addOds(responsibleConsumption);
                                        break;
                                case "Manolin":
                                        post.addOds(goodHealthWellBeing);
                                        break;
                                case "Algaepro AS":
                                        post.addOds(lifeOnLand);
                                        break;
                                case "Elife AS":
                                        post.addOds(sustainableCities);
                                        break;
                                case "Nordic Electrofuel":
                                        post.addOds(responsibleConsumption);
                                        break;
                                case "Evoltec":
                                        post.addOds(industryInnovation);
                                        break;
                                case "EcoCharge":
                                        post.addOds(affordableCleanEnergy);
                                        break;
                                case "WaterWise":
                                        post.addOds(cleanWaterSanitation);
                                        break;
                                case "GreenTransport":
                                        post.addOds(affordableCleanEnergy);
                                        break;
                                default:
                                        System.out.println(
                                                        "No se encontro el post de la startup: "
                                                                        + post.getStartup().getName());
                                        break;
                        }

                        if (service.findByTitle(post.getTitle()) == null) {
                                service.save(post);

                                Comment newComment = new Comment();

                                newComment.setComment("I love " + post.getTitle());
                                newComment.setPost(post);
                                newComment.setUser(vhat);

                                vhat.addComment(newComment);

                                commentService.save(newComment);
                                startupService.save(vhat);
                                service.save(post);
                        }

                });

        }
}