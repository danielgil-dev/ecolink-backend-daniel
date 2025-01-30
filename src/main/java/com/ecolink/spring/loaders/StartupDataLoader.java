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
                                                "Empresa de tecnología que desarrolla sensores avanzados para optimizar procesos industriales con menor impacto ambiental."),
                                new Startup("BeCause", Arrays.asList(
                                                odsService.findByName("Industria, innovación e infraestructura"),
                                                odsService.findByName("Producción y consumo responsables")),
                                                "contact@because.com",
                                                "Plataforma de gestión de sostenibilidad para empresas, facilitando decisiones responsables."),

                                new Startup("Bitlig Renewable Fuels", Arrays.asList(
                                                odsService.findByName("Acción por el clima"),
                                                odsService.findByName("Energía asequible y no contaminante")),
                                                "info@bitligrenewables.com",
                                                "Desarrolla tecnología para reducir costos de producción de combustibles sostenibles."),

                                new Startup("Mavi Bioscience", Arrays.asList(
                                                odsService.findByName("Acción por el clima"),
                                                odsService.findByName("Producción y consumo responsables")),
                                                "contact@mavibioscience.com",
                                                "Solución innovadora para reducir emisiones de metano del ganado en un 80%."),

                                new Startup("Doecos", Arrays.asList(
                                                odsService.findByName("Energía asequible y no contaminante"),
                                                odsService.findByName("Ciudades y comunidades sostenibles")),
                                                "info@doecos.com",
                                                "Ayuda a clientes a adoptar fuentes de calor más sostenibles en sus hogares."),

                                new Startup("VISCAN", Arrays.asList(
                                                odsService.findByName("Industria, innovación e infraestructura"),
                                                odsService.findByName("Ciudades y comunidades sostenibles")),
                                                "contact@viscan.com",
                                                "Captura 3D de edificios para mejorar la eficiencia y reducir huella de carbono."),

                                new Startup("NXT Catch AS", Arrays.asList(
                                                odsService.findByName("Vida submarina"),
                                                odsService.findByName("Producción y consumo responsables")),
                                                "info@nxtcatch.com",
                                                "Innovaciones para la pesca sostenible y reducción de desperdicio de equipos."),

                                new Startup("SquareRoot", Arrays.asList(
                                                odsService.findByName("Ciudades y comunidades sostenibles"),
                                                odsService.findByName("Acción por el clima")),
                                                "contact@squareroot.com",
                                                "Plataforma para fomentar la infraestructura verde en ciudades."),

                                new Startup("Altered Power AS", Arrays.asList(
                                                odsService.findByName("Energía asequible y no contaminante"),
                                                odsService.findByName("Acción por el clima")),
                                                "info@alteredpower.com",
                                                "Energía renovable portátil para personas que disfrutan actividades al aire libre."),

                                new Startup("Besen Group AS", Arrays.asList(
                                                odsService.findByName("Energía asequible y no contaminante"),
                                                odsService.findByName("Ciudades y comunidades sostenibles")),
                                                "contact@besengroup.com",
                                                "Distribución de cargadores eléctricos para la movilidad sostenible."),

                                new Startup("Seacirc", Arrays.asList(
                                                odsService.findByName("Producción y consumo responsables"),
                                                odsService.findByName("Industria, innovación e infraestructura")),
                                                "info@seacirc.com",
                                                "Software para mejorar la sostenibilidad de empresas mediante el uso de datos."),

                                new Startup("Manolin", Arrays.asList(
                                                odsService.findByName("Vida submarina"),
                                                odsService.findByName("Industria, innovación e infraestructura")),
                                                "contact@manolin.com",
                                                "Gestión de salud digital para la acuicultura sostenible."),

                                new Startup("Algaepro AS", Arrays.asList(
                                                odsService.findByName("Producción y consumo responsables"),
                                                odsService.findByName("Acción por el clima")),
                                                "info@algaepro.com",
                                                "Producción de biomasa a partir de microalgas para bioplásticos y alimentación."),

                                new Startup("Elife AS", Arrays.asList(
                                                odsService.findByName("Ciudades y comunidades sostenibles"),
                                                odsService.findByName("Energía asequible y no contaminante")),
                                                "contact@elife.com",
                                                "Fabricación y distribución de bicicletas eléctricas."),

                                new Startup("Nordic Electrofuel", Arrays.asList(
                                                odsService.findByName("Acción por el clima"),
                                                odsService.findByName("Energía asequible y no contaminante")),
                                                "info@nordicelectrofuel.com",
                                                "Transformación de energía eléctrica en combustibles renovables."),

                                new Startup("Evoltec", Arrays.asList(
                                                odsService.findByName("Industria, innovación e infraestructura"),
                                                odsService.findByName("Acción por el clima")),
                                                "contact@evoltec.com",
                                                "Servicios y productos tecnológicos para la industria energética sostenible."),

                                new Startup("EcoCharge", Arrays.asList(
                                                odsService.findByName("Energía asequible y no contaminante"),
                                                odsService.findByName("Ciudades y comunidades sostenibles")),
                                                "info@ecocharge.com",
                                                "Estaciones de carga solar para vehículos eléctricos."),

                                new Startup("WaterWise", Arrays.asList(
                                                odsService.findByName("Agua limpia y saneamiento"),
                                                odsService.findByName("Acción por el clima")),
                                                "contact@waterwise.com",
                                                "Tecnología de monitoreo para el uso eficiente del agua."),

                                new Startup("GreenTransport", Arrays.asList(
                                                odsService.findByName("Ciudades y comunidades sostenibles"),
                                                odsService.findByName("Acción por el clima")),
                                                "info@greentransport.com",
                                                "Soluciones de transporte público eléctrico y compartido."),

                                new Startup("EcoPackaging", Arrays.asList(
                                                odsService.findByName("Producción y consumo responsables"),
                                                odsService.findByName("Acción por el clima")),
                                                "contact@ecopackaging.com",
                                                "Desarrollo de envases biodegradables y compostables."),

                                new Startup("SustainableFashion", Arrays.asList(
                                                odsService.findByName("Producción y consumo responsables"),
                                                odsService.findByName("Trabajo decente y crecimiento económico")),
                                                "info@sustainablefashion.com",
                                                "Moda sostenible con materiales reciclados y comercio justo."));

                startups.forEach(startup -> {
                        if (!service.existsByName(startup.getName())) {
                                service.save(startup);
                        }
                });
        }

}
