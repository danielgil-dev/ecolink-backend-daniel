package com.ecolink.spring.loaders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.ecolink.spring.entity.Ods;
import com.ecolink.spring.entity.Post;
import com.ecolink.spring.entity.Startup;
import com.ecolink.spring.service.OdsService;
import com.ecolink.spring.service.PostService;
import com.ecolink.spring.service.StartupService;

@Component
@Order(4)
public class PostDataLoader implements CommandLineRunner {

    @Autowired
    private PostService service;

    @Autowired
    private StartupService startupService;

    @Autowired
    private OdsService odsService;

    @Override
    public void run(String... args) throws Exception {
        Startup vhat = startupService.findByName("VhAT");
        Startup gamingBuddy = startupService.findByName("GamingBuddy");
        Startup nors = startupService.findByName("Nørs");
        Startup andLight = startupService.findByName("AndLight");
        Startup imHub = startupService.findByName("Influencer Marketing Hub");
        Startup tooGoodToGo = startupService.findByName("Too Good To Go");
        Startup doublepoint = startupService.findByName("Doublepoint");

        Ods saludBienestar = odsService.findByName("Salud y bienestar");
        Ods igualdadGenero = odsService.findByName("Igualdad de género");
        Ods ciudadesSostenibles = odsService.findByName("Ciudades y comunidades sostenibles");
        Ods industriaInnovacion = odsService.findByName("Industria, innovación e infraestructura");
        Ods consumoResponsable = odsService.findByName("Producción y consumo responsables");
        Ods accionClima = odsService.findByName("Acción por el clima");



         service.save(new Post(
            vhat,
            saludBienestar,
            "VhAT: Apoyo emocional a través de IA",
            "Con nuestra tecnología de inteligencia artificial, VhAT ayuda a las personas a cuidar su salud mental y bienestar emocional.",
            LocalDate.now()
        ));

        service.save(new Post(
            gamingBuddy,
            igualdadGenero,
            "GamingBuddy: Promoviendo la igualdad en los videojuegos",
            "Creamos una comunidad inclusiva para que jugadores de todos los géneros y culturas puedan disfrutar de un espacio seguro y respetuoso.",
            LocalDate.now().minusDays(5)
        ));

        service.save(new Post(
            nors,
            ciudadesSostenibles,
            "Nørs: Transformando la movilidad urbana",
            "Nuestro servicio de transporte ecológico impulsa ciudades más sostenibles y reduce la contaminación.",
            LocalDate.now().minusDays(10)
        ));

        service.save(new Post(
            andLight,
            industriaInnovacion,
            "AndLight: Innovación en iluminación sostenible",
            "Desarrollamos sistemas de iluminación que ahorran energía y crean entornos más eficientes.",
            LocalDate.now().minusWeeks(1)
        ));

        service.save(new Post(
            imHub,
            industriaInnovacion,
            "IM Hub: Potenciando el marketing digital",
            "Ofrecemos herramientas para influencers que ayudan a optimizar estrategias y fomentar la innovación en la industria.",
            LocalDate.now().minusMonths(1)
        ));

        service.save(new Post(
            tooGoodToGo,
            consumoResponsable,
            "Too Good To Go: Luchando contra el desperdicio de alimentos",
            "Conectamos a consumidores con negocios locales para salvar alimentos que de otro modo se desperdiciarían.",
            LocalDate.now()
        ));

        service.save(new Post(
            doublepoint,
            accionClima,
            "Doublepoint: Soluciones para un mundo más verde",
            "Facilitamos el reciclaje y la gestión de residuos para ayudar a las empresas a reducir su impacto ambiental.",
            LocalDate.now().minusDays(3)
        ));


         List<Post> posts = Arrays.asList(
                new Post(
                        vhat,
                        saludBienestar,
                        "VhAT: Apoyo emocional a través de IA",
                        "Con nuestra tecnología de inteligencia artificial, VhAT ayuda a las personas a cuidar su salud mental y bienestar emocional.",
                        LocalDate.now()),
                new Post(
                        gamingBuddy,
                        igualdadGenero,
                        "GamingBuddy: Promoviendo la igualdad en los videojuegos",
                        "Creamos una comunidad inclusiva para que jugadores de todos los géneros y culturas puedan disfrutar de un espacio seguro y respetuoso.",
                        LocalDate.now().minusDays(5)),
                new Post(
                        nors,
                        ciudadesSostenibles,
                        "Nørs: Transformando la movilidad urbana",
                        "Nuestro servicio de transporte ecológico impulsa ciudades más sostenibles y reduce la contaminación.",
                        LocalDate.now().minusDays(10)),
                new Post(
                        andLight,
                        industriaInnovacion,
                        "AndLight: Innovación en iluminación sostenible",
                        "Desarrollamos sistemas de iluminación que ahorran energía y crean entornos más eficientes.",
                        LocalDate.now().minusWeeks(1)),
                new Post(
                        imHub,
                        industriaInnovacion,
                        "IM Hub: Potenciando el marketing digital",
                        "Ofrecemos herramientas para influencers que ayudan a optimizar estrategias y fomentar la innovación en la industria.",
                        LocalDate.now().minusMonths(1)),
                new Post(
                        tooGoodToGo,
                        consumoResponsable,
                        "Too Good To Go: Luchando contra el desperdicio de alimentos",
                        "Conectamos a consumidores con negocios locales para salvar alimentos que de otro modo se desperdiciarían.",
                        LocalDate.now()),
                new Post(
                        doublepoint,
                        accionClima,
                        "Doublepoint: Soluciones para un mundo más verde",
                        "Facilitamos el reciclaje y la gestión de residuos para ayudar a las empresas a reducir su impacto ambiental.",
                        LocalDate.now().minusDays(3))
        );

        posts.forEach(post -> {
            if (!service.existsByTitleAndStartupAndOds(post.getTitle(), post.getStartup(), post.getOds())) {
                service.save(post);
            }
        });

    }

}
